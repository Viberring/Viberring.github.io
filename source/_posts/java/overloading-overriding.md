---
title: overloading and overriding
description: overloading and overriding
date: 2021-05-06 19:43:53
tags:
categories:
- java
---


# Overloading and Overriding


## Overloading
two or more methods in the same class have the same name but different parameters

this is ok
```java
public void print() {

}
public void print(int i) {

}
public final void print(String s) {

}
public void print(short i) throws Exception {

}
```

this is not ok
```java
public <T extends Serializable> void printElement(T t) {
    System.out.println("Signature is: printElement(T)");
}

public void printElement(Serializable o) {
    System.out.println("Signature is: printElement(Serializable)");
}
```

```java
public Number sum(Integer term1, Integer term2) {
    System.out.println("Adding integers");
    return term1 + term2;
}

public Number sum(Number term1, Number term2) {
    System.out.println("Adding numbers");
    return term1.doubleValue() + term2.doubleValue();
}

public Number sum(Object term1, Object term2) {
    System.out.println("Adding objects");
    return term1.hashCode() + term2.hashCode();
}

public static void main(String[] args) {
    T t = new T();
    System.out.println(t.sum(Integer.valueOf(2), Integer.valueOf(2)));
    System.out.println(t.sum(2, 2));
    System.out.println(t.sum(1, 0x1));
    ///////////////////////////////
    System.out.println(t.sum(2.0d, 3.0d));
    System.out.println(t.sum(Float.valueOf(2), Float.valueOf(3)));
    ///////////////////////////////
    System.out.println(t.sum(2, "S"));
}
```

```java
public Number sum(Object term1, Object term2) {
    System.out.println("Adding objects");
    return term1.hashCode() + term2.hashCode();
}

public Number sum(Object term1, Object... term2) {
    System.out.println("Adding variable arguments: " + term2.length);
    int result = term1.hashCode();
    for (Object o : term2) {
        result += o.hashCode();
    }
    return result;
}
```

## Overriding
happend in Hierarchy relation, override parent method