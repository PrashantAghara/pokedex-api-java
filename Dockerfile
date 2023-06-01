FROM gradle:jdk11 as gradleimage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle clean build --no-daemon

FROM openjdk:11-jdk-slim
RUN mkdir /app
COPY --from=gradleimage /home/gradle/source/build/libs/*.jar /app/pokedex.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pokedex.jar"]