package com.zhd.shard.lee.s1022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-22
 */
public class Solution2 {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        new Solution2().partitionLabels(s);
    }

    /**
     * https://leetcode-cn.com/problems/partition-labels/
     */
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        Map<Character, List<Integer>> map = new HashMap<>();
        int length = S.length();
        for (int i = 0; i < S.length(); i++) {
            char tmp = S.charAt(i);
            if (map.containsKey(tmp)) {
                map.get(tmp).add(i);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                map.put(tmp, l);
            }
        }
        int begin = 0;
        while (begin < length) {
            int l = subString(S, map, begin);
            list.add(l);
            begin += l;
        }
        return list;
    }

    public int subString(String s, Map<Character, List<Integer>> map, int begin) {
        int length;
        char beginC = s.charAt(begin);
        int end = getMaxIndex(map, beginC);
        int tmpBegin = begin;
        while (tmpBegin < end) {
            int max = getMaxIndex(map, s.charAt(tmpBegin++));
            end = Math.max(end, max);
        }
        length = end - begin + 1;
        return length;
    }

    public int getMaxIndex(Map<Character, List<Integer>> map, char c) {
        List<Integer> l = map.get(c);
        return l.get(l.size() - 1);
    }
}
