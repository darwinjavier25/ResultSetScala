package coorp.darwin.dataframes

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
object SparkJDBC {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .appName("DaasConsumer")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("WARN")

    val mySqlDf = spark.read.format("jdbc")
    .option("url", "jdbc:mysql://localhost:3306/ResultSet")
    .option("driver", "com.mysql.cj.jdbc.Driver")
    .option("user", "darwin")
    .option("password", "$My21210909sql")
    .option("query", "SELECT id, email, phone, date_added FROM leads WHERE email is not null")
    .load()

    val pSqlDf = spark.read.format("jdbc")
    .option("url", "jdbc:postgresql://localhost:5432/darwin_psql")
    .option("driver", "org.postgresql.Driver")
    .option("user", "darwin_psql")
    .option("password", "$Ps21210909sql")
    .option("query", "SELECT id, email, phone, created_at FROM leads WHERE email <> '' LIMIT 5")
    .load()


    //df.show()
    val df = mySqlDf.union(pSqlDf)
    df.show()

  }

}
