FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle clean build

FROM openjdk:11-jdk-slim
COPY --from=gradleimage /home/gradle/source/build/libs/*.jar pokedex.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","pokedex.jar"]