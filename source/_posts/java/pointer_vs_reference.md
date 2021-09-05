---
title: pointer_vs_reference
description: pointer_vs_reference
date: 2021-08-30 23:08:41
tags: 
- Java
categories:
- Java
---

# Pointer && Reference

## Reference
reference is much alias and once you create a reference like `int& a`
then you can not change `a` eternally.

## Pointer
pointer store a memory address and pointer can be `void *`
while reference can not.

# Pass By Pointer
```cpp
#include <iostrem>
using namespace std;
void swap(int *a, int *b) {
    int c = *a;
    *a = *b;
    *b = c;
}
int main()
{
    int a = 45, b = 35;
    cout << "Before Swap\n";
    cout << "a = " << a << " b = " << b << "\n";
 
    swap(&a, &b);
 
    cout << "After Swap with pass by pointer\n";
    cout << "a = " << a << " b = " << b << "\n";
}
```

# Pass By Reference
```cpp
#include <iostream>
using namespace std;
void swap(int& x, int& y)
{
    int z = x;
    x = y;
    y = z;
}
 
int main()
{
    int a = 45, b = 35;
    cout << "Before Swap\n";
    cout << "a = " << a << " b = " << b << "\n";
 
    swap(a, b);
 
    cout << "After Swap with pass by reference\n";
    cout << "a = " << a << " b = " << b << "\n";
}

```

## Summary

some say reference is more like a pointer internal with some differences;
And I am still confused about it.





### Ref
[stack overflow](https://www.geeksforgeeks.org/passing-by-pointer-vs-passing-by-reference-in-c/)