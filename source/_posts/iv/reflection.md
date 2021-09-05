---
title: Reflection
description: Reflection
date: 2021-09-04 07:43:24
tags:
- iv
categories:
- java
---

# Reflection
Reflection is used for observing and modifying the runtime behavior 
of class. while any programing depending on class will have the behavior 
changed. (I hope this desc is right).

## Pros
1. we don't need all classes are defined before our program runing,
while we can load classes dynamically from external env like file,
network etc.
2. at some point, we must know what object we are dealing with like 
spring we need load classes and refresh our config on the fly.

## Cons
1. reflection's performance is the problem.
2. security, cuz we can load external classes.
3. security, cuz we can change the behavior of class
like change the modifier (private).


# Practice
## Usage
How can you use reflection in java ?
the entry point is `java.lang.Class`.
### Get Object's Class
- Object.getClass()
- The .class Syntax
- Class.forName()
- TYPE Field for Primitive Type Wrappers
- Methods and Return Classes
```java
// object.getClass()
System.out.println("a".getClass());
System.out.println(Enum.A.getClass());
System.out.println(ANNO.class);
// .class syntax
System.out.println(boolean.class);
// Class.forName()
System.out.println(Class.forName("_reflection.Benchmark"));
// Type Field for Primitive Type Wrappers
System.out.println(Integer.TYPE);
```
### Get Object's Members (Method && Field && Constructor)
| API                     | List of memebers? | Inherited memebers?  | Private members |
| ------------------------|:-----------------:| --------------------:| --------------: |
| `getDeclaredField`      |       no          |         no           |       yes       |
| `getField`              |       no          |         yes          |       no        |
| `getDeclaredFields`     |       yes         |         no           |       yes       |
| `getFields`             |       yes         |         yes          |       no        |
| `getDeclaredMethod`     |       no          |         no           |       yes       |
| `getMethod`             |       no          |         yes          |       no        |
|`getDeclaredMethods`     |       yes         |         no           |       yes       |
| `getMethods`            |       yes         |         yes          |       no        |
|`getDeclaredConstructor` |       not         |         N/A          |       yes       |
| `getConstructor`        |       no          |         N/A          |       no        |
|`getDeclaredConstructors`|       yes         |         N/A          |       yes       |
| `getConstructors`       |       yes         |         N/A          |       no        |



### Get Object's modifier
- Access modifiers: `public protected private`
- Require override: `abstract`
- Static: `static`
- Value modification: `final`
- Annotation

```java
Class<?> c = Class.forName("java.util.concurrent.ConcurrentSkipListMap");
System.out.format("Class: %n %s%n%n", c.getCanonicalName());
System.out.format("Modifiers: %n %s%n%n", Modifier.toString(c.getModifiers()));

TypeVariable<?>[] tv = c.getTypeParameters();
System.out.println(Arrays.toString(tv));

Type[] intfs = c.getInterfaces();
System.out.println(Arrays.toString(intfs));
Type[] intfs0 = c.getGenericInterfaces();
System.out.println(Arrays.toString(intfs0));

Class<?> ancestor = c.getSuperclass();
System.out.println(ancestor);

Annotation[] annotations = c.getAnnotations();
System.out.println(Arrays.toString(annotations));
```

## Benchmark
```java
public class Benchmark {

    private static int N = 20000000;

    public static class R {
        public void dosomething() {

        }
    }

    public static void regular() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            R r = new R();
            r.dosomething();
        }
        System.out.println("regular spend time: " + (System.currentTimeMillis() - start));
    }

    public static void reflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            R r = R.class.getDeclaredConstructor().newInstance();
            r.dosomething();
        }
        System.out.println("reflect spend time: " + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) throws Exception {
        regular();
        reflect();
    }

}
regular spend time: 6
reflect spend time: 300
```
the test is just simple and it can not tell all.
but the output is obviously that reflection is more 
slower.

## Slow
why reflection is slow ?
More frankly, cuz every action you do should be valided.

But what is slow ? 
slow is used to be compare with another thing of that kind.

yes. it is slow than normal call. but we still can use it 
when we need it.
Premature is not good, but we need be considerable with our
code with performance and tidy at the first place.


## Implementations
There are two real cases that i know use reflection.
the CGlib and Java Dynamic Proxy.
We will dig into it too.