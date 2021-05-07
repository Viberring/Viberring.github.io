---
title: default and static method 
description: default and static method in interface of java 8
date: 2021-05-06 17:46:29
tags:
categories:
- java
---

# Default method

adding new methods in the existing interfaces to backward compatible.

# Static method

 in interfaces are similar to the default methods except that 
 we cannot override these methods 
 in the classes that implements these interfaces.

 ## Example 
 ```java
public interface A {
    default void defaultMethod() {
        System.out.println(" default method ");
    }
    void say();
    static void staticMethod() {
        System.out.println(" static method ");
    }
}

public static class AImpl implements A {
    @Override
    public void say() {
        System.out.println(" A impl ");
    }
    public void staticMethod() {
        System.out.println(">>>>>>>>");
    }
}

public static void main(String[] args) {
    A a = new AImpl();
    a.defaultMethod();
    A.staticMethod();
    AImpl b = new AImpl();
    b.staticMethod();
}
 ```