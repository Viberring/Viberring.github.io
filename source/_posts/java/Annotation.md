---
title: Java Annotation
description: Java Annotation
date: 2022-05-06 16:13:25
tags:
- java
categories:
- java
---

# Annotation
Annotations is a form of metadata providing more information about program
- Information for the compiler
- Compile-time and deployment-time processing
- Runtime processing

## Basic
```java
@interface Anno {}
```
### Predefined Annotation Types
- Annotation Types Used by the Java Language
```java
@Deprecated @Override @SupressWarnings 
@SafeVarargs @FunctionalInterface @Contended
```
- Annotations That Apply to Other Annotations
1. `@Retention`
    - RetentionPolicy.SOURCE
    - RetentionPolicy.CLASS
    - RetentionPolicy.RUNTIME
2. `@Documented`
3. `@Target`
    - ElementType.ANNOTATION_TYPE can be applied to an annotation type.
    - ElementType.CONSTRUCTOR can be applied to a constructor.
    - ElementType.FIELD can be applied to a field or property.
    - ElementType.LOCAL_VARIABLE can be applied to a local variable.
    - ElementType.METHOD can be applied to a method-level annotation.
    - ElementType.PACKAGE can be applied to a package declaration.
    - ElementType.PARAMETER can be applied to the parameters of a method.
    - ElementType.TYPE can be applied to any element of a class.
4. `@Inherited`
    - the annotation type can be inherited from the super class. (This is not true by default.) 
5. `@Repeatable`
    - the marked annotation can be applied more than once to the same declaration or type use.

## Annotation Composition
In Spring you can see that `@Service` is with a `@Component`.
```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Service {
	@AliasFor(annotation = Component.class)
	String value() default "";
}
```
So this is a good example for something good
- Reduced visual complexity
- A custom annotation for hooks
- The possibility to further reduce complexity by adding more meta-information
```java
@Target(TYPE)
@Retention(RUNTIME)
@Documented
@Controller
@ResponseBody
@RequestMapping
@CrossOrigin
public @interface ApiController {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] value() default {};
}
```

### AliasFor
    we use @AliasFor to declare aliases for attributes so that we can apply them interchangeably.
    we decorate an attribute in a composed annotation with @AliasFor, it overrides the specified attribute in its meta-annotation.

### Reference
- [official doc](https://docs.oracle.com/javase/tutorial/java/annotations/index.html)
- [jenkov](https://jenkov.com/tutorials/java/annotations.html)
- [composition](https://chrysanthium.com/spring-annotation-composition#composition)