import org.apache.spark.sql.SparkSession

object SparkSessionProvider {
    def getSession(appName: String): SparkSession = {
        SparkSession.builder()
            .appName(appName)
            .getOrCreate()
    }
}