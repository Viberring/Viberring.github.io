---
title: install tomcat
description: install tomcat and deploy web app
date: 2021-05-10 15:40:09
tags:
categories:
- tomcat
---

# Tomcat

    Tomcat is a web container implements Java Servlet

## Download
    [official](https://tomcat.apache.org/download-10.cgi)

## ENV
- CATALINA_HOME
- CATALINE_BASE
```java
export CATALINA_HOME="/path/to/tomcat"
```

## Directory
- /bin
- /conf
- /logs
- /webapps

## Problems

I can't deploy sprintboot war under tomcat-10
but it works under tomcat-9

[link](https://stackoverflow.com/questions/60699994/spring-boot-application-is-not-starting-in-external-tomcat-tried-both-in-tomcat)


## Practice 
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/tomcat-jerry-demo)


### Ref
[doc](https://tomcat.apache.org/tomcat-10.0-doc/index.html)


