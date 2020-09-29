package com.zhd.shard.lee.s0827;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-27
 */
public class Solution1 {

    private Map<String, PriorityQueue<String>> map = new HashMap<>();

    private List<String> ans = new ArrayList<>();

    /**
     * 最小堆实现
     * https://leetcode-cn.com/problems/reconstruct-itinerary/
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            if (map.containsKey(src)) {
                map.get(src).offer(ticket.get(1));
            } else {
                PriorityQueue queue = new PriorityQueue();
                queue.offer(ticket.get(1));
                map.put(src, queue);
            }
        }
        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    public void dfs(String src) {
        while (map.containsKey(src) && map.get(src).size() > 0) {
            String tmp = map.get(src).poll();
            dfs(tmp);
        }
        ans.add(src);
    }

    public List<String> getTicketByFrom(List<List<String>> lists, String from) {
        List<List<String>> tmp = new ArrayList<>();
        for (List<String> list : lists) {
            if (list.get(0).equals(from)) {
                tmp.add(list);
            }
        }
        List<String> ans = null;
        if (tmp.size() == 1) {
            ans = tmp.get(0);
            lists.remove(ans);
            return ans;
        }
        //有下一站票 且 值小
        for (List<String> list : tmp) {
            for (List<String> list2 : lists) {
                if (list.get(1).equals(list2.get(0))) {
                    if (ans == null) {
                        ans = list;
                    } else if (list.get(1).compareTo(ans.get(1)) < 0) {
                        ans = list;
                    }
                }
            }
        }
        lists.remove(ans);
        return ans;
    }
}
