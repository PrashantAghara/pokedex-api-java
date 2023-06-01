FROM gradle:jdk11 as gradleimage
COPY . /home/gradle/source
WORKDIR /home/gradle/source
RUN gradle bootJar

FROM openjdk:11-jre-slim
COPY --from=gradleimage /home/gradle/source/build/libs ./my-test
RUN ls -l ./my-test
COPY --from=gradleimage /home/gradle/source/build/libs/*.jar /app/pokedex-api.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "pokedex-api.jar"]