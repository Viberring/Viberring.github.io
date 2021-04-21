---
title: UUID && Snowflake
description: Why UUID and Snowflake ?
date: 2021-04-20 18:07:54
tags:
- iv
categories:
- java
---

# Why 

    When it comes to ID generator, the DB AUTO_INCREMENT strategy hit me first.
    But with the data growing, we need shard data and transfer data, so here comes
    the problem.
        - id conflict
          when we shard data, we will insert data into many dbs the same time 
          and every single row of data should have a unique id.
          And if we use auto_incremnt then the id will duplicate.
          
# Prerequisite

    - ID is sortable
    - ID should be 64 bits (for smaller indexes, and better storage in systems like Redis)
    - Minimize changes
    
# Solutions

## Inside Web Application => UUID
### Pros:
    1. each thread generates IDs independently, minimizing points
       of failure and contention for ID generation
    2. use timestamp as the first component of the ID, the IDs remain time-sortable
### Cons:
    1. need more storage space to make reasonable uniqueness guarantees
    2. some UUID types are completely random and have no natural sort

## ID Generation Service (Snowflake)
### Pros:
    1. Snowflake IDs are 64-bits
    2. remain sortable
    3. Distributed system that can survive nodes dying
### Cons:
    1. introduce additional complexity and more `changes` into system
    2. snowflake algorithm strongly depends on machine clock. If the clock is dialed back on the machine, it will result in duplicate calls or the service will be unavailable. 
    
## Persistence Layer Generated IDs
### Pros:
    1. alleviate the problem of having to generate unique IDs in application code
### Cons:
    1. performance problems
    2. the generated id need a roudtrip from db to code
    
## DB Ticket Servers
### Pros:
    1. Isolate from app and have pretty predictable scaling factors
### Cons:
    1. Can eventually become a write bottleneck
    2. If using a single DB, becomes single point of failure. 
    If using multiple DBs, can no longer guarantee that they are sortable over time.  
    

# UUID (8byte == 128bits)
    UUID (aka Univeral Unique Identifiers) is a 128-bits number.
    A UUID is a synthetic ID comprising of several distinct parts, 
    such as time, MAC address, or an MD5 hashed namespace.
    EX: `ccb717a7-78d6-4e04-a2b7-739f02edfb08`
    
    > UUID can be generated in isolation and still gurantee uniqueness 
    in a distributed environment.
    
    > UUIDs require extra storage and may negatively affect your querying performance

# Snowflake
    ID server for generating unique IDs for your distributed infrastructure.
    It can be single server or cluster of servers to gnerate numbers.
    The generated ids were composed of:
        1. Bit 1 occupies 1 bit, and its value is always 0, 
           which can be regarded as the symbol bit is not used.
        2. The first 41 bits are timestamps, and the 41-bit bits represent 2 ^ 41 numbers, 
           each representing milliseconds. Then the time available for snowflake 
           algorithm is (1L < 41)/(1000L360024 * 365)= 69 years.
        3. The 10-bit bit bit in the middle represents the number of machines, 
           i.e. 2 ^ 10 = 1024 machines, but in general we do not deploy such machines. 
           If we need IDC (Internet Data Center), we can also give IDC 10-bit 5-bit 
           and work machine 5-bit. In this way, 32 IDCs can be represented, 
           each IDC can have 32 machines, specific partition 
           can be defined according to their own needs.
        4. The last 12-bit bit is a self-increasing sequence, 
           which can represent 2 ^ 12 = 4096 numbers.

    - Such partitioning is equivalent to generating 4096 orderly, 
      non-repetitive IDs on a machine in a data center in a millisecond.
    - But we certainly have more than one IDC and machine, 
      so the number of ordered IDs that can be generated in milliseconds is doubled.

## Code
[snowflake](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/blob/master/java/IVQ/Snow.java)
[better snowflake](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/blob/master/java/IVQ/Snowflake.java)


#### reference from
[snowflake implementation](https://programmer.group/snowflake-algorithm-improved-version-snowflake.html)
[snowflkae explain](https://www.fatalerrors.org/a/snowflake-algorithm-and-uuid-generation-strategy.html)
[Ins with PL/PGSQL](https://instagram-engineering.com/sharding-ids-at-instagram-1cf5a71e5a5c)
[uuid and snowflake](https://betterprogramming.pub/uuid-generation-snowflake-identifiers-unique-2aed8b1771bc)
[ticket db server](https://code.flickr.net/2010/02/08/ticket-servers-distributed-unique-primary-keys-on-the-cheap/)
[leaf](https://github.com/Meituan-Dianping/Leaf)
[uid generator](https://github.com/baidu/uid-generator)
[go snowflake](https://github.com/sony/sonyflake/blob/master/example/sonyflake_server.go)
[js uuid](https://github.com/uuidjs/uuid)
