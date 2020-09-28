from pyspark.sql import SQLContext
# from pyspark.sql.functions import *
from pyspark import SparkContext
from pyspark.sql.types import *
# from functools import reduce
# from pyspark.sql import DataFrame
from pyspark.sql import SparkSession
from pyspark.sql import Row
import os

# import time
import pyspark

class Recommand:
    spark = 0
    sqlContext = 0
    df_list = 0
    # testnum = 0
    #
    # @staticmethod
    # def test():
    #     Recommand.testnum += 1

    @staticmethod
    def create():

        os.environ["PYSPARK_PYTHON"] = "/usr/bin/python3"
        os.environ["PYSPARK_DRIVER_PYTHON"] = "/usr/bin/python3"

        # df 로드 및 group 단계까지 캐싱 처리
        sc = SparkContext('local').getOrCreate()
        Recommand.sqlContext = SQLContext(sc)
        # Recommand.spark = SparkSession(sc)
        Recommand.spark = SparkSession.builder.master("spark://spark-skp-master:7077").appName("pysprk")
        Recommand.spark = Recommand.spark.config("spark.cores.max", "48")
        Recommand.spark = Recommand.spark.config("spark.driver.memory", "32g")
        Recommand.spark = Recommand.spark.config("spark.executor.memory", "32g")
        Recommand.spark = Recommand.spark.config("spark.python.worker.memory", "32g")
        Recommand.spark = Recommand.spark.getOrCreate()
        print("create 단계입니다")
        # df_list = load_csv()
        # return self.preprocessing(df_list)

    @staticmethod
    def load_csv():
        print("csv 로딩 단계입니다")
        csv_list = ["09", "10", "11", "12"]
        df_list = []
        activityList = [1001, 1003, 1004, 1007, 1008, 1010, 1099, 1101, 1102, 1104, 1105, 1199, 1201, 1202, 1203, 1204,
                        1205, 1206, 1207, 1208, 1299, 2002, 2003, 2004, 2099, 2104, 2107, 2109, 2110, 2111, 2112, 2113,
                        2199, 2401, 2402, 2403, 2404, 2406, 2407, 2499, 2299, 2312, 2317, 4113, 7104, 7299, 6001, 6002,
                        6003, 6004, 6005, 6006, 6007, 6008, 6009, 6010, 6011, 6012, 6013, 6015, 6016, 6021, 6099, 6014,
                        6019, 6020, 6018, 6107, 6113, 6201, 6202, 6203, 6204, 6205, 6206, 6207, 6208, 6209, 6210, 6111,
                        6112, 6299, 7101, 7102, 7103, 7105, 7106, 7108, 7199]

        for listname in csv_list:
            df = Recommand.sqlContext.read.format('com.databricks.spark.csv') \
                .options(header='true', inferSchema='true') \
                .load('C:/ssafy/2nd/s03p23a305/bigdata/analysis/2019-' + listname + '-UTF.csv') \
                .cache()

            # 기업 데이터 제거
            df = df.filter(df.개인기업구분 != "기업")
            df = df.filter(df.성별 != "0.기타")

            # 활동 분류
            df = df.filter(df.가맹점업종코드.isin(activityList))

            removeColList = ["회원_시도명", "회원_시군구명", "개인기업구분", "매출년월일", "결제금액", "결제건수"]
            for ls in removeColList:
                df = df.drop(ls)
            df.persist()
            df_list.append(df)
            Recommand.df_list = df_list

    @staticmethod
    def preprocessing(df_list):
        print("preprocessing 입니다")
        print("union 단계입니다")
        df_all = df_list[0]
        for idx, df in enumerate(df_list):
            if (idx == 0):
                continue
            print(idx)
            df_all = df_all.union(df)


        print("rename 단계입니다")
        df_all_renamed = df_all.withColumnRenamed("성별", "gender") \
            .withColumnRenamed("연령", "age") \
            .withColumnRenamed("승인시간대1", "time") \
            .withColumnRenamed("가맹점_시도명", "do") \
            .withColumnRenamed("가맹점_시군구명", "si") \
            .withColumnRenamed("가맹점_읍면동명", "dong") \
            .withColumnRenamed("가맹점업종코드", "code") \
            .withColumnRenamed("회원수", "n")

        print("mapping 단계입니다")
        rdd = df_all_renamed.rdd
        rdd = rdd.map(lambda x: Row(gender=int(x[0][0]), \
                                    age=int(x[1][0]), \
                                    time=int(x[2]), \
                                    do=x[3], \
                                    si=x[4], \
                                    dong=x[5], \
                                    code=int(x[6]), \
                                    n=int(x[7])))
        print("schema 생성 단계입니다")
        schema = StructType([StructField("gender", StringType(), True), \
                             StructField("age", StringType(), True), \
                             StructField("time", IntegerType(), True), \
                             StructField("do", StringType(), True), \
                             StructField("si", StringType(), True), \
                             StructField("dong", StringType(), True), \
                             StructField("code", IntegerType(), True), \
                             StructField("n", IntegerType(), True)])

        df_mapped = Recommand.spark.createDataFrame(rdd, schema).persist(pyspark.StorageLevel.DISK_ONLY)
        df_mapped.registerTempTable("df_tmp")

        print("grouping 단계입니다")
        df_group = df_mapped.groupBy('gender', 'age', 'time', 'do', 'si', 'dong', 'code').sum('n')

        df_group = df_group.withColumnRenamed("sum(n)", "total").persist(pyspark.StorageLevel.DISK_ONLY)
        # df_group.show()
        df_group.registerTempTable("df_group")

        print("si 찾기 단계입니다")
        si = "'종로구'"
        query = "select gender, age, time, code, total from df_group where si =" + si + " order by total desc"
        selected_df = Recommand.sqlContext.sql(query).persist(pyspark.StorageLevel.DISK_ONLY)
        # selected_df.show()

        print("통계 단계입니다")
        selected_df.registerTempTable("selected_df")
        query = "select code, sum(total) as sum from selected_df group by code order by sum desc"
        sum_groupByDf = Recommand.sqlContext.sql(query).persist(pyspark.StorageLevel.DISK_ONLY)
        return sum_groupByDf.take(3)







