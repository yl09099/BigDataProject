package com.mini.btree;

/**
 * Created by xiaodou on 2018/12/2911:39.
 */

public class BTreeNode {
    /**
     * 定义一个数据存储变量
     */
    private int data;
    /**
     * 定义一个左节点
     */
    private BTreeNode leftNode;
    /**
     * 定义一个有节点
     */
    private BTreeNode rightNode;

    public BTreeNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public BTreeNode getLeftNode() {
        return leftNode;
    }

    public BTreeNode getRightNode() {
        return rightNode;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeftNode(BTreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(BTreeNode rightNode) {
        this.rightNode = rightNode;
    }
}
