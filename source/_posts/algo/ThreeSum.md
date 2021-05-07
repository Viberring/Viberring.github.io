---
title: ThreeSum
description: ThreeSum
date: 2021-05-01 14:01:49
tags:
categories:
- algo
---


## ThreeSum
Given an integer array nums, return all the triplets 
[nums[i], nums[j], nums[k]] 
such that i != j, i != k, and j != k, 
and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.

Example 1:
    Input: nums = [-1,0,1,2,-1,-4]
    Output: [[-1,-1,2],[-1,0,1]]

Example 2:
    Input: nums = []
    Output: []

Example 3:
    Input: nums = [0]
    Output: []

Constraints:
    0 <= nums.length <= 3000
    -105 <= nums[i] <= 105


# 1

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
                
        List<List<Integer>> result = new ArrayList<>();
        int N = nums.length;
        if(N < 3) return result;
        
        Arrays.sort(nums);
        
        for (int i=0; i < N - 1; i++) {
            int a = nums[i];
            int k = N - 1;

            // there are some problem 
            // if input is [1, 2, 2, 3, 3]
            // which will lead to repeat iteration
            // so we must check repeat result
            for (int m = i+1, n = N-1; m < n; ) {
                int b = nums[m], c = nums[n];             
                if (a + b + c > 0) n--;
                if (a + b + c < 0) m++;
                if (a + b + c == 0) {             
                    List<Integer> o = new ArrayList<>();
                    o.add(a);o.add(c);o.add(b);
                    if(!result.contains(o)) {
                        result.add(o);
                    }
                    n--;
                    m++;
                }
            }
            // also  
            // if [1, 1, 1, 2]
            // we need skip the same number
            while(i < N - 1 && nums[i] == nums[i+1]) i++;
        }        
        return result;        
    }
}
```

# 2
```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int N = nums.length;
        if (N < 3) {
            return Collections.emptyList();
        }
        
        Arrays.sort(nums);
        
        int first = nums[0];
        if (first >= 0) {
            return (first == 0) && (nums[1] == 0) && (nums[2] == 0) ?
                    Collections.singletonList(Arrays.asList(0, 0, 0)) : Collections.emptyList();
        }
        
        List<List<Integer>> result = new ArrayList<>();
                
        for (int i = 0; i < N; i++) {
            if (i == 0 || nums[i] != nums[i-1]) { // this will skip same element
                int lo = i + 1, hi = N - 1;
                while (lo < hi) {
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if (sum == 0) {
                        
                        List<Integer> o = Arrays.asList(nums[i], nums[lo], nums[hi]);
                        result.add(o);

                        while(lo < hi && nums[lo] == nums[lo+1]) lo++; // this will skip the same element
                        while(hi > lo && nums[hi] == nums[hi-1]) hi--; // this work the same but reverse-direction

                        lo++;
                        hi--;
                    }
                    else if (sum > 0) hi--;
                    else lo++;
                }
            }
            // while(i < N - 1 && nums[i] == nums[i+1]) i++;
        }
        return result;
    }
}
```