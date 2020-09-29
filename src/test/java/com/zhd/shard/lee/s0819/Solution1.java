package com.zhd.shard.lee.s0819;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-19
 */
public class Solution1 {

    public static void main(String[] args) {
        String str = "aaa";
        Solution1 s = new Solution1();
        int count = s.countSubstrings(str);
        System.out.println(count);
    }

    // https://leetcode-cn.com/problems/palindromic-substrings/
    public int countSubstrings(String s) {
        int count = 0;
        if (s == null || s.length() <= 0) {
            return count;
        }
        int length = s.length();
        for (int i = 0; i <= length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (match(s.substring(i, j))) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean match(String s) {
        int length = s.length();
        if (length < 1) {
            return false;
        }
        int i = 0;
        int j = length - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
