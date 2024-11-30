import Config._
import FileUtils._
import LogParser._
import SparkSessionProvider._

object LogFileAnalysis {
  def main(args: Array[String]): Unit = {
    val spark = getSession("Spark File Analyzer")
    implicit val sc = spark.sparkContext

    deletePathIfExists(outputPath)

    val logData = spark.read.text(inputPath)
    logData.show(truncate = false)

    val parsedLogs = parseLogs(logData)
    parsedLogs.show(truncate = false)

    parsedLogs.write
      .mode("overwrite")
      .option("header", "true")
      .csv(outputPath)

    spark.stop()
  }
}