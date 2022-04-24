---
title: clone in java
description: clone in java
date: 2021-12-03 14:25:28
tags:
categories:
- java
---

# Clone In Java

`clone` method is `protected method` in `Object`.
so in java, every object will have `clone` method.
but we can't use it directly unless we implement `Clonable`.

1. we must implement `Clonable` interface
2. `clone` does not trigger construt method
3. we can not modify final fields in `clone`
4. `clone`'s default copy is `shallow copy`

## Clonable interface
`clone` is used in the precense of `Clonable` interface.
In the doc, the `clone` method is used by `jvm` which 
will check if the object can use clone method.

the example will throw a `CloneNotSupportedException`.
```java
public class CloneInterface {
    static class EX {
        private String name;
        @Override
        protected EX clone() throws CloneNotSupportedException {
            return (EX) super.clone();
        }

        public static void main(String[] args) throws CloneNotSupportedException {
            EX ex = new EX();
            ex.name = "ex";
            EX nex = ex.clone();
        }
    }
}
```

## Construction
when we create a new object, we will call its constructor
but clone method will not call its constructor.

```java
public class CloneInterface {

    static class EX implements Cloneable {
        private String name;
        public EX(String name) {
            System.out.println(">>>>>>> call constructor");
            this.name = name;
        }
        @Override
        protected EX clone() throws CloneNotSupportedException {
            return (EX) super.clone();
        }

        public static void main(String[] args) throws CloneNotSupportedException {
            EX ex = new EX("ex");
            System.out.println(">>>>>>>>>>>>> start clone");
            ex.clone();
            System.out.println(">>>>>>>>>>>>> end clone");
        }
    }
}
```
the output shows that the clone is just create object by copying fields
and do not construct the object.
```js
>>>>>>>>>>>>> call constructor
>>>>>>>>>>>>> start clone
>>>>>>>>>>>>> end clone
```


## Shallow Copy && Deep Copy
the clone is default to `shallow copy`, so if we have `final`
field in `object` then we are in trouble.
we can not modify the `final object` which means we can not
make `deep copy`.

### Shallow Copy
Cuz in java we have refrence objects, so when we copy object
we copy the reference other than create a new reference object.


```java
public class CloneInterface {

    static class Family {
        private String name;
        public Family(String name) { this.name = name; }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return "Family{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    static class Dog implements Cloneable {
        private String name;
        private int age;
        private Family family;
        public Dog(String name, int age, Family family) {
            this.name = name; this.age = age; this.family = family;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Family getFamily() {
            return family;
        }

        public void setFamily(Family family) {
            this.family = family;
        }

        @Override
        protected Dog clone() throws CloneNotSupportedException {
            return (Dog) super.clone();
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", family=" + family +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Family family = new Family("L");
        Dog d = new Dog("T", 12, family);
        Dog nd = d.clone();
        System.out.println(d);
        System.out.println(nd);
        // they are not equal
        System.out.println(Objects.equals(d, nd));
        // modify family
        nd.getFamily().setName("S");
        System.out.println(d);
        System.out.println(nd);
    }

}
```
the output will prove that we did modify the same object.
which means we did not get a whole brand new object.
```js
Dog{name='T', age=12, family=Family{name='L'}}
Dog{name='T', age=12, family=Family{name='L'}}
false
Dog{name='T', age=12, family=Family{name='S'}}
Dog{name='T', age=12, family=Family{name='S'}}
```

### Deep Copy
In contrast to shallow copy, we get a brand new object
wieh deep copy.
so let's make some tiny change to the code.
```java
public class CloneInterface {

    static class Family implements Cloneable {
        private String name;
        public Family(String name) { this.name = name; }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        protected Family clone() throws CloneNotSupportedException {
            return (Family)super.clone();
        }

        @Override
        public String toString() {
            return "Family{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
    static class Dog implements Cloneable {
        private String name;
        private int age;
        private Family family;
        public Dog(String name, int age, Family family) {
            this.name = name; this.age = age; this.family = family;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Family getFamily() {
            return family;
        }

        public void setFamily(Family family) {
            this.family = family;
        }

        @Override
        protected Dog clone() throws CloneNotSupportedException {
            Dog d = (Dog) super.clone();
            d.family = this.family.clone();
            return d;
        }

        @Override
        public String toString() {
            return "Dog{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", family=" + family +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        Family family = new Family("L");
        Dog d = new Dog("T", 12, family);
        Dog nd = d.clone();
        System.out.println(d);
        System.out.println(nd);
        // they are not equal
        System.out.println(Objects.equals(d, nd));
        // modify family
        nd.getFamily().setName("S");
        System.out.println(d);
        System.out.println(nd);
    }
}
```
the output shows that we did not modify the original family object.
which is what exactly we want.
```js
Dog{name='T', age=12, family=Family{name='L'}}
Dog{name='T', age=12, family=Family{name='L'}}
false
Dog{name='T', age=12, family=Family{name='L'}}
Dog{name='T', age=12, family=Family{name='S'}}
```


# Alternative 
the clone method has so many disadvantage, then 
where we will go.

## The Copy Construcor
the copy constructor will init with the `object`
we want copy and in the process we can make any 
modification we want without influence the original
object.
```java
public class CloneInterface {
    static class EX implements Cloneable {
        private String name;
        private final List<String> list;
        public EX(String name) {
            this.name = name;
            this.list = new ArrayList<>();
        }
        public EX(EX ex) {
            this.name = ex.name;
            this.list = new ArrayList<>(ex.list);
        }
    }
}
```

## Serialization
serialization is not recommended in `effictive java`
so we will discuss this later.