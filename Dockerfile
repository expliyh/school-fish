FROM gradle:8.4.0-jdk20-jammy AS build

WORKDIR /app

COPY src src
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle

RUN gradle clean build

FROM amazoncorretto:20-alpine-full
LABEL authors="Expliyh"

WORKDIR /app

COPY --from=build /app/build/libs/school-fish.jar app.jar

EXPOSE 1145

CMD ["java", "-jar", "app.jar"]