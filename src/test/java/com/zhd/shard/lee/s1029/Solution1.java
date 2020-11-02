package com.zhd.shard.lee.s1029;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.zhd.shard.lee.s0821.TreeNode;

/**
 * @author zhanghongda <zhanghongda@kuaishou.com>
 * Created on 2020-10-29
 */
public class Solution1 {
    /**
     * https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/
     */
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode node, int parentVal) {
        if (node == null) {
            return parentVal;
        }
        parentVal = parentVal * 10 + node.val;
        if (node.left == null && node.right == null) {
            return parentVal;
        } else if (node.left != null && node.right == null) {
            parentVal = dfs(node.left, parentVal);
        } else if (node.left == null && node.right != null) {
            parentVal = dfs(node.right, parentVal);
        } else {
            int left = dfs(node.left, parentVal);
            int right = dfs(node.right, parentVal);
            parentVal = left + right;
        }
        return parentVal;
    }

    /**
     * https://leetcode-cn.com/problems/intersection-of-two-arrays/
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums2.length < nums1.length) {
            int[] tmp = nums1;
            nums1 = nums2;
            nums2 = tmp;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (set.contains(i)) {
                list.add(i);
            }
        }
        int[] tmp = list.stream().mapToInt(Integer::valueOf).toArray();
        return tmp;
    }
}
