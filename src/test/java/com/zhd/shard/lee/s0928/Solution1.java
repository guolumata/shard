package com.zhd.shard.lee.s0928;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-18
 */
public class Solution1 {

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        //layer
        while (queue.size() > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
