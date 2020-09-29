package com.zhd.shard.lee.s0929;

import java.util.ArrayList;
import java.util.List;

import com.zhd.shard.lee.s0821.TreeNode;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-29
 */
public class Solution1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list;
    }

    public void dfs(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        dfs(node.left, list);
        dfs(node.right, list);
        list.add(node.val);
    }
}
