---
title: Struct In Go
description: the usage of struct
date: 2023-01-13 16:10:00
tags:
- go
categories:
- go
---


# Struct
Unlike `class` with `method` and `fields`, 
`struct` is just a collection of fields.

## Basic usage

Let's start with basic `struct` usage.

<table>
<tr>
<th> Go </th>
<th> Java </th>
</tr>
<tr>
<td>

```go
type Shape struct {
    name string
}
func (s *Shape) draw() {
	fmt.Printf(" >>> draw %s\n", s.name)
}
```

</td>

<td>

```java
public class Shape {
    private String name;
    public void draw() {
        System.out.println(String.format(" >>> draw %s", this.name));
    }
}
```

</td>

</tr>
</table>

## More Advance Usage

### Struct Embedding
we can embed `strcut` to another `struct`
to make a `hybird` struct

it's is more like a `composition`

<table>
<tr>
<th> Go </th>
<th> Java </th>
</tr>
<tr>
<td>

```go
type Shape struct {
    name string
}
func (s *Shape) draw() {
	fmt.Printf(" >>> draw %s\n", s.name)
}
type Circle struct {
    name string
    Shape
}
func (c *Circle) draw() {
    fmt.Println(" >>> draw %s\n", c.name)
}

func main() {
	s := &Shape{name: "Shape"}
	c := &Circle{
		name: "Circle",
		Shape: s,
	}
	fmt.Printf(" circle: %s\n", c)
	c.draw()
}

```

</td>

<td>

```java
public class Shape {
    private String name;
    public Shape(String name) {
        this.name = name;
    }
    public void draw() {
        System.out.println(String.format(" >>> draw %s", this.name));
    }
}
public class Circle extends Shape {
    private String name;
    public void draw() {
        System.out.println(String.format(" >>> draw %s", this.name));
    }
}
public static void main(String[] args) {
    Shape s = new Shape("shape");
    s.draw();
    Circle c = new Shape("circle");
    c.draw();
}
```

</td>

</tr>
</table>



### LINK TO GO REFERENCES & RESOURCES
