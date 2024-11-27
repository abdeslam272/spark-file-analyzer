import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.spark.SparkContext

object FileUtils {
  def deletePathIfExists(path: String)(implicit sc: SparkContext): Unit = {
    val fs = FileSystem.get(sc.hadoopConfiguration)
    val outputDir = new Path(path)
    if (fs.exists(outputDir)) {
      fs.delete(outputDir, true)
    }
  }
}