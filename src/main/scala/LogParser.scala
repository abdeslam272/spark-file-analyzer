import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions._

object LogParser {
    def parseLogs(df: DataFrame): DataFrame = {
        df.withColumn("raw_line", col("value"))
          // Extraire la date (jour/mois/année)
          .withColumn("date", regexp_extract(col("value"), """(\d{2}/\d{2}/\d{2})""",1))
          // Extraire l'heure
          .withColumn("time", regexp_extract(col("value"), """(\d{2}:\d{2}:\d{2})""",1))
          // Extraire le type du log (par exemple INFO, ERROR)
          .withColumn("log_level", regexp_extract(col("value"), """\s(\w+)\s""", 1))
          // Extraire le message complet après le type du log
          .withColumn("message", regexp_extract(col("value"), """\s\w+\s(.+)""", 1))
          .drop("value")
    }
}