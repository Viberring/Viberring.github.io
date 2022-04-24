---
title: filed injection 
description: why not field injection
date: 2021-12-02 14:25:28
tags:
categories:
- spring
---

# Spring Field Injection

## Three types
- Constructor-based DI
- Setter-based DI
- Field-based DI

### Why do not use Field DI
- Disallows immutable field declaration
- Eases signle responsibility principal violation
- Tightly coupled with dependency injection container
- Hidden dependencies





### Ref
[field di](https://blog.marcnuri.com/field-injection-is-not-recommended)