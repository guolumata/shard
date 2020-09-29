package com.zhd.shard.lee.s0819;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-19
 */
public class Solution2 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        double ans = new Solution2().findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int[] nums = new int[l1 + l2];
        int i = 0;
        int j = 0;
        while (i + j < l1 + l2) {
            if (i >= l1) {
                nums[i + j] = nums2[j];
                j++;
                continue;
            }
            if (j >= l2) {
                nums[i + j] = nums1[i];
                i++;
                continue;
            }
            if (nums1[i] < nums2[j]) {
                nums[i + j] = nums1[i];
                i++;
            } else {
                nums[i + j] = nums2[j];
                j++;
            }
        }
        int index = (l1 + l2) / 2;
        double ans = (l1 + l2) % 2 == 0 ? (double) (nums[index] + nums[index - 1]) / 2 : nums[index];
        return ans;
    }
}
