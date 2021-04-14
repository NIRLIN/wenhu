FROM openjdk:11-jre
MAINTAINER NIRLIN
RUN echo "Asia/Shanghai" > /etc/timezone
ADD target/*.jar app.jar
# 开放端口
EXPOSE 8010
ENTRYPOINT ["java","-jar","app.jar"]
