FROM amazoncorretto:21-alpine

WORKDIR /app

COPY target/demo-oproduct-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

RUN apk add --no-cache iputils

ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar"]
