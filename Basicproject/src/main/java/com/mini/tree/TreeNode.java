package com.mini.tree;

/**
 * 构建二叉树节点
 */
public class TreeNode {
    private Integer data;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode() {
    }

    public Integer getData() {
        return data;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
