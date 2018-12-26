package com.mini.tree;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new LinkedList<TreeNode>();
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> res = new LinkedList<TreeNode>();
        if (start > end) {
            res.add(null);
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> subLeftTree = generateTrees(start, i - 1);
            List<TreeNode> subRightTree = generateTrees(i + 1, end);
            for (TreeNode left : subLeftTree) {
                for (TreeNode right : subRightTree) {
                    TreeNode node = new TreeNode(i);
                    node.setLeftNode(left);
                    node.setRightNode(right);
                    res.add(node);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> lists = solution.generateTrees(3);
        for (TreeNode t : lists) {
            firstprint(t);
        }
    }

    public static void firstprint(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.getData() + " ");
            firstprint(treeNode.getLeftNode());
            firstprint(treeNode.getRightNode());
        }
    }
}
