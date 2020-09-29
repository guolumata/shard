package com.zhd.shard.lee.s0826;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-26
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null) {
            return ans;
        }
        for (int i = 0; i < digits.length(); i++) {
            List<String> chars = trans(digits.charAt(i));
            ans = combination(ans, chars);
        }
        return ans;
    }

    public List<String> trans(char c) {
        List<String> list = new ArrayList<>();
        if ('2' == c) {
            list.add("a");
            list.add("b");
            list.add("c");
        } else if ('3' == c) {
            list.add("d");
            list.add("e");
            list.add("f");
        } else if ('4' == c) {
            list.add("g");
            list.add("h");
            list.add("i");
        } else if ('5' == c) {
            list.add("j");
            list.add("k");
            list.add("l");
        } else if ('6' == c) {
            list.add("m");
            list.add("n");
            list.add("o");
        } else if ('7' == c) {
            list.add("p");
            list.add("q");
            list.add("r");
            list.add("s");
        } else if ('8' == c) {
            list.add("t");
            list.add("u");
            list.add("v");
        } else if ('9' == c) {
            list.add("w");
            list.add("x");
            list.add("y");
            list.add("z");
        }
        return list;
    }

    public List<String> combination(List<String> list, List<String> chars) {
        if (chars == null || chars.size() < 1) {
            return list;
        }
        if (list == null || list.size() < 1) {
            return chars;
        }
        List<String> ans = new ArrayList<>();
        for (String aChar : chars) {
            list.forEach(s -> ans.add(s + aChar));
        }
        return ans;
    }
}
