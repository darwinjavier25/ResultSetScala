package coorp.darwin.connections

import java.sql.{Connection, DriverManager}

trait conection {

  val mySqldbUrl = "jdbc:mysql://localhost:3306/ResultSet"
  val psqlDbUrl = "jdbc:postgresql://localhost:5432/darwin_psql"

  var mySqldbProp = new java.util.Properties
  mySqldbProp.setProperty("driver", "com.mysql.jdbc.Driver")
  mySqldbProp.setProperty("user", "darwin")
  mySqldbProp.setProperty("password", "$My21210909sql")
  mySqldbProp.setProperty("stringtype", "unspecified")

  var PsqlProp = new java.util.Properties
  PsqlProp.setProperty("driver", "org.postgresql.Driver")
  PsqlProp.setProperty("user", "darwin_psql")
  PsqlProp.setProperty("password", "$Ps21210909sql")
  PsqlProp.setProperty("stringtype", "unspecified")

  val connMysql: Connection = DriverManager.getConnection(mySqldbUrl, mySqldbProp)
  val connPsql: Connection = DriverManager.getConnection(psqlDbUrl, PsqlProp)

}
