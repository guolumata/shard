package com.zhd.shard.lee.s0819;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-19
 */
public class Solution3 {
    /**
     * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
     */
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int ans = 0;
        Set<Character> set = null;
        for (int i = 0; i < length; i++) {
            int rk = i;
            set = new HashSet<>();
            //窗口滑动
            while (rk < length && !set.contains(s.charAt(rk))) {
                set.add(s.charAt(rk));
                rk++;
            }
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        int ans = 0;
        for (int i = 0; i <= length; i++) {
            for (int j = length; j > i; j--) {
                String sub = s.substring(i, j);
                int l = sub.length();
                if (ans >= l) {
                    continue;
                }
                if (norepeat(sub)) {
                    ans = l;
                }
            }
        }
        return ans;
    }

    private boolean norepeat(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set.size() == s.length();
    }
}
