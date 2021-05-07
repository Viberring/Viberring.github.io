---
title: executable jar file
description: executable jar with maven
date: 2021-05-07 16:08:27
tags:
categories:
- spring
---

# SpringBoot Executable Jar With Maven

we use maven plugin to build our springboot app package
but do you know the file structure of the jar file 
and how it works with tomcat ?

## The Project structure
kickoff
 |
 +-src
 |   +-main
 |       +-java
 |           +-com
 |               +-kickoff
 |                   +-YourClasses.java
 |       +-resources
 |           +-config
 |               +-application.yml
 |           +-META-INF
 |               +-spring.factories
 |           application.properties
 |           banner.txt
## The Jar File Structure
kickoff-0.0.1-SNAPSHOT.jar
 |
 +-BOOT-INF
 |   +-classes
 |   |  +-com
 |   |     +-kickoff
 |   |        +-YourClasses.class
 |   |  +-config
 |   |     +-application.yml
 |   |  +-application.properties
 |   |  +-banner.txt
 |   +-lib
 |   |   +-dependency1.jar
 |   |   +-dependency2.jar
 |   +-classpath.idx
 |   +-layers.idx
 +-META-INF
 |   +-maven
 |   +-MANIFEST.MF
 |   +-spring.factories
 +-org
     +-springframework
        +-boot
            +-loader
                +-<spring boot loader classes>

BOOT-INFO/classes       : Application classes
BOOT-INFO/lib           : Application dependencies
BOOT-INFO/classpath.idx : provides the ordering that jars should be added to the classpath
BOOT-INFO/layers.idx    : it allows a jar to be split into logical layers for Docker/OCI image creation

## Spring-boot-loader
spring-boot-loader modules let SprintBoot support executable jar and war files.

## Maven Plugin
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```




### Ref
[doc](https://docs.spring.io/spring-boot/docs/2.5.0-RC1/reference/htmlsingle/#executable-jar)