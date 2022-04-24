---
title: serialization in java
description: serialization in java
date: 2021-12-16 20:25:28
tags:
categories:
- java
---

# Serialization && Deserialization
we can `serialize` our object to bytecode and transport it 
via file or internet then we can dynamically `deserialization`
it to the jvm to create the object we want.

## Serializable interface
`Serializable` interface is a mark-interface like `Clonable`
we must implemnt `Serializable` interface then we can 
serialize the object.

## static and transient fields
in serialization, static fields and transient fields will
not be serialized

## What is the serialVersionUID
we will change the structure of a `Class` sometime.
then the problem is what we gonna do with previous 
`Serialized Object`. if we did not provide the serialVersionUID
then the jvm will do so, which makes the problem that 
we cannot deserialize the `Object` any more if we've change it
and it will throw `InvalidClassException`.
We provide the `serialVersionUID` means we make sure the object
can be changed and will not get `InvalidClassException`.

## Custom the serializaton logic
the default serialization of the object does not call the 
constructor.
so we need some extra efforts.
`writeObject` method and `readObject` method are here.

## Use serialization with caution
in effective java, the author recommend to do not use
serializaiton with the problems it will cause.
we can find better alternatives.





### Ref
[example](https://www.programmingmitra.com/2019/08/what-is-serialization-everything-about-java-serialization-explained-with-example.html)