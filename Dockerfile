FROM bellsoft/liberica-openjdk-debian:17.0.12-cds

LABEL maintainer="devzyh"

WORKDIR /toolbox

ARG JAR_FILE=build/libs/toolbox-*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENV TZ=Asia/Shanghai LANG=C.UTF-8 LC_ALL=C.UTF-8 JAVA_OPTS="-Xms128m -Xmx256m -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT java $JAVA_OPTS -jar app.jar