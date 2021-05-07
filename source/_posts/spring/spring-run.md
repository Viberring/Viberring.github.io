---
title: kickoff
description: kickoff
date: 2021-04-28 14:25:28
tags:
categories:
- spring
---

# Spring Kickoff


```java
@SpringBootApplication
public class KickoffApplication {
    public static void main(Stirng[] args) {
        SpringApplication.run(KickoffApplication.class, args);
    }
}
```

## How to start

we start a application from `SpringApplicaton.run(clazz, args)`

### What work `SpringApplication.run(clazz, args)` do
    bootstrap spring application
- create `ApplicationContext`
- register `CommandLinePropertySource`
- refresh context, loading all singleton beans
- trigger `CommandLineRunner`





## How to init





### Practice
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/kickoff)