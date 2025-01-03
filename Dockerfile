FROM openjdk:17-alpine
ARG JAVA_OPTS
ENV JAVA_OPTS=${JAVA_OPTS}
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
COPY target/eventorganizer-0.0.1-SNAPSHOT.jar eventorganizer.jar
EXPOSE 8003
ENTRYPOINT ["java", "${JAVA_OPTS}", "-Djava.security.egd=file:/dev/./urandom", "-jar", "eventorganizer.jar"]
