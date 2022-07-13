FROM adoptopenjdk/openjdk11
EXPOSE 8091

ADD /target/blog-api-0.0.1-SNAPSHOT.jar blog-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","blog-api-0.0.1-SNAPSHOT.jar"]
