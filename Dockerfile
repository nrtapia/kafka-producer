# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine3.8

# The application's jar file
ARG JAR_FILE

ENV _JAVA_OPTIONS "-Xms100m -Xmx200m -Djava.awt.headless=true"

# Add Maintainer Info
LABEL maintainer="neider.tapia@gmail.com"

# Add a volume pointing to /tmp
# Create a /tmp volume to speed up second launch times of the containers,
# as this is where the embedded application container stores its exploded contents to
VOLUME /tmp

# Make port 8888 available to the world outside this container
EXPOSE 8761

# Copy the application's jar to the container
COPY ${JAR_FILE} /app/app.jar

WORKDIR /app

# Lastly we tell Java to use /dev/urandom for its random number seed to improve boot times.
# Run the jar file
ENTRYPOINT ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "/app/app.jar"]



# Command to build image
# docker build --build-arg JAR_FILE=build/libs/kafka-producer-1.0-SNAPSHOT.jar . -t ntapia/kafka-producer:1.0  --no-cache
# docker run  --name kafka-producer-instance -p 80:80 ntapia/kafka-producer:1.0