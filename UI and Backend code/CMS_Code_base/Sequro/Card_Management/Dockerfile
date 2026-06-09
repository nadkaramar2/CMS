FROM tomcat:9.0.87
#FROM adoptopenjdk/openjdk8

RUN apt-get update && apt-get install -y openjdk-8-jdk && apt-get install -y ant && apt-get clean
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

COPY target/*.war /usr/local/tomcat/webapps
COPY web.xml /usr/local/tomcat/conf
RUN apt-get update && apt-get install -y sudo
RUN apt-get install -y nano

