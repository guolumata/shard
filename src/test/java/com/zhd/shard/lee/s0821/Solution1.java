package com.zhd.shard.lee.s0821;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-21
 */
public class Solution1 {

    /**
     * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int b1 = minDepth(root.left);
        int b2 = minDepth(root.right);
        if (b1 == 0 || b2 == 0) {
            return Math.max(b1, b2) + 1;
        }
        return Math.min(b1, b2) + 1;
    }
}
