package com.zhd.shard.lee.s0824;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-24
 */
public class Solution3 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        new Solution3().threeSum(nums);
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Set<Integer>> list = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        Set<Integer> tmp = new HashSet<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        list.add(tmp);
                    }
                }
            }
        }
        for (Set<Integer> s : list) {
            List<Integer> l = new ArrayList<>(s);
            ans.add(l);
        }
        return ans;
    }
}
