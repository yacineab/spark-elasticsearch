package yacineab

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.hive.HiveContext
import org.elasticsearch.spark.sql._

object WriteHiveToES {

  def main(args: Array[String]): Unit = {

    val esNodes = "myElasticSearchNode"
    val esPort = "9200"

    //set Kerberos configuration
    System.setProperty("java.security.krb5.conf", "/etc/krb5.conf")
    System.setProperty("sun.security.krb5.debug","true")

    // Spark & authentication configuration
    val conf = new SparkConf().setAppName("spark-elasticsearch")
    conf.set("spark.hadoop.hadoop.security.authentication", "kerberos")
    conf.set("spark.hadoop.hadoop.security.authorization", "true")
    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    // autocreate index if not exist
    conf.set("es.index.auto.create", "true")

    // ElasticSearch configuration
    conf.set("es.nodes", esNodes)
    conf.set("es.port", esPort)


    //SparkContext
    val sc = new SparkContext(conf)

    //sqlContext and hiveContext
    val sqlContext = new SQLContext(sc)
    val hiveContext = new HiveContext(sc)

    // get data from hive table, result in a DATAFRAME
    val hiveDF = hiveContext.sql("select * from myDatabase.myTable")

    //save DF to ElasticSearch
    hiveDF.saveToEs("myIndex/myType")
  }

}
