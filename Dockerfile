FROM gradle:jdk17 AS builder
WORKDIR /usr/app/
COPY . .
RUN gradle build --no-daemon 

FROM eclipse-temurin:17-jdk
COPY --from=builder /usr/app/build/libs/laptimes*.jar laptimes.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","laptimes.jar"]