---
title: About Thread 
description: about thread
date: 2021-10-09 09:54:09
tags:
- thread
categories:
- iv
---

# Background
when I work with c# `async/await` which I compare with java's `completablefuture`, I found out that c# `async/await` is different cuz the implementation is not the same.


# Thread
A thread is a basic unit of CPU utilization.
Every thread has a `thread ID`, a `program counter(PC)`, 
a `regester set` and a `stack`.
Thread shares with other threads belonging to the 
same process its `code section`, `data` section and other
operating-system resources such as `open files` and `signals`.

Thread is sub-process, which exists in a process.
so we have `single-thread process` and `multi-thread
process`.

## [Process](./Process.md)

## State

### Thread in JAVA
- NEW
> created not started
- RUNNABLE
> executing in jvm but may be not in os
- BLOCKED
> waiting for monitor lock
- WAITING
> waiting without timeout  
  `Object.wait`  
  `Thread.join`  
  `LockSupport.park`
- TIMED_WAITING
> waiting with timeout  
  `Thread.sleep`  
  `Object.wait`  
  `Thread.join`  
  `LockSupport.parkNanos`  
  `LockSupport.parkUntil`
- TERMINATED
> the thread has completed execution

#### LifeCycle
```
---------------------------------
| NEW -> RUNNABLE -> TERMINATED |
------------|--------------------
            |
            v
-----------------------------------        
| BLOCKED   WAITING  TIME_WAITING |
-----------------------------------
```

### C#
ThreadState
- Running
- StopRequested
- SuspendRequested
- Background
- Unstarted
- Stopped
- WaitSleeping
- Suspended
- AbotRequested
- Aborted


### Ref
[Operating System Concepts]()
