FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle clean build

FROM openjdk:11-jre-slim
COPY --from=gradleimage /home/gradle/source/build/libs/pokedex-api-java-1.0.0.jar /app/pokedex-api.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "pokedex-api.jar"]