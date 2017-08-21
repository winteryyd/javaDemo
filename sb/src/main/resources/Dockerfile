From java
MAINTAINER "yandong"<xxxxx@xxx.com>
VOLUME /tmp
ADD sb.jar app.jar
RUN docker rmi -f test-sb/sb
EXPOSE 8880
CMD java -jar app.jar --server.port=8880