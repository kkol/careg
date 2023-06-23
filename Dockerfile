FROM openjdk:17
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://127.0.0.1:5432/crc_docker
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=admin
COPY ./target/careg-0.0.1-SNAPSHOT.jar /usr/src/careg/
WORKDIR /usr/src/careg
EXPOSE 8089
CMD ["java", "-jar", "careg-0.0.1-SNAPSHOT.jar"]