FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/customer-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8091
CMD ["java", "-jar", "app.jar"]



#FROM maven:3.8.7-openjdk-17 AS builder
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=builder /app/target/customer-service-0.0.1-SNAPSHOT.jar app.jar
#EXPOSE 8091
#CMD ["java", "-jar", "app.jar"]