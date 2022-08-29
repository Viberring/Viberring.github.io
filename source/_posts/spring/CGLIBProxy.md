---
title: cglib proxying in spring @Configuration
description: cglib proxying in spring @Configuration for real
date: 2022-05-06 17:32:47
tags:
- cglib
categories:
- spring
---

# CGLib Proxying Practice in Spring @Configuration

## Bean in Configuration
Method invocations in a Spring @Configuration class don't follow the regular Java semantics. 
When we call postRepository() three times, it doesn't create three new PostRepository instances.
 This is because Spring creates a CGLIB proxy around the @Configuration classes. 
 The calls are intercepted and then Spring checks the container before creating a new bean for us.

- @Configuration class shouldn't be final
- @Bean methods shouldn't be final
- @Bean methods shouldn't be private

### Static @Bean Methods
When we declare a @Bean method as static, 
Spring doesn't need to initialize its enclosing @Configuration class. 
This behavior is convenient for some classes like BeanPostProcessors. 
Since Java doesn't allow overriding static methods, 
CGLIB proxying doesn't work with static bean definitions.

## Reference
- [practice](http://www.javabyexamples.com/cglib-proxying-in-spring-configuration)