FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /home/selenium-docker
#addd the required files to run the test
ADD target/docker-resources ./
