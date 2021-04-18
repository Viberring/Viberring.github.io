---
title: Palindrome Number
date: 2021-03-12 23:36:00
description: Palindrome Number
tags: algo
---

# Palindrome Number


```md
Given an integer x, return true if x is palindrome integer.

An integer is a palindrome when it reads the same backward as forward. For example, 121 is palindrome while 123 is not.

 

Example 1:

Input: x = 121
Output: true
Example 2:

Input: x = -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: x = 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Example 4:

Input: x = -101
Output: false
 

Constraints:

-231 <= x <= 231 - 1

```


### #1

```java

class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        
        int ans = 0;
        int temp = x;
        

        while ( x >= 10) {
            int r = x % 10;
            x = x/10;
            ans = (ans + r) * 10;
        }
        
        
        return (ans + x) == temp;
        
    }
}

```
