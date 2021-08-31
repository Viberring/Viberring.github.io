---
title: value or reference
date: 2020-10-28 12:50:12
description: reference or value
tags:
- Java
categories:
- Java
---

# Is Java "pass-by-reference" or "pass-by-value" ?
[stack overflow](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value)

> Java is always *pass-by-value*
In my point, I think java pass *the value of the reference* (aka the memory location) around code.


```java
public class ValusVsReference {

    public static class Dog {
        private String name;
        private String color;
        public Dog(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public String getColor() { return this.color; }
        public void setColor(String color) { this.color = color; }
    }
    
    public static void test() {
        Dog samoye = new Dog("Timo");
        System.out.println(" dog color before fool: " + samoye.getColor());
        fool_around(samoye);
        System.out.println(" dog color after fool: " + samoye.getColor());
    }

    public static void test1() {
        Dog samoye = new Dog("Timo");
        System.out.println(" dog color before fool: " + samoye.getColor());
        another_fool(samoye);
        System.out.println(" dog color after fool: " + samoye.getColor());
    }    

    public static void fool_around(Dog d) {
        System.out.println("========== fool ==============");
        System.out.println(" dog name: " + d.getName());
        d.setColor("Black");
        System.out.println("========= fool end ==============");
    }

    public static void another_fool(Dog d) {
        System.out.println("========== another fool ==============");
        d = new Dog("haqi");
        System.out.println("========== fool end ==============");
    }
    
    public static void main(String[] args) {
        System.out.println("========= test0 ==============");                
        test();
        System.out.println();
        System.out.println("========= test1 ==============");        
        test1();
    }   
}
```

## What is the mean of `Dog samoye = new Dog("Timo")`

you create a Dog object with `new Dog("Time")`
then you store the location to the samoye variable.
`samoye` is a pointer to a Dog Object which stored in memory.

```java
foo_around(samoye);
```
in the above env, you pass the address which samoye point-to to
the method `foo_around`



## At the end

- How pass-by-referece works like in c/c++ ? (done)
