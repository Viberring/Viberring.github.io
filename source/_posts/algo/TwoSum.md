---
title: TwoSum
date: 2021-02-12 17:20:39
description: Two Sum
tags: algo
categories:
- algo
---

## Record Solution And Thinking


#### Description

    Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    You can return the answer in any order.

    Example 1:

    Input: nums = [2,7,11,15], target = 9
    Output: [0,1]
    Output: Because nums[0] + nums[1] == 9, we return [0, 1].
    
    Example 2:
    Input: nums = [3,2,4], target = 6
    Output: [1,2]

    Example 3:
    Input: nums = [3,3], target = 6
    Output: [0,1]

    Constraints:

    2 <= nums.length <= 103
    -109 <= nums[i] <= 109
    -109 <= target <= 109
    Only one valid answer exists.

#### Solutions

##### #1
```java
public class Solution0 {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i=0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for (int i=0; i < nums.length; i++) {
            int current = nums[i];
            
            Integer cache = map.get(target - current);
            
            if (cache != null && cache != i) {
                result[0] = i;
                result[1] = cache;
                return result;
            }
            
        }
        return result;
    }
}
```


##### #2
```java
public class Solution0 {
    public int[] twoSum(int[] nums, int target) {
        
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i=0; i < nums.length; i++) {
            int current = nums[i];
            
            if (map.get(current) == null) {
                map.put(current, i);
            }
            int expect = target - current;
            if ( map.get(expect) != null && map.get(expect) != i) {
                result[0] = i;
                result[1] = map.get(expect);
                return result;
            }
        }
       
        return result;
    }
}
```


The difference is instead of caching all the values all at once, we can cache one at a time
cuz there will always have two values a and b => a + b = target, we loop once and check whether
there is a value in cache and we just need one loop then we get what we want.
