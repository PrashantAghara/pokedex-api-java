FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle clean build

FROM openjdk:11-jre-slim
USER root
RUN ls -l /home/gradle/source/build/libs/
COPY --from=gradleimage /home/gradle/source/build/libs/*.jar /app/pokedex-api.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "pokedex-api.jar"]