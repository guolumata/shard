package com.zhd.shard.lee.s0821;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-21
 */
public class Solution2 {
    /**
     * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
