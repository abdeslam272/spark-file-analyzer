#!/bin/bash

# Set the project root directory 
PROJECT_DIR="/c/Abdeslam/DOCKER-WORK/spark-file-analyzer"

# Step 1: Start the container
echo "Starting Docker containers..."
docker compose up -d

# Step 2: Clear the output directory on the host machine
echo "Clearing output directory on the host..."
# Remove all files and directories inside the output folder, including hidden files
rm -rf "$PROJECT_DIR/data/output"/* "$PROJECT_DIR/data/output"/.* 2>/dev/null
rm -rf "$PROJECT_DIR/src/main/scala/target"  # Delete compiled filessrc/main/scala/target
rm -rf "$PROJECT_DIR/src/main/scala/project"  # Delete project-specific files

# Step 3: Compile the Scala project inside the container
echo "Compiling the project..."
docker exec -u 0 -it spark-master bash -c "
    cd /src/main/scala &&
    sbt package
"

# Step 4: Run the Spark application
echo "Running the Spark application..."
docker exec -u 0 -it spark-master bash -c "
    /opt/spark/bin/spark-submit \
    --class SparkFileAnalyzer \
    --master local[*] \
    /src/main/scala/target/scala-2.12/sparkfileanalyzer_2.12-0.1.jar
"

# Step 5: Copy output files from the container to the host
echo "Copying output files to host machine..."
docker cp spark-master:/data/output/ /c/Abdeslam/DOCKER-WORK/spark-file-analyzer/data/

# Step 6: Shut down the container
echo "Shutting down Docker containers..."
docker compose down

echo "All steps completed successfully!"