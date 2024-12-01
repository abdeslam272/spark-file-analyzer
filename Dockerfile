# Use Spark base image
FROM spark:latest

# Switch to root user for installing dependencies
USER root

# Install sbt (already in your Dockerfile)
RUN apt-get update && \
    apt-get install -y apt-transport-https curl gnupg wget && \
    wget -qO - https://scala.jfrog.io/artifactory/api/gpg/key/public | gpg --dearmor -o /usr/share/keyrings/sbt-archive-keyring.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/sbt-archive-keyring.gpg] https://scala.jfrog.io/artifactory/debian all main" > /etc/apt/sources.list.d/sbt.list && \
    apt-get update && \
    apt-get install -y sbt

# Switch back to the non-root user
USER spark