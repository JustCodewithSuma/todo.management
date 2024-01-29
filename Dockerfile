FROM openjdk:19
EXPOSE 8080
ADD build/libs/todo.management-0.0.1-SNAPSHOT.jar todo.management.jar
ENTRYPOINT ["java", "-jar", "todo.management.jar"]