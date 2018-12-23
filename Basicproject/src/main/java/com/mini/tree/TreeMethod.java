package com.mini.tree;

public class TreeMethod {
    /**
     * 创建一棵二叉树
     * @param data
     * @return
     */
    public static TreeNode create_bTree(int data){
        TreeNode newTree = new TreeNode();
        newTree.setData(data);
        newTree.setLeftNode(null);
        newTree.setRightNode(null);
        return newTree;
    }





}
