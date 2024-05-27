FROM amazoncorretto:21 AS builder
WORKDIR /app
COPY gradle gradle
COPY build.gradle.kts build.gradle.kts
COPY gradlew gradlew
COPY settings.gradle.kts settings.gradle.kts
RUN ./gradlew check --dry-run
COPY src src
RUN ./gradlew clean assemble

FROM amazoncorretto:21
ARG APP_ENV
ENV APP_ENV=$APP_ENV
WORKDIR /app
COPY --from=builder /app/build/libs/resource-management-service-0.0.1.jar app.jar
CMD java -jar app.jar