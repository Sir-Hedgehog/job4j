FROM maven:3.9.6-openjdk-17
RUN mkdir job4j_spring_di
WORKDIR job4j_spring_di
COPY . .
RUN mvn install
CMD ["java", "-jar", "target/spring_di_scope.jar"]