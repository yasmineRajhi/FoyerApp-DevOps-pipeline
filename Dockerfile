FROM openjdk:17-jdk-alpine
LABEL authors="Yasmine Rajhi"
EXPOSE 8080
RUN apk add --no-cache curl

# Optional: if you want to pull JAR from Nexus directly
ARG JAR_FILE_URL="http://192.168.186.131:8081/repository/maven-releases/tn/esprit/tpfoyer/0.0.1/foyer-project-0.0.1.jar"
RUN curl -u "admin:admin" -L $JAR_FILE_URL -o /foyer-project-0.0.1.jar

ENTRYPOINT ["java", "-jar", "/foyer-project-0.0.1.jar"]
