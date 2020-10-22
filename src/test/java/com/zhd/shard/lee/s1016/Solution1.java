package com.zhd.shard.lee.s1016;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-16
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
     */
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        int length = A.length;
        int left = 0;
        int right = length - 1;
        int index = length - 1;
        int[] ans = new int[length];
        while (left <= right) {
            int v1 = A[left] * A[left];
            int v2 = A[right] * A[right];
            if (v1 > v2) {
                ans[index] = v1;
                left++;
            } else {
                ans[index] = v2;
                right--;
            }
            index--;
        }
        return ans;
    }
}
