---
title: spring with docker
description: use docker with spring service
date: 2022-05-28 16:47:25
tags:
- docker
categories:
- docker
---

# Spring With Docker
## Background
I am using docker to run springboot application and I need
connect to mongodb which is running by docker too.


## Using docker command line
1. we need config our application 
springboot application.properties (or `yml` file)
```yml
spring.data.mongodb.uri=${MONGO_URL:mongodb://localhost:27017/dev}
```
2. we need the jar package
```sh
mvn clean install spring-boot:run
```
with this command, we package and run the application.
```sh
mvn clean & mvn install
```
3. then we build Docker image
we need locate the base directory with DockerFile
```docker
FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-slim
ENV PORT 8080
COPY target/*.jar /opt/app.jar
WORKDIR /opt
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
```
```sh
docker build -t xxx-java .
```
4. then we can start Docker container
```sh
docker network create xxxNet
```
```sh
docker run --name=mongooo --rm --network=xxxNet mongo
```
```sh
docker run --name=xxx-java --rm
  --network=xxxNet -p 8080:8080 
  -e MONGO_URL=mongodb://mongo:27017/foo knote-java:1.0.0
```
we first start `mongo` then we start `java`.
we need these two docker service in the same `network` which
is `xxxNet`
we also hava `MONGO_URL` which will replace the default url

## Using docker-compose