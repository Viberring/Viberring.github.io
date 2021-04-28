---
title: false-sharing
description: what is false-sharing
date: 2021-04-22 10:00:50
tags:
- java
categories:
- iv
---


## What is False-Sharing
    
    Simple words:
        memory cache will lost its cache functionality.
        
    The most common cache line size is 64 bytes.
    False sharing is a term which applies when threads unwittingly 
    impact the performance of each other 
    while modifying independent variables sharing the same cache line.
    
    
    
### ref
[A](https://mechanical-sympathy.blogspot.com/2011/07/false-sharing.html)
[A(a)](https://mechanical-sympathy.blogspot.com/2011/08/false-sharing-java-7.html)
[B](https://en.wikipedia.org/wiki/False_sharing)
[C](http://psy-lob-saw.blogspot.com/2013/05/know-thy-java-object-memory-layout.html)
