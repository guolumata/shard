package com.zhd.shard.lee.s1019;

import java.util.Stack;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-19
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/backspace-string-compare/
     */
    public boolean backspaceCompare(String S, String T) {
        return opr(S).equals(opr(T));
    }

    public String opr(String s) {
        Stack<Character> stack = new Stack<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if ('#' == c) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }
        String tmp = "";
        while (!stack.isEmpty()) {
            tmp = stack.pop() + tmp;
        }
        return tmp;
    }
}
