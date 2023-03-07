package coorp.darwin.ResultSet

object IntermediateDF {

  val mySqlRsInstance = new MysqlRs
  val mySqlList = mySqlRsInstance.mList
  val psqlRsInstance = new PsqlRs
  val psqlList = psqlRsInstance.mList

}
