---
title: B/N/A IO
description: B/N/A IO
date: 2021-10-01 23:23:34
tags:
- java
categories:
- iv
---

# BIO NIO AIO
It is all about io.
cuz io is always related to time-consuming,
we need to deal with this with more effecient way.

## BIO
the most familiar io `blocking io`.
with the name `blocking`, we must wait the io
operation done then we can make progress.

Synchronous blocks the current thread of 
execution until processing is complete

## NIO
`non-blocking io` which make us do not wait
for the completiion, but with a result which
indicate the state of the execution.

you check the result rather than wait the result.
i think when you check the result and it is not done
the current thread will be scheduled to do other things
then after some time another check comes again and do
the things over and over again.

## AIO
`asynchronous io`
this is aync which means they don't care abou the result
is done or not. 
when the they are exected then their job is done, then it
is you job to make sure the result is what you want.

simple to say is just run at another space.

- event-loop + (coroutine || callback)

there are some problems, js and python use eventloop to 
implemnt async.
while c++/c#/java use another approach.
why ? cuz when you use async you need the result of the 
await object, how you output the result is not the problem.

## Summary
asyncprogramming include `non-blocking io`, so i think 
there are some misunderstanding with the usage.

- async return immediately, without waiting for the I/O to 
complete.
- the completion of the I/O is later communicated to the 
application either through some action, like `callback`.


## Ref
[stackoverflow](https://stackoverflow.com/questions/25099640/non-blocking-io-vs-async-io-and-implementation-in-java)
[full understandable](http://www.programmr.com/blogs/difference-between-asynchronous-and-non-blocking)
[blog](http://blog.omega-prime.co.uk/2015/09/03/asynchronous-and-non-blocking-io/)