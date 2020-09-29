package com.zhd.shard.lee.s0821;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-08-21
 */
public class Solution3 {

    /**
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<TreeNode> list = new ArrayList<>();
        list.add(root);
        ans.add(vals(list));
        while (true) {
            List<TreeNode> children = children(list);
            if (children.size() <= 0) {
                break;
            }
            ans.add(vals(children));
            list = children;
        }
        return ans;
    }

    public List<TreeNode> children(List<TreeNode> list) {
        List<TreeNode> children = new ArrayList<>();
        list.forEach(p -> {
                    if (p != null) {
                        if (p.left != null) {
                            children.add(p.left);
                        }
                        if (p.right != null) {
                            children.add(p.right);
                        }
                    }
                }
        );
        return children;
    }

    public List<Integer> vals(List<TreeNode> list) {
        List<Integer> vals = new ArrayList<>();
        list.forEach(n -> vals.add(n.val));
        return vals;
    }
}
