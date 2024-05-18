FROM gradle:jdk17 AS builder
WORKDIR /usr/app/
COPY . .
RUN gradle build --no-daemon 

FROM eclipse-temurin:17-jdk
COPY --from=builder /usr/app/build/libs/laptimes*.jar laptimes.jar
EXPOSE 9000
EXPOSE 9996
EXPOSE 20777

LABEL org.opencontainers.image.author = contact@benjamindanlos.fr
LABEL org.opencontainers.image.source = "https://github.com/BenjaminDls/laptimes"
ENTRYPOINT ["java","-jar","laptimes.jar"]