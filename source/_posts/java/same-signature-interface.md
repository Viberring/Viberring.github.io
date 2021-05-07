---
title: same-signature-interface
description: same-signature-interface with some tricks
date: 2021-05-02 13:31:59
tags:
categories:
---

# Pre
Why is the return type of method not included in the method-signature?

This is done because the compiler would not 
be able to figure out the overload in all contexts.

For example, if you call
`String x = method1("aaa");`
the compiler knows that you are looking for the second overload. 
However, if you call
`method1("aaa");`
like this, the compiler has no idea which one of the two methods you wanted to invoke, 
because it is OK to call a method returning String and discard the result. 
To avoid ambiguities like this, 
Java prohibits overloads that differ solely on the return type.

`signature is comprised of the name and parameter types`

# Background

In SpringBoot, there are some code

```java
@Deprecated
public interface Bootstrapper {
    default void initializa(BootstrapRegistry registry) {
        intitialize(registry);
    }
    @Deprecated
    void intitialize(BootstrapRegistry registry);
}

@FunctionalInterface
public interface BootstrapRegistryInitializer {
    void initialize(BootstrapRegistry registry);
}

@SuppressWarnings("deprecation")
private List<BootstrapRegistryInitializer> getBootstrapRegistryInitializersFromSpringFactories() {
    ArrayList<BootstrapRegistryInitializer> initializers = new ArrayList<>();
    getSpringFactoriesInstances(Bootstrapper.class).stream()
            .map((bootstrapper) -> ((BootstrapRegistryInitializer) bootstrapper::initialize))
            .forEach(initializers::add);
    initializers.addAll(getSpringFactoriesInstances(BootstrapRegistryInitializer.class));
    return initializers;
}
```

## Problem
how `Bootstrapper` can be converted to `BootstrapRegistryInitializer` ?

## Interface
An interface is a reference type, similar to a class, that can contain 
only `constants, method signatures, default methods, static methods, 
and nested types`. Method bodies exist only for 
`default methods and static methods`. 
Interfaces cannot be instantiated—they can only be 
implemented by classes or extended by other interfaces.

Interface will introduce `type hierarchy` and 
With `type hierarchy` we can use polymorphic.

### Basic Usage
```java
public interface Human {
    String say();
}
public class Man implements Human {
    @Override
    public String say() {
        return "man is here";
    }
}
```
### Advance Usage 
interface can extends multiple interfaces
```java
public interface Gift { void present(); }
public interface Guest { void present(); }
public interface Presentable extends Gift, Guest {}
public class Present implements Presentable {
    @Override
    public void present() {
        System.out.println("Heee JOhnny!!!");
    }
}
```
The example above works.
However,
```java
interface Gift  { void present(); }
interface Guest { boolean present(); }
interface Presentable extends Gift, Guest { } 
```
This will have compile error `attempting to use incompatible return type`
```java
public class Present implements Gift, Guest { 
    @Override
    public void present() {
        System.out.println("Heee JOhnny!!!");
    }
}
```
This will have compile error with 'present()' is already defined
because method signature does not include return type in java.

## To The Problem

```java
map((bootstrapper) -> ((BootstrapRegistryInitializer) bootstrapper::initialize))
```
This code use method reference `bootstrapper::initialize`
which is equal to `bootstrapper -> bootstrapper.initialize()`
```java
public interface A {
    default void initialize() {
        this.intitialize();
    }
    void intitialize();
}

public interface B {
    void initialize();
}

public static void main(String[] args) {
    List<A> list = new ArrayList<>();
    list.stream().map(a -> ((B)a::initialize)).forEach(System.out::println);
}
```
because `map((a) -> {})` accept a `Function`.
`A` and `B` have the same method `without input` and return `void`


### Ref 
[signature](https://stackoverflow.com/questions/13314316/why-is-the-return-type-of-method-not-included-in-the-method-signature)
[interface](https://stackoverflow.com/questions/2801878/implementing-two-interfaces-in-a-class-with-same-method-which-interface-method)