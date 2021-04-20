---
title: Container With Most Water
date: 2021-03-12 23:36:00
description: Container With Most Water
tags: algo
---

### Container With Most Water


```md
Given n non-negative integers a1, a2, ..., an , 
where each represents a point at coordinate (i, ai). 
n vertical lines are drawn such that 
the two endpoints of the line i is at (i, ai) and (i, 0). 
Find two lines, which, together with the x-axis forms a container, 
such that the container contains the most water.

Notice that you may not slant the container.



Example 1:

Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented 
by array [1,8,6,2,5,4,8,3,7]. 
In this case, the max area of water (blue section) 
the container can contain is 49.


Example 2:
Input: height = [1,1]
Output: 1

Example 3:
Input: height = [4,3,2,1,4]
Output: 16


Example 4:
Input: height = [1,2,1]
Output: 2
 

Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104

```


### #1
```java
class Solution {
    public int maxArea(int[] height) {
        
        int N = height.length;
        int ans = 0, start = 0, end = N - 1;
            
        while ( start < end )
            
            int h0 = height[start];
            int h1 = height[end];
        
            
            ans = Math.max(ans, Math.min(h0, h1) * (end - start));
            
            if(h0 <= h1) start++;
            if(h0 > h1) end--;
                        
        }
        
        return ans;
        
    }
}
```
