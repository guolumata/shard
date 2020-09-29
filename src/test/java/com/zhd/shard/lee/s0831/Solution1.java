package com.zhd.shard.lee.s0831;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;


/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-31
 */
public class Solution1 {
    public static void main(String[] args) {
        List<List<Integer>> rooms =
                Lists.newArrayList(Lists.newArrayList(1, 3), Lists.newArrayList(3, 0, 1), Lists.newArrayList(2),
                        Lists.newArrayList(0));
        new Solution1().canVisitAllRooms(rooms);
    }

    private List<Integer> visited = new ArrayList<>();

    /**
     * https://leetcode-cn.com/problems/keys-and-rooms/
     */
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visit(rooms, 0);
        return visited.size() == rooms.size();
    }

    public void visit(List<List<Integer>> rooms, int index) {
        if (visited.contains(index)) {
            return;
        }
        visited.add(index);
        List<Integer> keys = rooms.get(index);
        keys.forEach(key -> visit(rooms, key));
    }
}
