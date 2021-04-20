---
title: Longest Substring Without Repeating Characters
date: 2021-03-10 13:12:39
description: RemoveElements
tags: algo
---

##   Longest Substring Without Repeating Characters



```md

Given a string s, find the length of the longest substring without repeating characters.

Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0

Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.

```


## Solution


#### #1 
```java
class Solution {
    public static  int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int N = s.length(), span = 0;
        
        for (int start=0, point=start+1; start < N;) {
            span = Math.max(point - start, span);       
            if (point == N) {
                start++;
                point = start+1;
                continue;
            }
            char alp = s.charAt(start);         
            boolean re = false;
            if (alp != s.charAt(point)) {
                for (int j=start+1; j < point; j++) {
                    if (s.charAt(j) == s.charAt(point)) {
                        re = true;
                    }
                }
                if (re) {
                    start++;
                    point = start+1;
                } else {
                    point++;
                }
            } else {
                start++;
                point = start + 1;
            }
        }
        return span;        
    }
}
```

### #2 
```java
    public static  int lengthOfLongestSubstring0(String s) {
        if (s == null || s.length() == 0) return 0;
        int N = s.length();
        
        Set<Character> window = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        
        while (i < N && j < N) {            
            char a = s.charAt(j);
            if (!window.contains(a)) {
                j++;
                ans = Math.max(ans, j - i);
                window.add(a);
            } else {                
                window.remove(s.charAt(i++));
            }
        }

        return ans;
        
    }

```

### #3
```java
    public static  int lengthOfLongestSubstring00(String s) {
        int N = s.length();

        Map<Character, Integer> window = new HashMap<>();
        int i = 0, ans = 0;

        for (int j = 0; j < N; j++) {
            if(window.containsKey(s.charAt(j))) {
                i = Math.max(i, window.get(s.charAt(j)));                
            }
            ans = Math.max(ans, j - i + 1);
            window.put(s.charAt(j), j + 1);            
        }

        return ans;
        
    }
```
