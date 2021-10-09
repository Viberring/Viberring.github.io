---
title: Functional Programming
description: Functional Programming
date: 2021-04-30 13:57:48
tags:
categories:
- java
---

# FP
functional programming is a programming paradigm.
java is not a pure fp language.
fp is a feature added to java8.

so what we can do with fp ? fp is first-class-citizen which
means we can pass/return/declare a function.
so we can `apply` a function to something and we can `combine`
a function with others.

## FP In Java
First of all, in java `Function` is a `Object`, which we should
know. so we can create a `Function object` as a normal `Object`.


### Funcions
`java.util.function.Function` is added to java8.
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}
```
this example shows a function with one argument and return a return-value
so what if we want two or more arguments, fortunately we have `BiFunction`
for two arguments but there is no arbitrary argument function in jdk.

Example
```java
public class F {
    public static void main(String[] args) {
        Function<String, Integer> f = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        };
        Integer r = f.apply("789");
    }
}
```
we create a `Function Object` with `new`, it is the same with we create
a `Object`.
But r u familiar with the format of creating a `Function Object`?
yes, we create a `anonymous object`.

### Lambda && Method-Reference
[doc](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html) says `method reference` are compact, easy-to-read 
lambda expressions for methods that already have a name.

[explain](https://stackoverflow.com/questions/24487805/lambda-expression-vs-method-reference) in stackoverflow

#### lambda expression
we can replace the `anonymous format` with `lambda format`.
```java
public static void main(String[] args) {
    Function<String, Integer> f = s -> Integer.valueOf(s);
    Integer r = f.apply("789");
}
```
so `() -> {}` is a `syntax sugar` for creating a `Function`.
#### method reference
we can also replace the `lambda` with `method reference`
```java
public static void main(String[] args) {
    Function<String, Integer> f = Integer::valueOf;
    Integer r = f.apply("789");
}
```
### functional interface
`@FunctionalInterface` note that this interface only have one method.
```java
@FunctionalInterface
public interface A {
    void a();
}
```

#### Consumer
`consumer` is a function accepts one argument but without
return value.

#### Supplier
`supplier` is a function without argument but has a
single return value.

#### Predicate
`predict` is a funtion with one argument and return a
boolean value.

```java
public class F {

    static class C {
        static void p(String arg) {
            System.out.println(">>>>>" + arg);
        }
    }

    static class S {
        static int f() {
            return 0;
        }
    }

    static class P {
        static boolean check(Integer i) {
            return i > 10;
        }
    }

    public static void consume(Consumer<String> c) {
        c.accept("KKK");
    }
    public static Integer supply(Supplier<Integer> s) {
        return s.get();
    }
    public static boolean predict(Predicate<Integer> p, Integer i) {
        return p.test(i);
    }

    public static void main(String[] args) {
        consume(C::p);
        System.out.println(supply(S::f));
        System.out.println(predict(P::check, 9));
        System.out.println(predict(P::check, 11));
    }
}
```
this is a example usage, and you can find that the `function name`
that we use is not exactly the same with the `interface`.
