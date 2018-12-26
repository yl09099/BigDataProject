package com.mini.tree;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] arrays = {1, 3, 6, 8, 9, 23, 56, 78, 100};

    public static void firstprint(TreeNode treeNode) {
        if (treeNode != null) {
            System.out.print(treeNode.getData() + " ");
            firstprint(treeNode.getLeftNode());
            firstprint(treeNode.getRightNode());
        }
    }

    public static void secondprint(TreeNode treeNode) {
        if (treeNode != null) {
            secondprint(treeNode.getLeftNode());
            System.out.print(treeNode.getData() + " ");
            secondprint(treeNode.getRightNode());
        }
    }

    public static void thirdprint(TreeNode treeNode) {
        if (treeNode != null) {
            thirdprint(treeNode.getLeftNode());
            thirdprint(treeNode.getRightNode());
            System.out.print(treeNode.getData() + " ");
        }
    }

    public static void main(String[] args) {
        List<TreeNode> listNode = new ArrayList<TreeNode>();
        for (int arr : arrays) {
            listNode.add(TreeMethod.create_bTree(arr));
        }
        if (listNode.size() > 0) {
            for (int i = 0; i < arrays.length / 2 - 1; i++) {
                if (listNode.get(2 * i + 1) != null) {
                    listNode.get(i).setLeftNode(listNode.get(2 * i + 1));
                }
                if (listNode.get(2 * i + 2) != null) {
                    listNode.get(i).setRightNode(listNode.get(2 * i + 2));
                }
            }
            int middle = listNode.size() / 2 - 1;
            System.out.println("middle=" + middle);
            listNode.get(middle).setLeftNode(listNode.get(middle * 2 + 1));
            if (listNode.size() % 2 == 1) {
                listNode.get(middle).setRightNode(listNode.get(middle * 2 + 2));
            }
        }
        firstprint(listNode.get(0));
        System.out.println();
        secondprint(listNode.get(0));
        System.out.println();
        thirdprint(listNode.get(0));


    }
}
