FROM openjdk:8
ADD target/foyh-webapp-api.jar foyh-webapp-api.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","foyh-webapp-api.jar"]