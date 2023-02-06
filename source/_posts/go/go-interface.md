---
title: Interface In Go -- method aspect
description: the usage of interface as method
date: 2023-01-10 16:10:00
tags:
- go
categories:
- go
---

# Interface

So what is an interface? 

An interface is two things: 
- it is a set of methods
- it is also a type. 

This article explains the basic usage of `interface` as `a set of methods` in go.

To be familiar with `interface`, we need to know one things:
- The purpose of interface's usage

## A Brief Summary
- An `interface` is some kind of contract for what can do with it.
- An `interface` provides a form, a protocol, a standard, a contract, a specification, a set of rules, an interface
- An `interface` is a specification and rules that any `struct` implementing it agrees to follow.


## Basic

### Let's start with a simple example
<table>
<tr>
<th> GO </th>
<th> Java </th>
</tr>
<tr>
<td>

```go
package main
import "fmt"
type Flyable interface {
	fly()
}
type Man struct {}
func (m Man) fly() {
	fmt.Println(" man can not fly ")
}
func fly(f Flyable) {
	f.fly()
}
func main() {
	man := Man{}
	fly(man)
}
```
A `Flyable` is a `interface` with a `fly()` method signature.

A `Man` is a `struct` which implements the `fly()` method with `value receiver`.

A `fly(f Flyable)` is a `func` which receive a `Flyable interface`.

When we call `fly(man)`, the method will implicitly call `the fly() method`
of `Man`'s.
</td>
<td>

```java
interface Flyable {
    void fly();
}
class Man implements Flyable {
    public void fly() {
        System.out.println(" I can not fly ");
    }
}
public class A {
    public static void fly(Flyable f) {
        f.fly();
    }
    public static void main(String[] args) {
        Man man = new Man();
        fly(man);
    }
}
```
The purpose of `interface` is the same, and `go` choose a different way to 
power `oop` to itself.
</td>
</tr>
</table>


## Value receiver && Pointer receiver
As we can follow up the example

<table>
<tr>
<th> value receiver </th>
<th> pointer receiver </th>
</tr>
<tr>
<td>

```go
package main
import "fmt"
type Flyable interface {
	fly()
}
type Man struct {}
func (m Man) fly() {
	fmt.Println(" man can not fly ")
}
func fly(f Flyable) {
	f.fly()
}
func main() {
	man := Man{}
	fly(man)
    manptr := &Man{}
    fly(manptr)
}
```
</td>
<td>

```go
package main
import "fmt"
type Flyable interface {
	fly()
}
type Man struct {}
func (m *Man) fly() {
	fmt.Println(" man can not fly ")
}
func fly(f Flyable) {
	f.fly()
}
func main() {
	// man := Man{}
	// fly(man)
    manptr := &Man{}
    fly(manptr)
}
```
</td>
</tr>
</table>

As we can see `pointer receiver` can fit into both scenario, while
`value receiver` can not fit into `pointer receiver`.

### Why we need two type receiver
> Methods with pointer receivers can modify the value to which the receiver points


## Embedding In Go
As Embedding explained in [effective go](https://go.dev/doc/effective_go#embedding)

Go does not provide the typical, type-driven notion of subclassing,
but it does have the ability to “borrow” pieces of an implementation 
by embedding types within a struct or interface.


### Interface In Interface
<table>
<tr>
<th> Go </th>
<th> Java </th>
</tr>
<tr>
<td>

```go
package main
import "fmt"
type Flyable interface {
	fly()
}
type Shootable interface {
    shoot()
}
type FlyShoot interface {
    Flyable
    Shootable
}
type SuperMan struct {}
func (s *Man) fly() {
	fmt.Println(" superman fly ")
}
func (s *Man) shoot() {
    fmt.Println(" superman shoot ")
}
func flyshoot(fs FlyShoot) {
    fs.fly()
    fs.shoot()
}
func main() {
    superman := &SuperMan{}
    flyshoot(superman)
}
```
</td>
<td>

```java
interface Flyable {
    void fly();
}
interface Shootable {
    void shoot();
}
interface FlyShoot extends Flyable, Shootable {}

class SuperMan implements FlyShoot {
    public void fly() {
        System.out.println(" superman fly ");
    }
    public void shoot() {
        System.out.println(" superman shoot ");
    }
}
public class A {
    public static void flyshoot(FlyShoot fs) {
        fs.fly();
        fs.shoot();
    }
    public static void main(String[] args) {
        SuperMan superman = new SuperMan();
        fly(superman);
    }
}
```
</td>
</tr>
</table>

Once again, go use it's own way to power the `oop` ability.


### Interface In Struct
<table>
<tr>
<th> Anonymouse </th>
<th> Explicit </th>
</tr>
<tr>
<td>

```go
type Flyable interface {
	fly()
}
type FlyMan struct {
	name string
	Flyable
}
type eagle struct {}
func (e eagle) fly() {
	fmt.Println(" >>> fly like eagle <<< ")
}

func fly(o Flyable) {
	fmt.Printf(" >>> o: %+v \n", o)
}
type otherMan struct {
	Flyable
}

func main() {
	e := eagle{}
	s := FlyMan{
		Flyable: e,
		name: "eagle man",
	}
	fly(s)
	s.fly()

	o := otherMan{s}
	fly(o)

}

```
</td>

<td>

```go
type Flyable interface {
	fly()
}
type FlyMan struct {
	name string
	fly Flyable
}
type eagle struct {}
func (e eagle) fly() {
	fmt.Println(" >>> fly like eagle <<< ")
}

func fly(o Flyable) {
	fmt.Printf(" >>> o: %+v \n", o)
}

func main() {
	e := eagle{}
	ss := FlyMan{
		name: "eagle man",
		fly: e,
	}
	fly(ss.fly)
	ss.fly.fly()	
}

```

</td>

</tr>
</table>

### Compare to OOP

<table>
<tr>
<th> Go </th>
<th> Java </th>
</tr>
<tr>
<td>

```go
type Flyable interface {
	fly()
}
type FlyMan struct {
	name string
	Flyable
}
type bird struct {}
func (b *bird) fly() {
	fmt.Println(" >>> fly like bird <<< ")
}
type eagle struct {}
func (e *eagle) fly() {
	fmt.Println(" >>> fly like eagle <<< ")
}
func fly(o Flyable) {
	fmt.Printf(" >>> o: %+v \n", o)
}
func main() {
	
    b := &bird{}	
    e := &eagle{}

	s := &FlyMan{
		Flyable: b,
		name: "Kobe",
	}
	fly(s)
	s.fly()
	fmt.Println(" change fly sytle ")

    s.Flyable = e
    fly(s)
	s.fly()

	fmt.Println("==============")
}
```
</td>

<td>

```java
interface Flyable {
    void fly();
}
class Bird implements Flyable {
    public void fly() {
        System.out.println(" >>> fly like bird <<< ");
    }
}
class Eagle implements Flyable {
    public void fly() {
        System.out.println(" >>> fly like eagle <<< ");
    }
}
public class A {
    public static Flyable fly;
    public static void main(String[] args) {
        Flyable bird = new Bird();
        fly = bird;
        fly.fly();

        System.out.println(" change fly sytle ");

        Flyable eagle = new Eagle();
        fly = eagle;
        fly.fly();

    }
}
```

</td>

</tr>
</table>


### LINK TO GO REFERENCES & RESOURCES
- https://go.dev/tour/methods/4
- https://jordanorelli.com/post/32665860244/how-to-use-interfaces-in-go
- https://research.swtch.com/interfaces