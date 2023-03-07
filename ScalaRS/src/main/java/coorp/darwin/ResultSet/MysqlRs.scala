package coorp.darwin.ResultSet

import coorp.darwin.connections.conection
import org.apache.spark.sql.Row

import java.sql.PreparedStatement
import scala.collection.mutable.ListBuffer

class MysqlRs extends conection {

  val sqlString = "SELECT id, email, phone, date_added FROM leads WHERE email is not null;"
  var pstmt: PreparedStatement = connMysql.prepareStatement(sqlString)
  val exRS = pstmt.execute
  val exctRs = pstmt.executeQuery()
  println("mySql conn")
  println(exRS)

  //var ls = new ListBuffer[String]()
  var ls = ListBuffer[Any]()
  val mList = scala.collection.mutable.MutableList(Row(null, null, null, null))

  while (exctRs.next()) {
    val id = exctRs.getInt(1)
    val email = exctRs.getString(2)
    val phone = exctRs.getString(3)
    val date = exctRs.getTimestamp(4)
    //println(id + " | " + email)
    mList.++=(ListBuffer(Row(id, email, phone, date)))
  }

}
