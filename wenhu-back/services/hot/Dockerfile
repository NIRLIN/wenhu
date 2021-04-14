FROM openjdk:11-jre
MAINTAINER NIRLIN
RUN echo "Asia/Shanghai" > /etc/timezone
ADD target/*.jar app.jar
# 开放端口
EXPOSE 8020
ENTRYPOINT ["java","-jar","app.jar"]
