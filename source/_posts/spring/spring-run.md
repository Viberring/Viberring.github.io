---
title: kickoff
description: kickoff
date: 2021-04-28 14:25:28
tags:
categories:
- spring
---

# Spring Kickoff


```java
@SpringBootApplication
public class KickoffApplication {
    public static void main(Stirng[] args) {
        SpringApplication.run(KickoffApplication.class, args);
    }
}
```

## How to start
we start a application from `SpringApplicaton.run(clazz, args)`
```java
public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
    return run(new Class<?>[] { primarySource }, args);
}
public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
    return new SpringApplication(primarySources).run(args);
}
public SpringApplication(Class<?>... primarySources) {
    this(null, primarySources);
}
@SuppressWarnings({ "unchecked", "rawtypes" })
public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
    // default we don't set resourceLoader
    this.resourceLoader = resourceLoader;
    Assert.notNull(primarySources, "PrimarySources must not be null"); 
    // The entry ApplicationClass
    this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources)); 
    // WEB TYPE
    this.webApplicationType = WebApplicationType.deduceFromClasspath();   
    // Bootstrap.class && BootstrapRegistryInitializer
    this.bootstrapRegistryInitializers = getBootstrapRegistryInitializersFromSpringFactories(); 
    setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    // 
    this.mainApplicationClass = deduceMainApplicationClass();
}
public ConfigurableApplicationContext run(String... args) {
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    // only create bootstrapcontext 
    DefaultBootstrapContext bootstrapContext = createBootstrapContext();
    ConfigurableApplicationContext context = null;
    // what is this ?
    configureHeadlessProperty();
    // 
    SpringApplicationRunListeners listeners = getRunListeners(args);
    listeners.starting(bootstrapContext, this.mainApplicationClass);
    try {
        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        ConfigurableEnvironment environment = prepareEnvironment(listeners, bootstrapContext, applicationArguments);
        configureIgnoreBeanInfo(environment);
        Banner printedBanner = printBanner(environment);
        context = createApplicationContext();
        context.setApplicationStartup(this.applicationStartup);
        prepareContext(bootstrapContext, context, environment, listeners, applicationArguments, printedBanner);
        refreshContext(context);
        afterRefresh(context, applicationArguments);
        stopWatch.stop();
        if (this.logStartupInfo) {
            new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
        }
        listeners.started(context);
        callRunners(context, applicationArguments);
    }
    catch (Throwable ex) {
        handleRunFailure(context, ex, listeners);
        throw new IllegalStateException(ex);
    }

    try {
        listeners.running(context);
    }
    catch (Throwable ex) {
        handleRunFailure(context, ex, null);
        throw new IllegalStateException(ex);
    }
    return context;
}
```

### What work `SpringApplication.run(clazz, args)` do
    bootstrap spring application
- create `ApplicationContext`
- register `CommandLinePropertySource`
- refresh context, loading all singleton beans
- trigger `CommandLineRunner`





## How to init





### Practice
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/kickoff)