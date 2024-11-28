name := "SparkFileAnalyzer"

version := "0.1"

scalaVersion := "2.12.17" // Adjust according to your Spark version

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.4.0",
  "org.apache.spark" %% "spark-sql"  % "3.4.0"
)
