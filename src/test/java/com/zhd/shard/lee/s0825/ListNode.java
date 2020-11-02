package com.zhd.shard.lee.s0825;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-25
 */
public class ListNode implements Comparable<ListNode> {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public int compareTo(ListNode o) {
        return val - o.val;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        System.out.println(n1.compareTo(n2));
    }
}
