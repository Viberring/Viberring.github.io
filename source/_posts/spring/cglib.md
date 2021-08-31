---
title: cglib 
description: cglib 
date: 2021-05-08 10:30:26
tags:
categories:
- spring
---

# What is CGLib 
    Code Generation Library
    It is used to extend JAVA classes 
    and implements interfaces at runtime
### How CGLib works
    create a proxy by subclassing.
    the proxy becomes a subclass of the target
    class. No need for interfaces.
### Notice for CGLib
    when subclass, you can not declare final methods
    or make the class final.

## AOP

## Proxy

## Java Dynamic Proxy
    Can only proxy by interface
    your target class needs to implement an interface, 
    which is then also implemented by the proxy class.


### Practice 
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/cglib-demo)

### Ref
[wiki](https://github.com/cglib/cglib/wiki)
[jdk](https://www.geekyhacker.com/2020/01/25/getting-started-with-dynamic-proxies-in-java/)