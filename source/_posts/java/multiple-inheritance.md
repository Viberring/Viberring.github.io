---
title: multiple-inheritance
description: multiple-inheritance
date: 2021-05-06 17:19:52
tags:
categories:
- java
---

# Multiple Inheritance

Multiple Inheritance means a class can inherit properties of more
than one parent class.

## Problem 

when there exist mehtods with same signature in both the super classes 
and subclass. On calling the method, the compiler cannot determine 
which class method to be called.


### In java
java is single root inheritance language.

```java
public class Shape {
    public void draw() {
        System.out.println("draw a shape");
    }
}
public class Color {
    public void draw() {
        System.out.println("draw a color");
    }
}
public class Circle extends Shape, Color {
    public static void main(String args[]) {
        Circle c = new Circle();
        c.draw();
    }
}
// compile error
```

### Diamond Problem
   GrandParent
    /     \
   /       \
Parent1   Parent2
   \       /
    \     /
     Child

### Alternative Solution

```java
public interface Shape {
    default void draw() {
        System.out.println("draw a shape");
    }
}
public interface Color {
    default void draw() {
        System.out.println("draw a color");
    }
}
class RedShape implements Shape, Color {}  // compile error
class RedShape implements Shape, Color {
    @Override
    public void draw() {
        System.out.println("draw a color shape");
    }
}
```


### Ref
[GG](https://www.geeksforgeeks.org/java-and-multiple-inheritance/)