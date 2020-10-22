package com.zhd.shard.lee.s1022;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-22
 */
public class Solution1 {
    public static void main(String[] args) {
        minHeap();
    }

    public static void minHeap() {
        PriorityQueue<Integer> queue = new PriorityQueue<>(5, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        add(queue);
        it(queue);
    }

    public static void add(PriorityQueue<Integer> queue) {
        int i = 7;
        Random random = new Random();
        System.out.println("=add===============");
        while (i-- > 0) {
            int tmp = random.nextInt(100);
            System.out.println(tmp);
            queue.add(tmp);
        }
        System.out.println("=add===============");
    }

    public static void it(PriorityQueue<Integer> queue) {
        System.out.println("=it===============");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
        System.out.println("=it===============");
    }
}
