package com.zhd.shard.lee.s0828;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-30
 */
public class Solution2 {
    public static void main(String[] args) {
        String s = "Let's take LeetCode contest";
        System.out.println(new Solution2().reverseWords(s));
    }

    /**
     * https://leetcode-cn.com/problems/reverse-words-in-a-string-iii/
     */
    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }
        String ans = "";
        int start = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == ' ') {
                ans += reverse(s, start, i);
                ans += s.charAt(i);
                start = i + 1;
            }
        }
        ans += reverse(s, start, length);
        return ans;
    }

    public String reverse(String s, int begin, int end) {
        if (s == null || s == " ") {
            return s;
        }
        String s1 = "";
        for (int i = end - 1; i >= begin; i--) {
            s1 += s.charAt(i);
        }
        return s1;
    }
}
