---
title: RemoveDuplicatesFromSortedArray
description: RemoveDuplicatesFromSortedArray
date: 2021-05-10 09:11:37
tags:
categories:
- algo
---

# Remove Duplicates from Sorted Array

Given a sorted array nums, remove the duplicates in-place such
 that each element appears only once and returns the new length.

Do not allocate extra space for another array, you must do this 
by modifying the input array in-place with O(1) extra memory.


Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2]
Explanation: Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the returned length.

Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4]
Explanation: Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively. It doesn't matter what values are set beyond the returned length.


# 1
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int N = nums.length;
        if (N == 0 || N == 1) return N;
        int start = 0;
        for (int i=0, j=0; j < N;) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[++start] = nums[j];
                i = j;
            }
        }
        return start + 1;
    }
}
```

# 2 
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int N = nums.length;
        if (N == 0 || N == 1) return N;
        int i = 0; // represent count  
        for (int j = 1; j < N; j++) {
            if (nums[j] != nums[i]) { // this is different way to deal
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
```
