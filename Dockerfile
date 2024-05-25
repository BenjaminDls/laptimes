FROM gradle:jdk17 AS builder
WORKDIR /usr/app/
COPY . .
RUN gradle build --no-daemon 

FROM eclipse-temurin:17-jre
WORKDIR /usr/app/
COPY --from=builder /usr/app/build/libs/laptimes*.jar laptimes.jar
COPY --from=builder /usr/app/src/main/resources/ci-scripts ci-scripts
EXPOSE 9000
EXPOSE 9996
EXPOSE 20777

LABEL org.opencontainers.image.author = contact@benjamindanlos.fr
LABEL org.opencontainers.image.source = "https://github.com/BenjaminDls/laptimes"

ENTRYPOINT ["java","-jar","/usr/app/laptimes.jar"]