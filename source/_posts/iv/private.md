# Private

so what is private ?
well, private is private.
ok, but is private private ?

## Java
private in java is a access-restrict for 
encapsulation.
but private is not absolutely private in java.

Here is one example.
`private` is not private, it can be accessed.
so private field is not inherited.
but we can still get the private field by
public access method.
```java
class A {
    private int a;
    public A(int a) { this.a = a; }
    public int getA() { return a; }
}
class B extends A {
    public B(int b) { super(b); }
    public int getB() { return getA(); }
}
public static void main(String[] args) {
    int result = new B(10).getA(); // 10
}
```

with reflection, anything is just open to 
the world.
```java
class A {
    private int i = 5；
}
class B extends A {}
B b = new B();
Field f = A.class.getDeclaredField("i");
f.setAccessible(true);
int i = (int) f.get(b); // 5
```