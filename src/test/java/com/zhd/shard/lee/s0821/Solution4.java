package com.zhd.shard.lee.s0821;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-21
 */
public class Solution4 {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.substring(0, 3));
    }

    /**
     * https://leetcode-cn.com/problems/longest-palindromic-substring/
     */
    public String longestPalindrome(String s) {
        int length = s.length();
        if (length < 1) {
            return "";
        }
        String ans = s.substring(0, 1);
        for (int i = 0; i < length; i++) {
            for (int j = length; j > i; j--) {
                if (j - i < ans.length()) {
                    break;
                }
                if (judge(s, i, j - 1)) {
                    ans = s.substring(i, j);
                }
            }
        }
        return ans;
    }

    public boolean judge(String s, int head, int tail) {
        if (head > tail) {
            return false;
        }
        while (head <= tail) {
            if (s.charAt(head) != s.charAt(tail)) {
                return false;
            }
            head++;
            tail--;
        }
        return true;
    }
}
