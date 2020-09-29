package com.zhd.shard.lee.s0826;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-26
 */
public class Solution2 {
    public static void main(String[] args) {
        int[][] nums = {
                {1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1}
        };
        System.out.println(new Solution2().accessible(nums, 0, 0, 4, 4));
    }

    public int[] x_off = {-1, -1, -1, 0, 0, 1, 1, 1};
    public int[] y_off = {-1, 0, 1, -1, 1, -1, 0, 1};
    public List<Location> visited = new LinkedList<>();

    /**
     * 给出任意两点，判断是否可达
     * 深度遍历
     */
    public boolean accessible(int[][] nums, int startX, int startY, int endX, int endY) {
        Location start = new Location(startX, startY);
        Location end = new Location(endX, endY);
        visit(nums, start);
        return visited.contains(end);
    }

    public void visit(int[][] nums, Location location) {
        visited.add(location);
        List<Location> around = around(nums, location);
        if (around.isEmpty()) {
            return;
        }
        for (Location tmp : around) {
            if (visited.contains(tmp)) {
                continue;
            }
            visit(nums, tmp);
        }
    }


    /**
     * 周围可选的路
     */
    public List<Location> around(int[][] nums, Location location) {
        List<Location> list = new ArrayList<>();
        int val = getValue(nums, location.x, location.y);
        if (val < 0) {
            return list;
        }
        for (int i = 0; i < 8; i++) {
            int tmp = getValue(nums, location.x + x_off[i], location.y + y_off[i]);
            if (tmp == 1) {
                list.add(new Location(location.x + x_off[i], location.y + y_off[i]));
            }
        }
        return list;
    }

    public int getValue(int[][] nums, int x, int y) {
        if (x < 0 || x >= nums.length || y < 0 || y >= nums[0].length) {
            return -1;
        }
        return nums[x][y];
    }
}
