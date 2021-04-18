---
title: Strategy Pattern 
date: 2020-12-28 09:32:40
description: Strategy Pattern Demo in SpringBoot
tags: Java
---

# This is a Simple Usage of Strategy in Spring

[some source](https://github.com/jensalm/strategy/blob/master/src/main/java/com/captechventures/strategy/StrategyFactory.java)

### Register With Factory
You can use Map to store you Strategies.
```java
@Component
public class MultipleOperationFactory {

    @Autowired
    private ApplicationContext applicationContext;

    private final Map<Integer, MultipleOperation> multipleStrategies = new HashMap<>();

    public MultipleOperation fetchOperation(Integer changeType) throws ProcessingException {
        MultipleOperation multipleOperation =  multipleStrategies.get(changeType);
        if (Objects.isNull(multipleOperation)) {
            throw new ProcessingException(ProcessingErrorCode.FAILURE_CAN_NOT_FIND_STRATEGY);
        }
        return multipleOperation;
    }

    @PostConstruct
    private void createStrategies() {
        Map<String, Object> strategyBeans =  applicationContext.getBeansWithAnnotation(MultipleStrategy.class);
        strategyBeans.values().forEach(strategy -> {
            MultipleStrategy strategyAnnotation = AnnotationUtils.findAnnotation(strategy.getClass(), MultipleStrategy.class);
            if (Objects.nonNull(strategyAnnotation)) {
                multipleStrategies.put(strategyAnnotation.changeType().getValue(), (MultipleOperation) strategy);
            }
        });
    }

}
```
### Define Strategy
you can use `interface` or `abstract class` to implement your strategy for you need
And I use one custom `annotation` to mark strategies
```java
public interface MultipleOperation {
    public void execute();
}

@Component
@MutipleStrategy()
public class OneOperation implements MultipleOperation {
    public void execute() {}
}

@Component
@MutipleStrategy()
public class TwoOperation implements MultipleOperation {
    public void execute() {}
}

```
