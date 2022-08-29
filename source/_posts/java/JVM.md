---
title: java virtual machine
description: the jvm architecture
date: 2021-04-22 14:00:50
tags:
- java
categories:
- iv
---

# Java Virtual Machine
    1. specification
    2. implementation
    3. instance

    jvm is part of jre.
    jvm provide runtime environment to run java code.


## Architecture
![Archtecture](/images/java/architecture.png)
    
    class loader -> byte code verifier -> execution engine

1. ClassLoader
    - Bootstrap class loader
    - Extension class loader
    - System/Application class loader

    JVM follows the Delegation-Hierarchy principle to load classes. System class loader delegate load request to extension class loader and extension class loader delegate request to the bootstrap class loader. If a class found in the boot-strap path, the class is loaded otherwise request again transfers to the extension class loader and then to the system class loader. At last, if the system class loader fails to load class, then we get run-time exception java.lang.ClassNotFoundException. 

    ![Delegation-Hierarchy](/images/java/jvmclassloader.jpg)
    - Check if the class has already been loaded.
    - Typically ask the parent class loader to load the class
    - Attempt to find the class in its own class path.

    > loading class files
    - Loading
        The Class loader reads the “.class” file, generate the corresponding binary data and save it in the method area. For each “.class” file, JVM stores the following information in the method area. 
    - Linking
        Performs verification, preparation, and (optionally) resolution. 
    - Initialization



2. Method Area

    > stores class structure 
    - metadata
    - the constant runtime pool
    - the code for method

3. Heap

    > store all Objects and shared across threads
    - instance
    - arrays

4. Stack

    > thread isolation
    
5. Pc Registers

    > store the address of the machine instruction 
    for context switch
    - currently executing instruction
    - thread isolation

6. Native Method Stacks

    > store the instruction of native code depends
    on the native library

7. Execution Engine
8. Native Method interface
9. Native Method Libraries

## Heap
![Heap Model](/images/java/heap.png)

### Heap Part

    The JVM heap is physically divided into two parts (or generations): nursery (or young space/young generation) and old space (or old generation).

    The nursery is divided into three parts – Eden Memory and two Survivor Memory spaces.

#### Important points about the nursery space:

- Most of the newly created objects are located in the Eden Memory space
- When Eden space is filled with objects, Minor GC is performed and all the survivor objects are moved to one of the survivor spaces
Minor GC also checks the survivor objects and moves them to the other survivor space. So at a time, one of the survivor space is always empty
- Objects that have survived many cycles of GC, are moved to the old generation memory space. Usually, it is done by setting a threshold for the age of the nursery objects before they become eligible to promote to the old generation
    
When the old generation becomes full, garbage is collected there and the process is called as old collection. Old generation memory contains the objects that are long-lived and survived after many rounds of Minor GC. Usually, garbage collection is performed in Old generation memory when it’s full. Old generation garbage collection is called as Major GC and usually takes longer. The reasoning behind a nursery is that most objects are temporary and short-lived. A young collection is designed to be swift at finding newly allocated objects that are still alive and moving them away from the nursery. Typically, a young collection frees a given amount of memory much faster than an old collection or a garbage collection of a single-generational heap (a heap without a nursery).

### PermGen
    PermGen (Permanent Generation) is a special heap space separated from the main memory heap.

    The JVM keeps track of loaded class metadata in the PermGen. Additionally, 
    the JVM stores all the static content in this memory section. 
    This includes all the static methods, primitive variables, 
    and references to the static objects.

    Furthermore, it contains data about bytecode, names, and JIT information. 
    Before Java 7, the String Pool was also part of this memory. 
    The disadvantages of the fixed pool size are listed in our write-up.

    However, we can change the default size with the JVM options:

    -XX:PermSize=[size] is the initial or minimum size of the PermGen space
    -XX:MaxPermSize=[size] is the maximum size


    With its limited memory size, PermGen is involved in generating the famous OutOfMemoryError.
    Simply put, the class loaders weren't garbage collected properly and, 
    as a result, generated a memory leak.


###  Metaspace

Simply put, Metaspace is a new memory space – starting from the Java 8 version; 
it has replaced the older PermGen memory space. The most significant difference is how it handles memory allocation.

Specifically, this native memory region grows automatically by default.


We also have new flags to tune the memory:

MetaspaceSize and MaxMetaspaceSize – we can set the Metaspace upper bounds.
MinMetaspaceFreeRatio – is the minimum percentage of class metadata capacity free after garbage collection
MaxMetaspaceFreeRatio – is the maximum percentage of class metadata capacity free after a garbage collection 
to avoid a reduction in the amount of space

Therefore, with this improvement, JVM reduces the chance to get the OutOfMemory error.


## Garbage Collection
Garbage Collection is the process of freeing space in the heap for the allocation of new objects. 
### Process
    - Marking
    - Normal Deletion
    - Deletion with compacting
### Terms
1. Unreachable objects : An object is said to be unreachable iff it doesn’t contain any reference to it.
 Also note that objects which are part of island of isolation are also unreachable.
2. Eligibility for garbage collection : An object is said to be eligible for GC(garbage collection) if it is unreachable

### Implementation
#### Serial Garbage Collector
works with a single thread.
this GC implementation freezes all application threads when it runs
> java -XX:+UseSerialGC -jar Application.java
#### Parallel Garbage Collector
this uses multiple threads for managing heap space.
but it also freezes other application threads while gc.

    -XX:ParallelGCThreads=<N>
    -XX:MaxGCPauseMillis=<N>
    -XX:GCTimeRatio=<N>
    -Xmx<N>
> java -XX:+UseParallelGC -jar Application.java
#### CMS Garbage Collector
The Concurrent Mark Sweep (CMS) implementation uses multiple garbage collector threads for garbage collection. 
> java -XX:+UseParNewGC -jar Application.java
    As of Java 9, the CMS garbage collector has been deprecated.
#### G1 Garbage Collector
G1 (Garbage First) Garbage Collector is designed for applications running on multi-processor machines with large memory space.

G1 collector partitions the heap into a set of equal-sized heap regions, each a contiguous range of virtual memory. When performing garbage collections, G1 shows a concurrent global marking phase (i.e. phase 1 known as Marking) to determine the liveness of objects throughout the heap.
> java -XX:+UseG1GC -jar Application.java


### Ref
- [jvm architecture](https://www.guru99.com/java-virtual-machine-jvm.html)

- [jvm](https://www.betsol.com/blog/java-memory-management-for-java-virtual-machine-jvm/)
- [another jvm](https://www.freecodecamp.org/news/jvm-tutorial-java-virtual-machine-architecture-explained-for-beginners/)

- [Gc](https://www.geeksforgeeks.org/garbage-collection-java/)
- [another gc](https://www.freecodecamp.org/news/garbage-collection-in-java-what-is-gc-and-how-it-works-in-the-jvm/)
- [Gc Basic oracle](https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html)

