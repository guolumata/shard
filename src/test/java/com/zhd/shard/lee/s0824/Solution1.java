package com.zhd.shard.lee.s0824;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-24
 */
public class Solution1 {

    public static void main(String[] args) {
        String s = "abcabcabcabc";
        System.out.println(new Solution1().repeatedSubstringPattern(s));
    }


    /**
     * https://leetcode-cn.com/problems/repeated-substring-pattern/
     */
    public boolean repeatedSubstringPattern(String s) {
        int length = s.length();
        if (length <= 1) {
            return false;
        }
        for (int i = 1; i < length; i++) {
            int step = i - 0;
            if (length % step == 0) {
                int count = length / step;
                int j = 1;
                String tmp = s.substring(0, i);
                while (j < count) {
                    if (!tmp.equals(s.substring(step * j, step * (j + 1)))) {
                        break;
                    }
                    j++;
                }
                if (j == count) {
                    return true;
                }
            }
        }
        return false;
    }
}
