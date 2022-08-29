---
title: spring transaction
description: spring tx
date: 2021-12-03 14:25:28
tags:
categories:
- spring
---

# Transaction In Spring
How spring trasaction implement

## Top Design 
```java
public interface TransactionManager {}
public interface PlatformTransactionManager extends TransactionManager {
    TransactionStatus getTransaction(
        TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
```
PlatformTransactionManager is primarily a service provider interface (SPI).
`TransactionException` is a `RuntimeException`

`TransactionDefinition` interface's aid
- Isolation
- Propagation
- Timeout
- Read-only status
```java
public interface TransactionDefinition {
	int PROPAGATION_REQUIRED = 0;
    int PROPAGATION_SUPPORTS = 1;
    int PROPAGATION_MANDATORY = 2;
    int PROPAGATION_REQUIRES_NEW = 3;
    int PROPAGATION_NOT_SUPPORTED = 4;
    int PROPAGATION_NEVER = 5;
    int PROPAGATION_NESTED = 6;
    int ISOLATION_DEFAULT = -1;
    int ISOLATION_READ_UNCOMMITTED = 1;
    int ISOLATION_READ_COMMITTED = 2;
    int ISOLATION_REPEATABLE_READ = 4;
    int ISOLATION_SERIALIZABLE = 8;
    int TIMEOUT_DEFAULT = -1;
}
```

```java
public interface TransactionStatus 
                    extends TransactionExecution, 
                            SavepointManager, Flushable {
    boolean hasSavepoint();
    @Override
	void flush();
}
public interface TransactionExecution {
    boolean isNewTransaction();
    void setRollbackOnly();
    boolean isRollbackOnly();
    boolean isCompleted();
}
public interface SavepointManager {
    Object createSavepoint() throws TransactionException;
    void rollbackToSavepoint(Object savepoint) throws TransactionException;
    void releaseSavepoint(Object savepoint) throws TransactionException;
}
public interface Flushable {
    void flush() throws IOException;
}
```

## @Transactional
working with spring aop, so it's all about proxy.
when it comes with proxy, you should call from outside if you can.
- REQUIRED
- REQURIED_NEW
- NESTED


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