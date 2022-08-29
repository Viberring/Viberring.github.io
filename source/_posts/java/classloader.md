---
title: ClassLoader
description: what is classloader 
date: 2021-04-30 09:31:26
tags:
- java
categories:
- java
---

# Background 
    Why Spring use ClassLoader

# Java ClassLoader 
    classLoader dynamicly load class into jvm at runtime.

## Why
    ClassLoader.loadClass("/fullname/class") is lazy initialiazaion.



## Java ClassLoader

BootstrapClassLoader is loaded by jvm
First Launcher Will load ExtClassLoader and AppClassLoader

```java
package sun.misc;
public class Launcher {
    ...
    private ClassLoader loader;
    ...
    // the loader is set to ApplicationClassLoader
    // Now create the class loader to use to launch the application
    try {
        loader = AppClassLoader.getAppClassLoader(extcl);
    } catch (IOException e) {
        throw new InternalError(
            "Could not create application class loader", e);
    }
    // Also set the context class loader for the primordial thread.
    Thread.currentThread().setContextClassLoader(loader);
    ...
    ...
    static class ExtClassLoader extends URLClassLoader {
        ...
    }
    ...
    static class AppClassLoader extends URLClassLoader {
        ...
    }
    ...
}
```
As We can see `ExtClassLoader` and `AppClassLoader` is 
extends from `URLClassLoader` and `URLClassLoader` extends
from `SecureClassLoader` which extends from `ClassLoader`.

```java
public abstract class ClassLoader {
    ...
    // default constructor
    protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }
    ...
    @CallerSensitive
    public static ClassLoader getSystemClassLoader() {
        initSystemClassLoader();
        if (scl == null) {
            return null;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            checkClassLoaderPermission(scl, Reflection.getCallerClass());
        }
        return scl;
    }

    private static synchronized void initSystemClassLoader() {
        if (!sclSet) {
            if (scl != null)
                throw new IllegalStateException("recursive invocation");
            sun.misc.Launcher l = sun.misc.Launcher.getLauncher();
            if (l != null) {
                Throwable oops = null;
                // this will get the AppClassLoader
                // so ClassLoader default constructor will
                // use AppClassLoader as default classLoader
                // but the precondition is AppClassLoader is loaded.
                scl = l.getClassLoader(); 
                try {
                    scl = AccessController.doPrivileged(
                        new SystemClassLoaderAction(scl));
                } catch (PrivilegedActionException pae) {
                    oops = pae.getCause();
                    if (oops instanceof InvocationTargetException) {
                        oops = oops.getCause();
                    }
                }
                if (oops != null) {
                    if (oops instanceof Error) {
                        throw (Error) oops;
                    } else {
                        // wrap the exception
                        throw new Error(oops);
                    }
                }
            }
            sclSet = true;
        }
    }
    ....
}
```

```java
public class A {
    public A() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>");
    }
    public void Say() {
        System.out.println(">>> Say <<<");
    }
    public static void main(String[] args) throws Exception {
        URLClassLoader loader = new URLClassLoader(new URL[]{ new URL("file:///Users/viber/Workspace")});
        Class c = loader.loadClass(args[0]);
        Object o = c.newInstance();
    }
}
```

### Practice 
[Implementation](https://github.com/Viberring/THE-WAY-TO-THE-FUTURE/tree/master/spring-practice/class-loader-demo)


### Ref
[Oracle](https://www.oracle.com/technical-resources/articles/javase/classloaders.html)
[A](https://stackoverflow.com/questions/2424604/what-is-a-java-classloader)
[Java ClassLoader](https://segmentfault.com/a/1190000013469223)
[classloader](http://ifeve.com/classloader/)
[how to use classloaders](https://www.jrebel.com/blog/how-to-use-java-classloaders)