package com.mini.tree;

/**
 * Created by xiaodou on 2018/12/2413:47.
 */

public class TreeOperator {
    /**
     * 找出二叉树的最大深度
     */
    public int maxDepth(TreeNode rootNode) {
        if (rootNode == null) {
            return 0;
        }
        int leftDepth = maxDepth(rootNode.getLeftNode()) + 1;
        int rightDepth = maxDepth(rootNode.getRightNode()) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 找出二叉树的最小深度
     *
     * @param rootNode
     * @return
     */
    public int minDepth(TreeNode rootNode) {

        if (rootNode == null) {
            return 0;
        }
        if (rootNode.getLeftNode() == null) {
            return minDepth(rootNode.getRightNode()) + 1;
        }
        if (rootNode.getRightNode() == null) {
            return minDepth(rootNode.getLeftNode()) + 1;
        }
        return Math.min(minDepth(rootNode.getLeftNode()), minDepth(rootNode.getRightNode())) + 1;
    }


}
