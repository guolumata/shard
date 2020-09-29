package com.zhd.shard.lee.s0905;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-05
 */
public class Solution1 {
    private Map<Integer, Integer> map = new HashMap<>();
    private boolean[] nums;

    /**
     * https://leetcode-cn.com/problems/permutation-sequence/
     */
    public String getPermutation(int n, int k) {
        nums = new boolean[n];
        int tmp = 1;
        map.put(0, tmp);
        for (int i = 1; i <= n; i++) {
            tmp = tmp * i;
            map.put(i, tmp);
        }
        k = k - 1;
        String ans = "";
        for (int i = n; i > 0; i--) {
            int index = k / map.get(i - 1);
            k = k % map.get(i - 1);
            int num = getNum(index);
            ans += num;
        }
        return ans;
    }

    /**
     * 第几个为false的位置
     * count>=0;
     */
    public int getNum(int count) {
        int num = 0;
        count = count + 1;
        for (int i = 0; i < nums.length; i++) {
            if (!nums[i]) {
                num++;
            }
            if (num == count) {
                nums[i] = true;
                return i + 1;
            }
        }
        return 0;
    }
}
