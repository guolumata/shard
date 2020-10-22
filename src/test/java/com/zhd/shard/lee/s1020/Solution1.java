package com.zhd.shard.lee.s1020;

import com.zhd.shard.lee.s0825.ListNode;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-20
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/reorder-list/
     */
    public void reorderList(ListNode head) {
        ListNode tmp = head;
        int length = 0;
        while (tmp != null) {
            length++;
            tmp = tmp.next;
        }
        if (length < 3) {
            return;
        }
        int half = (length - 1) / 2;
        tmp = head;
        ListNode other = null;
        while (true) {
            tmp = tmp.next;
            half--;
            if (half <= 0) {
                other = tmp.next;
                tmp.next = null;
                break;
            }
        }
    }
}
