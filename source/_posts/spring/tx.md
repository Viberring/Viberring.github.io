---
title: spring transaction
description: spring tx
date: 2021-12-03 14:25:28
tags:
categories:
- spring
---

# Transaction In Spring

## @Transactional





### Practice 
```java
@Service
public class UserService {

    @Autowired
    private TransactionHandler transactionHandler;

    public boolean addUsers(List<User> users) {
        for (User user : users) {
            transactionHandler.runInTransaction(() -> addUser(user.getUsername, user.getPassword));
        }
    }

    private boolean addUser(String username, String password) {
        // TODO call userRepository
    }
}

@Service
public class TransactionHandler {

    @Transactional(propagation = Propagation.REQUIRED)
    public <T> T runInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public <T> T runInNewTransaction(Supplier<T> supplier) {
        return supplier.get();
    }
}
```




### Ref
[tx problem](https://stackoverflow.com/questions/3423972/spring-transaction-method-call-by-the-method-within-the-same-class-does-not-wo)