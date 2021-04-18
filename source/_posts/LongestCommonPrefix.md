---
title: Longest Common Prefix
date: 2021-03-12 23:36:00
description: Longest Common Prefix
tags: algo
---

### Longest Common Prefix

```md
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

```


### #1
```java

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        
        String shortest = strs[0];
        for (String str : strs) {
            shortest = shortest.length() < str.length() ? shortest : str;
        }
        
        int N = shortest.length();
        int mark = 0;
        boolean cc = true;
        for (int i = 0; i < N; i++) {
            char c = shortest.charAt(i);
            for (String str : strs) {
                if (c != str.charAt(i)) {
                    cc  = false;
                    break;
                }
            }
            if (cc) mark++;
        }
        
        return shortest.substring(0, mark);
        
    }
}

```
