package com.zhd.shard.lee.s0825;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-25
 */
public class Solution1 {
    public static void main(String[] args) {

    }

    public ListNode mergeKLists(ListNode[] lists) {
        int length = lists.length;
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return lists[0];
        }
        ListNode head = lists[0];
        for (int i = 1; i < length; i++) {
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }


    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int val = l1.val < l2.val ? l1.val : l2.val;
        ListNode head = new ListNode(val, l1);
        ListNode pre = head;
        ListNode next = pre.next;
        while (l2 != null) {
            if (next == null) {
                pre.next = l2;
                break;
            }
            if (l2.val >= pre.val && l2.val <= next.val) {
                ListNode tmp = l2.next;
                pre.next = l2;
                l2.next = next;
                l2 = tmp;
                pre = pre.next;
            } else {
                pre = next;
                next = pre.next;
            }
        }
        return head.next;
    }
}
