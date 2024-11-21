FROM openjdk:17
EXPOSE 5000
ADD target/admin-service.jar admin-service.jar
ENTRYPOINT ["java", "-jar", "/admin-service.jar"]