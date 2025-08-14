FROM amazoncorretto:21-alpine-jdk AS builder

WORKDIR /app

COPY .. .

RUN chmod -x ./gradlew

RUN ./gradlew clean build -x test

FROM amazoncorretto:8-alpine3.21-jre AS runtime

WORKDIR /app

ARG APPLICATION_NAME
ARG SERVER_PORT

COPY --from=builder /app/build/libs/${APPLICATION_NAME}-0.0.1.jar /build/$APPLICATION_NAME.jar

EXPOSE $SERVER_PORT

CMD ["java", "-jar", "/build/${APPLICATION_NAME}.jar"]