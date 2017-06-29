FROM openjdk:8-jre
COPY . ./build/libs/cfi.jar
WORKDIR ~/
EXPOSE 8080
CMD ["java", "-jar", "./build/libs/cfi.jar"]