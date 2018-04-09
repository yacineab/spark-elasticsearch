name := "spark-elasticsearch"

version := "0.1"

scalaVersion := "2.10.7"


// spark dependencies are available on the cluster, not included in assembly-jar => set to %provided%
libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.6.2" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "1.6.2" % "provided"

//SparkElasitcSearch dependency to include in assembly jar
libraryDependencies += "org.elasticsearch" %% "elasticsearch-spark-13" % "5.4.3"

// Dependencies fot Unit tests
libraryDependencies += "junit" % "junit" % "4.10" % Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.1"


assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
