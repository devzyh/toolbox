FROM registry.cn-hangzhou.aliyuncs.com/devzyh-mirror/openjdk:21-jdk-slim

WORKDIR /toolbox

ARG JAR_FILE=build/libs/toolbox-*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

CMD java $JAVA_OPTS -jar app.jar