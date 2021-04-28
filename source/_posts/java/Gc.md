---
title: garbage collection
description: garbage collection
date: 2021-04-22 15:00:50
tags:
- java
categories:
- iv
---

# GC


### Make object eligible for garbage collection
1. Object created inside a method 
2. Reassigning the reference variable
3. Nullifying the reference variable
4. Anonymous object

### Island of Isolation

    Object 1 references Object 2 and Object 2 references Object 1. 
    Neither Object 1 nor Object 2 is referenced by any other object. 
    That’s an island of isolation.
    
    Basically, an island of isolation is a group of objects that reference 
    each other but they are not referenced by any active object in the application. 
    Strictly speaking, even a single unreferenced object is an island of isolation too.