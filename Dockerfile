FROM gradle:jdk17-alpine AS builder
WORKDIR /usr/app/
COPY . .
RUN gradle build --no-daemon 

FROM eclipse-temurin:17-jdk-alpine
COPY --from=builder /usr/app/build/libs/laptimes*.jar laptimes.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","laptimes.jar"]