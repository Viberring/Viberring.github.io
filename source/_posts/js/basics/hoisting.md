---
title: js variable declaration
description: werid js scope
date: 2021-10-09 09:54:09
tags:
- js
categories:
- js
---
# Variable Hoisting
the `variable hoisting` is some kind of anti-programming.
but when you work with `js`, you must know how it works.
then you know how to avoid bad things happen.

## What is Hoisting
when you use `var` to declare a variable.
the variable will be initialized with `undefined`
and this `variable` will be lift to the start of 
the scope (`scope`) rather deal with variable when 
execution reach to the `variable declaration statement`.

## Example

### function hoisting
we call a functio before we define it.
this is `function hoisting`.
so what is `function hoisting`.
`function hoisting` is just like we declare a variable in 
the form `var bark[function(word) {}]`
```js
bark("kkkk");
function bark(word) {
    console.log(word);
}
```

### only declarations are hoisted
```js
console.log(num); // undefined
var num;
num = 6;
```
```js
console.log(num); // undefined
var num = 3;
```
this is wired, the two cases are the same.
this proves that only the the name is hoisted.
In this case whether you `declare` a variable
or `assign` a variable only the variable itself
is left to the top of the `scope` with initial
value with `undefined`.

which directrly make `functin hoisting` reasonable.
```js
var a = function() {}
funcation b() {}
```
the `a` and `b` will be lift to the top of the 
scope, while `a` is `undefined` which means you
can not just call `a()`. but you can directly 
call `b()`.

### some thing strange
how we get this value ?
yes, cuz hoisting, in the scope of function
the `foo` is lift to the top of the `function scope`
with initial value of `undefined`.
while the outer `foo` is not in the scope of 
`function scope` it will not affect the inner `foo`
```js
var foo = 1;
function bar() {
    if (!foo) {
        var foo = 10;
    }
    console.log(foo);
}
bar(); // outpu: 10
```
---

this is the same, which have two seperate
scope.
```js
var a = 1;
function b() {
    a = 10;
    return;
    function a() {}
}
b();
console.log(a); // output: 1
```
--- 
cuz `if` does not create `scope`
the `x` will not be lifted.
```js
var x = 1;
console.log("x: ", x); // 1
if (true) {
    var x = 2;
    console.log("x : ", x); // 2
}
console.log("x: ", x); // 2
```
while we can create a `scope` with a 
anonymous function.
```js
var x = 1;
console.log("x: ", x); // 1
if (true) {
    (function() {        
        var x = 2;
        console.log("x : ", x); // 2
    })();
}
console.log("x: ", x); // 1
```
---
this is the difference between `function declaration`
and `function assignment`.
```js
function test() {
    foo(); // TyperError
    bar();
    var foo = function() {
        console.log(">>>>>> foo()");
    }
    function bar() {
        console.log('>>>>>> bar()');
    }
}
```

## Summary
This is all about js's scope design.
Js's scope is confusing and I did not experience any
help with it, so i do not favor this way.


## Ref

[mdn](https://developer.mozilla.org/en-US/docs/Glossary/Hoisting)
this is the official doc and it do help.


[hoisting](http://www.adequatelygood.com/JavaScript-Scoping-and-Hoisting.html)
this is the very good explanation.