FROM alpine:3.3
MAINTAINER Paul Fremantle (paul@fremantle.org)

RUN apk --update add openjdk8-jre
	
RUN mkdir -p /home/root/po 
ADD build/libs/POResourceMS-all.jar /home/root/po	
EXPOSE 8080
ENV REDIS_HOST "redis"
ENTRYPOINT java -jar /home/root/po/POResourceMS-all.jar
