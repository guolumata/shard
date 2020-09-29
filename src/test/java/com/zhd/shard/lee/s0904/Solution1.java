package com.zhd.shard.lee.s0904;

import java.util.ArrayList;
import java.util.List;

import com.zhd.shard.lee.s0821.TreeNode;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-09-04
 */
public class Solution1 {
    private List<String> paths = new ArrayList<>();

    /**
     * https://leetcode-cn.com/problems/binary-tree-paths/
     */
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return paths;
        }
        String path = "" + root.val;
        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            if (root.left != null) {
                path(path, root.left);
            }
            if (root.right != null) {
                path(path, root.right);
            }
        }
        return paths;
    }

    public void path(String parentPath, TreeNode node) {
        parentPath = parentPath + "->" + node.val;
        if (node.left == null && node.right == null) {
            paths.add(parentPath);
            return;
        }
        if (node.left != null) {
            path(parentPath, node.left);
        }
        if (node.right != null) {
            path(parentPath, node.right);
        }
    }
}
