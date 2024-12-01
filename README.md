# spark-file-analyzer
Tout le monde pense maîtriser les logs... jusqu'à ce débug nocturne en prod à 3h du matin, 6 mois après avoir touché au code pour la dernière fois. 🌙

5 tips essentiels pour des logs qui vous sauveront la vie :

1️⃣ Dès que possible, Adoptez le logging structuré : timestamp, error_type, user_id... Fini le parsing de texte à 3h du mat' !

2️⃣ Utilisez les niveaux de log intelligemment 
 🟩 INFO : "Commande validée" 
 🟨 WARN : "Paiement ralenti"
 🟥 ERROR : "Connexion DB perdue" 
 ❌ FATAL : "Plus de mémoire, arrêt système"

3️⃣ Contextualisez TOUJOURS vos logs 
WHO : ID utilisateur 
WHAT : Action réalisée 
WHERE : Service concerné 
WHY : Description détaillée

4️⃣ Une ligne de log = Une histoire complète Capturez tout le processus en une seule entrée : action, résultat, durée. Votre "vous du futur" vous remerciera.

5️⃣ Bannissez les données sensibles Pas de mot de passe, carte bancaire ou clé d'API dans les logs. Jamais. Jamais ... Jamais

## Exemple de workflow dans un projet plus large :
1. **Créer une nouvelle branche pour chaque tâche** :
   ```bash
   git checkout -b feature/nouvelle-fonctionnalite
   ```
2. **Committer les changements régulièrement** :

   ```bash
   git add .
   git commit -m "Ajout de la fonctionnalité X"
   ```
3. **Mettre à jour ta branche main avant de fusionner** :

   ```bash
   git checkout main
   git pull origin main
   ```

4. **Fusionner ta branche dans main** :

   ```bash
   git merge feature/nouvelle-fonctionnalite
   ```

5. **Résoudre les conflits (si nécessaire) et pousser sur GitHub** :

   ```bash
    git push origin main
   ```

6. **Supprimer les branches locales et distantes après fusion** :

   ```bash
    git branch -d feature/nouvelle-fonctionnalite
    git push origin --delete feature/nouvelle-fonctionnalite
   ```

# Understand Spark Workflow:

Write Scala code → Compile (sbt) → Package → Run (spark-submit).

Understand spark-submit spark-submit is the tool used to run Spark applications on a cluster or a standalone environment. It handles:

Deploying your JAR file. Configuring Spark runtime options (e.g., memory, cores, environment variables). Running your application.

# a sample exemple with code:

1. Write the Scala Code
Write your Spark application in Scala. For example, a program that reads data and performs some transformations:
   ```scala
import org.apache.spark.sql.SparkSession

object SparkFileAnalyzer {
  def main(args: Array[String]): Unit = {
    // Step 1: Create SparkSession
    val spark = SparkSession.builder()
      .appName("File Analyzer")
      .master("local[*]") // Use all cores locally
      .getOrCreate()

    // Step 2: Read data
    val inputPath = args(0) // Pass input path as argument
    val data = spark.read.text(inputPath)

    // Step 3: Process data
    val wordCounts = data
      .flatMap(row => row.getString(0).split(" ")) // Split lines into words
      .groupBy("value") // Group by word
      .count()          // Count occurrences

    // Step 4: Write result
    val outputPath = args(1) // Pass output path as argument
    wordCounts.write.csv(outputPath)

    // Stop SparkSession
    spark.stop()
  }
}
   ```
2. Compile and Build (sbt)
Create an sbt project structure:
   ```css
project/
    build.properties
src/
    main/
        scala/
            SparkFileAnalyzer.scala
build.sbt
   ```
Write build.sbt:
   ```sbt
name := "sparkfileanalyzer"

version := "0.1"

scalaVersion := "2.12.15"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "3.3.2",
  "org.apache.spark" %% "spark-sql" % "3.3.2"
)
   ```
Compile and package:
Run the following commands in the project directory:

   ```bash
sbt clean compile
sbt package
   ```
This generates a JAR file (e.g., target/scala-2.12/sparkfileanalyzer_2.12-0.1.jar).

3. Run the Application (spark-submit)
Run your JAR file using spark-submit. For example:

   ```bash
/opt/spark/bin/spark-submit \
    --class SparkFileAnalyzer \
    --master local[*] \
    /path/to/target/scala-2.12/sparkfileanalyzer_2.12-0.1.jar \
    /path/to/input.txt /path/to/output
       ```
What Happens Here:
--class SparkFileAnalyzer: Specifies the main class to execute.
--master local[*]: Runs locally using all available CPU cores.
JAR Path: Deploys your compiled code (JAR) to Spark.
Arguments: /path/to/input.txt and /path/to/output are passed to your application as arguments.
4. What Happens During Execution
Step 1: Spark initializes a session using your configuration (local[*] here).
Step 2: Spark reads the input data (e.g., a text file).
Step 3: The data is processed (split into words, counted, etc.).
Step 4: The results are saved to the output directory.
For example:

Input (input.txt):
   ```
hello world
hello Spark
```
Output (CSV files in /path/to/output):

```
hello, 2
world, 1
Spark, 1
```
