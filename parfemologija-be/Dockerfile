# Use an official OpenJDK runtime as a parent image
FROM openjdk:23-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven wrapper
COPY mvnw .
COPY .mvn ./.mvn

# Copy the pom.xml file
COPY pom.xml .

# Resolve project dependencies
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Specify the application's JAR file name (update this if your JAR has a different name)
ARG JAR_FILE=target/parfemologija-be-0.0.1-SNAPSHOT.jar

# Create a non-root user for security (optional but recommended)
RUN useradd -ms /bin/bash appuser
USER appuser

# Expose the port the application runs on
EXPOSE 8081

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app/target/parfemologija-be-0.0.1-SNAPSHOT.jar"]