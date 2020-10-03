from pyspark.sql import SQLContext
from pyspark import SparkContext
from pyspark.sql.types import *
from pyspark.sql import SparkSession
from pyspark.sql import Row
import pyspark

import os

class Recommand():
    instance = None
    spark, sc, sqlContext = None, None, None
    df_list, df_group, code_df = None, None, None
    result_dict = dict()

    def __new__(cls):
        if not cls.instance:
            cls.instance = object.__new__(cls)
            print("instance 생성!")
        return cls.instance

    @classmethod
    def create(cls):
        # df 로드 및 group 단계까지 캐싱 처리
        if cls.sc is None:
            cls.sc = SparkContext('local')
        # else:
        #     cls.sc.stop()
        #     cls.sc = SparkContext('local')
        if cls.sqlContext is None:
            cls.sqlContext = SQLContext(cls.sc)
        if cls.spark is None:
            cls.spark = SparkSession(cls.sc)
        print("create 단계입니다")

    @classmethod
    def load_csv(cls):
        print("csv 로딩 단계입니다")
        # csv_list = ["09", "10", "11", "12"]
        csv_list = ["09", "10"]
        df_list = []
        activityList = [1001, 1003, 1004, 1007, 1008, 1010, 1099, 1101, 1102, 1104, 1105, 1199, 1201, 1202, 1203, 1204,
                        1205, 1206, 1207, 1208, 1299, 2002, 2003, 2004, 2099, 2104, 2107, 2109, 2110, 2111, 2112, 2113,
                        2199, 2401, 2402, 2403, 2404, 2406, 2407, 2499, 2299, 2312, 2317, 4113, 7104, 7299, 6001, 6002,
                        6003, 6004, 6005, 6006, 6007, 6008, 6009, 6010, 6011, 6012, 6013, 6015, 6016, 6021, 6099, 6014,
                        6019, 6020, 6018, 6107, 6113, 6201, 6202, 6203, 6204, 6205, 6206, 6207, 6208, 6209, 6210, 6111,
                        6112, 6299, 7101, 7102, 7103, 7105, 7106, 7108, 7199]

        for listname in csv_list:
            df = cls.sqlContext.read.format('com.databricks.spark.csv') \
                .options(header='true', inferSchema='true') \
                .load('bigdata/2019-' + listname + '-UTF.csv') \
                .cache()

            # 기업 데이터 제거
            df = df.filter(df.개인기업구분 != "기업")
            df = df.filter(df.성별 != "0.기타")

            # 활동 분류
            df = df.filter(df.가맹점업종코드.isin(activityList))
            removeColList = ["성별", "연령", "승인시간대1", "회원_시도명", "회원_시군구명", "개인기업구분", "매출년월일", "결제금액", "결제건수"]
            for ls in removeColList:
                df = df.drop(ls)
            df.persist()
            df_list.append(df)

        cls.df_list = df_list

        cls.code_df = cls.sqlContext.read.format('com.databricks.spark.csv') \
            .options(header='true', inferSchema='true') \
            .load('bigdata/new_codelist-UTF.csv') \
            .cache()

    @classmethod
    def preprocessing(cls):
        print("preprocessing 입니다")
        print("union 단계입니다")
        df_all = cls.df_list[0]
        for idx, df in enumerate(cls.df_list):
            if (idx == 0):
                continue
            print(idx)
            df_all = df_all.union(df)


        print("rename 단계입니다")
        df_all_renamed = df_all.withColumnRenamed("가맹점_시도명", "do") \
            .withColumnRenamed("가맹점_시군구명", "si") \
            .withColumnRenamed("가맹점_읍면동명", "dong") \
            .withColumnRenamed("가맹점업종코드", "code") \
            .withColumnRenamed("회원수", "n")

        # df_all_renamed = df_all.withColumnRenamed("성별", "gender") \
            # .withColumnRenamed("연령", "age") \
            # .withColumnRenamed("승인시간대1", "time") \
            # .withColumnRenamed("가맹점_시도명", "do") \
            # .withColumnRenamed("가맹점_시군구명", "si") \
            # .withColumnRenamed("가맹점_읍면동명", "dong") \
            # .withColumnRenamed("가맹점업종코드", "code") \
            # .withColumnRenamed("회원수", "n")

        print("mapping 단계입니다")
        rdd = df_all_renamed.rdd
        # rdd = rdd.map(lambda x: Row(gender=int(x[0][0]), \
        #                             age=int(x[1][0]), \
        #                             time=int(x[2]), \
        #                             do=x[3], \
        #                             si=x[4], \
        #                             dong=x[5], \
        #                             code=int(x[6]), \
        #                             n=int(x[7])))
        rdd = rdd.map(lambda x: Row(do=x[0], \
                                    si=x[1], \
                                    dong=x[2], \
                                    code=int(x[3]), \
                                    n=int(x[4])))

        print("schema 생성 단계입니다")
        schema = StructType([StructField("do", StringType(), True), \
                             StructField("si", StringType(), True), \
                             StructField("dong", StringType(), True), \
                             StructField("code", IntegerType(), True), \
                             StructField("n", IntegerType(), True)])
        # schema = StructType([StructField("gender", StringType(), True), \
        #                      StructField("age", StringType(), True), \
        #                      StructField("time", IntegerType(), True), \
        #                      StructField("do", StringType(), True), \
        #                      StructField("si", StringType(), True), \
        #                      StructField("dong", StringType(), True), \
        #                      StructField("code", IntegerType(), True), \
        #                      StructField("n", IntegerType(), True)])

        df_mapped = cls.spark.createDataFrame(rdd, schema).persist(pyspark.StorageLevel.DISK_ONLY)
        df_mapped.registerTempTable("df_tmp")

        print("grouping 단계입니다")
        # df_group = df_mapped.groupBy('gender', 'age', 'time', 'do', 'si', 'dong', 'code').sum('n')
        df_group = df_mapped.groupBy('do', 'si', 'dong', 'code').sum('n')

        df_group = df_group.withColumnRenamed("sum(n)", "total").persist(pyspark.StorageLevel.DISK_ONLY)
        cls.df_group = df_group

    @classmethod
    def preAnalysis(cls, si_name):
        cls.df_group.registerTempTable("df_group")
        print("si 찾기 단계입니다")
        si = "'%"+si_name+"%'"
        # query = "select gender, age, time, code, total from df_group where si like " + si + " order by total desc"
        query = "select code, total from df_group where si like " + si + " order by total desc"
        selected_df = cls.sqlContext.sql(query).persist(pyspark.StorageLevel.DISK_ONLY)

        print("통계 단계입니다")
        selected_df.registerTempTable("selected_df")
        query = "select code, sum(total) as cnt from selected_df group by code order by cnt desc"
        sum_groupByDf = cls.sqlContext.sql(query).persist(pyspark.StorageLevel.MEMORY_AND_DISK)
        temp = cls.df_to_dict(sum_groupByDf)
        cls.result_dict[si_name] = temp
        # return sum_groupByDf

    @classmethod
    def df_to_dict(cls, df):
        join_df = df.join(cls.code_df, df.code == cls.code_df.code).select("selected_df.code", "cnt",
                                                                                         "largeCate", "MediumCate",
                                                                                         "smallCate", 'desc').orderBy("cnt", ascending=False)

        total_df = join_df.groupBy("largeCate").sum("cnt").withColumnRenamed('sum(cnt)', 'cnt')

        lc_list = []
        sum_list = []
        lc_row_list = total_df.select('largeCate').collect()
        sum_row_list = total_df.select('cnt').collect()

        for lc in lc_row_list:
            lc_list.append(lc.largeCate)

        for s in sum_row_list:
            sum_list.append(s.cnt)

        cate_dict = dict()
        for lc in lc_list:
            cate_dict[lc] = []

        for row in join_df.rdd.collect():
            largeCate = row[2]
            # mediumCate = row[3]
            smallCate = row[4]
            desc = row[5]
            cnt = row[1]
            cate_dict[largeCate].append({"desc": desc, "jong": smallCate, "cnt": cnt})

        store_dict = dict()
        store_dict['list'] = []
        for idx, lc in enumerate(lc_list):
            temp = {"kinds": lc, "totalCnt": sum_list[idx], "down": cate_dict[lc]}
            store_dict['list'].append(temp)
        # json_val = json.dumps(store_dict, ensure_ascii=False)

        return store_dict
