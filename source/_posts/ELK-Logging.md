---
title: ELK-Logging
date: 2020-12-28 08:45:09
tags: Java
description: SpringBoot Logstash ELK Configuration
---

# This is a simple configuration for Log4j2 & Logstach & ELK

# INSTALL STEPS

## Install Logstatch
    
#### Officail Download
     https://www.elastic.co/downloads/logstash
#### Run logstash with config file
1. change directory into logstash/bin  
`cd /pathto/logstach/bin`
2. install logstach's amazon plugin  
git: https://github.com/awslabs/logstash-output-amazon_es  
install: `./logstash-plugin install logstach-output-amazon_es`
3. change directory into logstash/config and create config file  
```sh
touch log4j2-gelf.conf
vim log4j2-gel.conf
```
```conf
input {
    gelf {
        host => "127.0.0.1"
        port => 9998
        type => gelf
    }
}
filter {
    if([application] =~ "^\$") {
        drop{}
    }
    grok {}
    translate {
        field => "[level]"
        destination => "[levelName]"
        dictionary => {
            "0" => "EMERG"
            "1" => "ALERT"
            "2" => "CRITICAL"
            "3" => "ERROR"
            "4" => "WARN"
            "5" => "NOTICE"
            "6" => "INFO"
            "7" => "DEBUG"
        }
    }
    mutate {
        lowercase => [ "host" ]
    }
}
output {
    stdout {
        codec => rubydebug
    }
    amazon_es {
        hosts => [ "your host" ]
        aws_access_key_id => "AKIAXI6NVJUIVJ3AF34Y"
        aws_secret_access_key => "your access key"
        index => "app-%{application}=%{+YYYY.MM.dd}-%{[host]}"
        region => "ap-southease-1"
    }
}
```

    
## Spring config

### add log4j2 dependencies
```xml
<!-- log4j2 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
<!--  load log4j2.yml   -->
<dependency>
    <groupId>com.fasterxml.jackson.dataformat</groupId>
    <artifactId>jackson-dataformat-yaml</artifactId>
</dependency>

<!-- logstash -->
<dependency>
    <groupId>biz.paluch.logging</groupId>
    <artifactId>logstash-gelf</artifactId>
    <version>1.14.0</version>
</dependency>
```
### write log4j2 yml file
```yml
Configuration:
    status: warn
    Properties:
        property:
            - name: LOG_PATTERN
              value: "%d{HH:mm:ss.SSS} %-5level %class{36} %L %M - %msg%xEx%n"
            - name: ENVIRONMENT
              value: DEFAULT

    Appenders:
        Console:
            name: Console
            target: SYSTEM_OUT
            PatternLayout:
                pattern: ${LOG_PATTERN}

        Gelf:
            name: logstash-gelf
            host: udp:192.168.56.1
            port: 8888
            ignoreExceptions: false
            Field:
                - name: application
                  pattern: "${ctx:application}"
                - name: timestamp
                  pattern: "%d{yyyy-MM-dd HH:mm:ss.SSS}"
                - name: logger
                  pattern: "%logger"
                - name: level
                  pattern: "%level"
                - name: simpleClassName
                  pattern: "%C{1}"
                - name: className
                  pattern: "%C"
                - name: host
                  pattern: "%host{lowerCase=true}"
                - name: exception
                  pattern: "%ex"
                - name: environment
                  pattern: "${ENVIRONMENT}"
    Loggers:
        Root:
            level: INFO
            AppenderRef:
                - ref: Console
                - ref: logstash-gelf
```
### With Dockerfile
```docker
ENV APPLICATION='your-application-name'
```
### Add Log4j2EventListener
```java
public class Log4jEventListener implements  GenericApplicationListener {

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = { ApplicationStartingEvent.class, ApplicationEnvironmentPreparedEvent.class,
            ApplicationPreparedEvent.class, ContextClosedEvent.class, ApplicationFailedEvent.class };

    private static Class<?>[] SOURCE_TYPES = { SpringApplication.class, ApplicationContext.class };


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            String application = environment.getProperty("spring.application.name");
            if (StringUtils.isNotBlank(application)) {
                MDC.put("application", application);
            }
        }
    }


    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

}
```
### Register EventListener
```java
@SpringBootApplication(scanBasePackages = GlobalConst.SCAN_BASE_PACKAGE)
@EnableEurekaClient
public class PaymentRestApiApplication {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        SpringApplication app = new SpringApplication(PaymentRestApiApplication.class);
        app.addListeners(new Log4jEventListener());
        app.run(args);
    }
}
```

## Multi-Log4j2-profile
configure file path in bootstrap.yml or application.yml
then create new log4j2-xxx.yml file according to your develop environment.  
example:
```yml
spring:
  profiles: local-v3
arb:
  eureka:
    url: localhost
  service:
    url: localhost
logging:
  config: classpath:log4j2-local-v3.yml
```
