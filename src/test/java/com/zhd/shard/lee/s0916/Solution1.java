package com.zhd.shard.lee.s0916;

import com.zhd.shard.lee.s0821.TreeNode;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-16
 */
public class Solution1 {
    public TreeNode invertTree(TreeNode root) {
        reverse(root);
        return root;
    }

    public void reverse(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            reverse(root.left);
            reverse(root.right);
        }
    }
}
