package coorp.darwin.dataframes

import coorp.darwin.ResultSet.IntermediateDF.{mySqlList, psqlList}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.types._


object CreateDF {

  def main(args: Array[String]): Unit = {

    val mysql = mySqlList
    val psql = psqlList

    val spark = SparkSession
      .builder
      .appName("DaasConsumer")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val schema = StructType(List
    (StructField("id", org.apache.spark.sql.types.IntegerType, true)
      , StructField("email", org.apache.spark.sql.types.StringType, true)
      , StructField("phone", org.apache.spark.sql.types.StringType, true)
      , StructField("date", org.apache.spark.sql.types.TimestampType, true)
    ))

    val mySqlRdd = spark.sparkContext.parallelize(mysql)
    val myslDF = spark.createDataFrame(mySqlRdd, schema)
    val psqlRdd = spark.sparkContext.parallelize(psql)
    val psqlDF = spark.createDataFrame(psqlRdd, schema)

    val unDF = myslDF.union(psqlDF)
    unDF.limit(50).write.mode(SaveMode.Overwrite).format("parquet").saveAsTable("test1")
    println("Succes")
  }

}
