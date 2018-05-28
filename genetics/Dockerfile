FROM maven:3-jdk-10 AS builder

WORKDIR /genetics
COPY pom.xml /genetics/pom.xml
COPY src /genetics/src

RUN mvn clean package

FROM openjdk:10-jre AS final

WORKDIR /genetics

COPY --from=builder /genetics/target/*jar-with-dependencies.jar /genetics/g4.jar

VOLUME ["/genetics/config", "/genetics/items"]
COPY config.json /genetics/config/config.json
COPY items-chicos /genetics/items

ENTRYPOINT ["java", "-jar", "/genetics/g4.jar"]
CMD ["-v", "-f", "/genetics/config/config.json"]

