---
title: aop
description: aop
date: 2021-05-10 10:19:47
tags:
categories:
- spring
---

## AOP
    aspect-oriented programming
    In my point, it use proxy to do something without
    modify the existing code. But use it in a more fancy 
    and simple way along with some more concept.

### Spring AOP

#### Concept
- Aspect: A modularization of a concern that cut across multiple classes.
- JoinPoint: method execution.
- Advice: Action taken by an aspect at a particular join point.
- Pointcut: A predicate that matches join points.

#### Advice
- Before advice
- After returning advice
- After throwing advice
- After finally advice
- Around advice

#### Diff
Aspect vs SpringAop



|Spring AOP	 | AspectJ |

Implemented in pure Java | Implemented using extensions of Java programming language

No need for separate compilation process | Needs AspectJ compiler (ajc) unless LTW is set up

Only runtime weaving is available |	Runtime weaving is not available. Supports compile-time, post-compile, and load-time Weaving

Less Powerful – only supports method level weaving | More Powerful – can weave fields, methods, constructors, static initializers, final class/methods, etc…

Can only be implemented on beans managed by Spring container | Can be implemented on all domain objects
Supports only method execution pointcuts | Support all pointcuts

Proxies are created of targeted objects, and aspects are applied on these proxies |	Aspects are weaved directly into code before application is executed (before runtime)

Much slower than AspectJ | Better Performance
Easy to learn and apply	Comparatively more complicated than Spring AOP


### Usage 

```java
@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

}

@Aspect
@Component
public class CusAspect {

    @Before("execution(* list(..))")
    public void before(JoinPoint joinPoint) {
        System.out.println(" >>> before join point execute ");
        System.out.println(joinPoint.getSignature().getName());
    }

}
```


### Practice 
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/aop-demo)

### Ref
[wiki](https://en.wikipedia.org/wiki/Aspect-oriented_programming)

[baeldung](https://www.baeldung.com/spring-aop-vs-aspectj#:~:text=Spring%20AOP%20is%20a%20proxy%2Dbased%20framework%2C%20so%20there%20is,the%20time%20of%20application%20startup.&text=On%20the%20other%20hand%2C%20AspectJ%20weaves%20the%20aspects%20into%20the,runtime%20overhead%2C%20unlike%20Spring%20AOP.)