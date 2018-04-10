# Project Name

Write Hive Data To ES in a kerberized Cluster.

Read Data from Hive (hdfs) and write it to Elasticsearch cluster using Apache Spark


## Getting Started

This project will help you read your data from Hive and write it as index to Elasticsearch in a kerberized Cluster.

### Prerequisites

```
sbt : 1.1.2
Apache Spark : 1.6.2
Elasticsearch : 5.4.3
HDP cluster : 2.5.3
```

### Packaging

Build un uber Jar (include all dependencies except %provided onces)

```
>sbt assembly
```

Build jar

```
>sbt package
```


## Deployment

To deploy the project in yarn cluster:

```
spark-submit \
--class WriteHiveToES \
--master yarn --deploy-mode cluster \
--num-executors 3 --driver-memory 4g --executor-memory 8g --executor-cores 1 \
--files /usr/hdp/current/spark-client/conf/hive-site.xml,/usr/hdp/current/spark-client/conf/core-site.xml \
--jars /usr/hdp/current/spark-client/lib/datanucleus-api-jdo-3.2.6.jar, \
/usr/hdp/current/spark-client/lib/datanucleus-rdbms-3.2.9.jar, \
/usr/hdp/current/spark-client/lib/datanucleus-core-3.2.10.jar \
--queue [queueName] /path/to/Your/Jar/POCSparkHiveES-assembly-0.1.jar
```

## Built With
* [sbt](https://www.scala-sbt.org/) - Dependency Management and deployment

## More about this project
Find more details about this project in this [article](http://yacine.aitouarab.com/blog/sparkes.html)
