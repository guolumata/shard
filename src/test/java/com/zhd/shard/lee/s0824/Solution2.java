package com.zhd.shard.lee.s0824;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;


/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-24
 */
public class Solution2 {
    public static void main(String[] args) {
        //        int[] nums = {84, -48, -33, -34, -52, 72, 75, -12, 72, -45};
        //        List<List<Integer>> l2 = new Solution2().findSubsequences(nums);
        List<Integer> l1 = Lists.newArrayList(-52, 75);
        List<Integer> l2 = Lists.newArrayList(-34, 72, 75);
        System.out.println(l1.hashCode());
        System.out.println(l2.hashCode());
    }

    /**
     * https://leetcode-cn.com/problems/increasing-subsequences/
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();
        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> tmp = findSubsequences(i, nums);
            int hashcode = tmp.hashCode();
            if (check(tmp)) {
                if (!set.contains(hashcode)) {
                    ans.add(tmp);
                    set.add(hashcode);
                } else if (!ans.contains(tmp)) {
                    ans.add(tmp);
                }
            }
        }
        return ans;
    }


    public List<Integer> findSubsequences(int mask, int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((mask & 1) != 0) {
                list.add(nums[i]);
            }
            mask = mask >> 1;
        }
        return list;
    }

    public boolean check(List<Integer> list) {
        if (list.size() < 2) {
            return false;
        }
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
}
