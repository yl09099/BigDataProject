package com.mini.btree;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaodou on 2018/12/2911:43.
 * 关于二叉树的各种方法
 */

public class BTreeUtils {


    /**
     * 通过一个数据构建一棵按顺序存放二叉树
     *
     * @param datas
     * @return
     */
    public List<BTreeNode> createCommonBTree(int[] datas) {

        List<BTreeNode> dataList = new ArrayList<BTreeNode>();

        for (int k : datas) {
            dataList.add(new BTreeNode(k));
        }

        for (int i = 0; i < dataList.size() / 2 - 1; i++) {
            dataList.get(i).setLeftNode(dataList.get(2 * i + 1));
            dataList.get(i).setRightNode(dataList.get(2 * i + 2));
        }
        int j = dataList.size() / 2 - 1;
        dataList.get(j).setLeftNode(dataList.get(2 * j + 1));
        if (dataList.size() % 2 == 1) {
            dataList.get(j).setRightNode(dataList.get(2 * j + 2));
        }
        return dataList;
    }


    /**
     * 先序遍历
     *
     * @param rootNode
     */
    public void rootSelect(BTreeNode rootNode) {
        if (rootNode != null) {
            System.out.print(rootNode.getData() + " ");
            rootSelect(rootNode.getLeftNode());
            rootSelect(rootNode.getRightNode());
        }
    }

    /**
     * 中序遍历
     *
     * @param rootNode
     */
    public void leftSelect(BTreeNode rootNode) {
        if (rootNode != null) {
            leftSelect(rootNode.getLeftNode());
            System.out.print(rootNode.getData() + " ");
            leftSelect(rootNode.getRightNode());
        }
    }

    /**
     * 后续遍历
     *
     * @param rootNode
     */
    public void rightSelect(BTreeNode rootNode) {
        if (rootNode != null) {
            rightSelect(rootNode.getLeftNode());
            rightSelect(rootNode.getRightNode());
            System.out.print(rootNode.getData() + " ");
        }
    }


}
