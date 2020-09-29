package com.zhd.shard.lee.s0908;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-08
 */
public class Solution1 {
    public static void main(String[] args) {
        new Solution1().combine(4, 2);
    }

    /**
     * https://leetcode-cn.com/problems/combinations/
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans = chose(n, ans);
        }
        return ans;
    }

    public List<List<Integer>> chose(int n, List<List<Integer>> list) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (list.isEmpty()) {
                List<Integer> tmp = new ArrayList<>();
                tmp.add(i);
                ans.add(tmp);
            } else {
                for (List<Integer> l : list) {
                    if (l.contains(i)) {
                        continue;
                    }
                    if (l.get(l.size() - 1) < i) {
                        continue;
                    }
                    List<Integer> tmp = new ArrayList<>();
                    tmp.addAll(l);
                    tmp.add(i);
                    ans.add(tmp);
                }
            }
        }
        return ans;
    }

}
