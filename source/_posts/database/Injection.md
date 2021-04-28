---
title: SQL Injection
description: sql injection 
date: 2021-04-22 23:55:57
tags:
categories:
- database
---

# What is Sql Injection

sql injection is a technique where additional sql is injected
by people as part of user-input which can lead to harmful
consequence.

### Sql Injection Consequence
- read sensitive data
- update database
- slowdown databse

### How Sql Injection Happen

```java
" select * from user where username = '" + username + "'";
```
In server side, we always construct sql in this way.

However, the username can be  `a` or `a' or '1'='1`
this result to 

```sql
select * from user where username = 'a'
```
or
```sql
select * from user where username = 'a' or '1'='1'
```


### Statement and PreparesStatement
- statement
    execute directly ?
- preparedStatement
    has four steps
    1. Parsing and Normalization Phase
    2. Compilation Phase
    3. Query Optimization Plan
    4. Cache
    5. Execution Phase


### Summary
1. Need to know more about database 
2. Do a db
3. [Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/sql-injection-demo)

#### Ref
[A: explaination](https://javabypatel.blogspot.com/2015/09/how-prepared-statement-in-java-prevents-sql-injection.html)