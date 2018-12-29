package com.mini;

import com.mini.btree.BTreeNode;
import com.mini.btree.BTreeUtils;
import org.junit.Test;

import java.util.List;

/**
 * Created by xiaodou on 2018/12/2914:09.
 */

public class BTreeUtilsTest {
    @Test
    public void createCommonBTreeTest() {
        BTreeUtils bTreeUtils = new BTreeUtils();
        int[] datas = {1, 3, 4, 5, 7, 8, 45, 34, 67};
        List<BTreeNode> list = bTreeUtils.createCommonBTree(datas);
        bTreeUtils.rightSelect(list.get(0));
    }
}
