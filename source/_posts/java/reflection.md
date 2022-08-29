---
title: reflection
description: reflection
date: 2021-05-10 14:16:22
tags:
categories:
- java
---

# Reflection
    introspection + manipulation
    
    introspection is a method to use code to inspect other 
    code in the same system.
    manipulation is make modification at runtime.

## Basic
- explore intrinsic class details at runtime
- dynamically create new class instances (without the explicit usage of the new operator)
- dynamically invoke methods
- introspect annotations

```java
Method[] methods = String.class.getMethods();
Filed[] fields = String.class.getFields();
Constructor<String> constructor = String.class.getConstructor(String.class);
method.invoke(obj, args);
Annotation annotation = A.class.getAnnotation(Annotation.class);
```
### Working with generic
```java
public class ParameterizedTypeExample {
    private List< String > strings;
 
    public List< String > getStrings() {
        return strings;
    }
}
final Type type = ParameterizedTypeExample.class
    .getDeclaredField( "strings" ).getGenericType();
 
if( type instanceof ParameterizedType ) {
    final ParameterizedType parameterizedType = ( ParameterizedType )type;
    for( final Type typeArgument: parameterizedType.getActualTypeArguments() ) {
        System.out.println( typeArgument );
    }
}
```

### Ref
[oracle](https://www.oracle.com/technical-resources/articles/java/javareflection.html)

[stack](https://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful)