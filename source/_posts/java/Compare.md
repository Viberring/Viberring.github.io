---
title: compare in java
description: compare in java
date: 2021-12-03 14:25:28
tags:
categories:
- java
---

# Comparable and Comparator
In my opinion, considerring the functionality these two are
the same. well, they are different in implementation.

## Comparable
object that implement `Comparable` means this object is comparable 
with another object.
the doc says `natural ordering` is applied, and i think this is saying
that we should compare with `one condition` which is natural, cuz in 
real world we usually compare things with this kind of rule.


```java
public class Comparable {

    static class Cble implements java.lang.Comparable<Cble> {
        private int id;
        public Cble(int id) { this.id = id; }
        @Override
        public int compareTo(Cble o) {
            return Integer.compare(o.id, this.id);
        }

        @Override
        public String toString() {
            return "Cble{" +
                    "id=" + id +
                    '}';
        }

        public static void main(String[] args) {
            List<Cble> cbleList = Arrays.asList(
                new Cble(0), new Cble(1),
                new Cble(2), new Cble(3),
                new Cble(4), new Cble(8)
            );
            Collections.sort(cbleList);
            System.out.println(
                    cbleList
            );
        }
    }

}
```
the result shows that we have a descending array.
Cuz, the element in List is `Comparable` which 
make this happend.
```js
[Cble{id=8}, Cble{id=4}, Cble{id=3}, Cble{id=2}, Cble{id=1}, Cble{id=0}]
```

## Comparator
object that implement `Comparator` means this object is a
comparator which compare two object.

```java
public class Comparator {

    static class Ctr implements java.util.Comparator<Ctr> {
        private int id;
        public Ctr() {}
        public Ctr(int id) { this.id = id; }
        @Override
        public int compare(Ctr o1, Ctr o2) {
            return Integer.compare(o2.id,  o1.id);
        }
        @Override
        public String toString() {
            return "Ctr{" +
                    "id=" + id +
                    '}';
        }

        public static void main(String[] args) {
            List<Ctr> ctrList = Arrays.asList(
                new Ctr(0), new Ctr(1),
                new Ctr(2), new Ctr(3),
                new Ctr(6), new Ctr(9)
            );
            Collections.sort(ctrList, new Ctr());
            System.out.println(
                    ctrList
            );
        }
    }

}
```
the elements in List have nothing to do with the `Comparator`
it could be any element.
so the `Comparator`'s functionality is to compare to objects.
```js
[Ctr{id=9}, Ctr{id=6}, Ctr{id=3}, Ctr{id=2}, Ctr{id=1}, Ctr{id=0}]
```