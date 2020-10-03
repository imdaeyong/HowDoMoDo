#!/usr/bin/env python
# coding: utf-8

# In[1]:


# import modules
from pyspark.sql import SQLContext
from pyspark.sql.functions import *
from pyspark import SparkContext
from pyspark.sql.types import *
import pandas as pd
import numpy as np

class pysparkTest:
    
    sc = SparkContext()
    sqlContext = SQLContext(sc)
    
    def __init__(self, name):
        pass

    def makedf():
        csv_list = ["09","10","11","12"]
        df_list = []
        for listname in csv_list:
            df = pysparkTest.sqlContext.read.format('com.databricks.spark.csv')                .options(header='true', inferSchema='true')                .load('C:/ssafy/2nd/s03p22a305/bigdata/analysis/2019-'+listname+'.csv')                .cache()
        df_list.append(df)
        schema = StructType([StructField("성별", StringType(), True), StructField("연령", StringType(), False),                             StructField("승인시간대1", LongType(), True), StructField("가맹점업종코드", StringType(), True)])
        df_all = pysparkTest.sqlContext.createDataFrame([],schema)
        for df in df_list:
            df_all.union(df)
        return df_all


# In[2]:


py = pysparkTest
py


# In[3]:


df = py.makedf()
df


# In[ ]:




