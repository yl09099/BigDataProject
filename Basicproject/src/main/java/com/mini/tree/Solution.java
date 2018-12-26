package com.mini.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiaodou on 2018/12/2510:38.
 */

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
                    System.out.println("这时的i为："+i);
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
        List<TreeNode> ls = solution.generateTrees(3);
        for (TreeNode t : ls) {
            xianxu(t);
            System.out.println();
        }
    }

    public static void xianxu(TreeNode rootNode) {
        if (rootNode != null) {
            System.out.print(rootNode.getData());
            xianxu(rootNode.getLeftNode());
            xianxu(rootNode.getRightNode());
        }
    }

}