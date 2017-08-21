RUN cname = $(docker images | grep 'sb' | awk '{print $1}') \
if [ "$cname" != "" ]; then \
docker rmi -f $cname \
fi
From java
MAINTAINER "yandong"<xxxxx@xxx.com>
VOLUME /tmp
ADD sb.jar app.jar
EXPOSE 8880
CMD java -jar app.jar --server.port=8880