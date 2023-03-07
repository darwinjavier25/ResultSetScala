package coorp.darwin.ResultSet

import coorp.darwin.connections.conection
import org.apache.spark.sql.Row

import java.sql.PreparedStatement
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

class PsqlRs extends conection {

  val psqlString = "SELECT id, email, phone, created_at FROM leads WHERE email <> '' LIMIT 5;"
  val psqlPstmt: PreparedStatement = connPsql.prepareStatement(psqlString)
  val exRS = psqlPstmt.execute
  val psqlExctRs = psqlPstmt.executeQuery()
  println("postgres conn")
  println(exRS)

  var ar = ArrayBuffer(Row())
  val mList = scala.collection.mutable.MutableList(Row(null, null, null, null))

  while (psqlExctRs.next()) {
    val id = psqlExctRs.getInt(1)
    val email = psqlExctRs.getString(2)
    val phone = psqlExctRs.getString(3)
    val date = psqlExctRs.getTimestamp(4)
    ar.append(Row(id, email, phone, date))
    mList.++=(ListBuffer(Row(id, email, phone, date)))
  }

}
