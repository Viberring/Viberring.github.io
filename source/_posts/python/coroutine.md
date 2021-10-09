---
title: Coroutine in python
description: coroutine
date: 2021-10-09 09:54:09
tags:
- coroutine
categories:
- coroutine
---
# Coroutine

## What is coroutine
In general coroutine is a function, but is differenct with
the normal funtion which is execute top-down.
while coroutine can be `suspend` and `resume`.
In other words, coroutine can be controled by code.

## How coroutine works
coroutine can be implemented with difference methods.
but the main work is to maintain the infomation of the
coroutine. so we can create a new stack to track the state
of the coroutine.

## coroutine vs. thread
1. thread is scheduled by os, which can not control the time.
while corotine can be controled by us, which makes the 
program more predictable.
2. thread switch is all about context-switch which have a lot
works to do, while coroutine do not need that. cuz, we know 
exactly which coroutine to resume the execution.
3. lock-free? cuz thread's behavior is determined by os 
scheduler.
4. thread need its own memeory and stuffs while coroutine's
memory manage is simpler (? or just in the call-stack ?)

## coroutine in Python 

### Iterators
iterator is kinda of design pattern, you don't know what 
the object is, but you just iterate it, cuz it is iterable.
### Generators
generator can be used just like iterator with different 
implementation and desgin purpose.
generator is more like time, you can only run forward but 
it have limits.
### Coroutines
coroutine can suspend and resume the execution.
like generator, but coroutine can receive and send value



## Practice

### yield

### yield from

### async/await

## Go && C++

### Goroutine

### C++





### 
1. I want to know how `yield` works and how `coroutine` replace `callback`.
2. I want to know how `event loop` based framework deal with `child coroutine`
with multiple level



## Ref
[c implement](http://www.vishalchovatiya.com/coroutine-in-c-language/)