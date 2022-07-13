package xyz.xcye.search;

import lombok.Data;

/**
 * 二叉排序树
 * @author qsyyke
 * @date Created in 2022/7/12 18:29
 */


public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {62,88,58,47,35,73,51,99,37,93};
        BinarySoreTree binarySoreTree = new BinarySoreTree();
        binarySoreTree.createBinaryTreeByInorder(arr);
        binarySoreTree.inorderTraversal();

        System.out.println(binarySoreTree.searchBST(12, binarySoreTree.getRoot()));
        binarySoreTree.insertKey(63);
        System.out.println(binarySoreTree.searchBST(63, binarySoreTree.getRoot()));
        binarySoreTree.deleteKey(37);
    }
}

@Data
class BinaryTreeNode {
    private int data;
    private BinaryTreeNode lChild, rChild;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int data) {
        this.data = data;
    }

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        if (this.getLChild() != null) {
            this.getLChild().inorderTraversal();
        }
        System.out.print(this.getData() + " ");
        if (this.getRChild() != null) {
            this.getRChild().inorderTraversal();
        }
    }
}

@Data
class BinarySoreTree {

    private BinaryTreeNode root;

    /**
     * 中序遍历
     */
    public void inorderTraversal() {
        if (root == null) {
            return;
        }
        root.inorderTraversal();
        System.out.println();
    }

    /**
     * 以中序遍历的方式创建二叉树
     * @param arr
     */
    public void createBinaryTreeByInorder(int[] arr) {
        if (arr.length > 0) {
            root = new BinaryTreeNode();
            root.setData(arr[0]);
        }
        for (int i = 1; i < arr.length; i++) {
            sort(arr[i], root);
        }
    }

    public void sort(int num, BinaryTreeNode node) {
        if (num < node.getData()) {
            if (node.getLChild() == null) {
                node.setLChild(new BinaryTreeNode(num));
            }else {
                sort(num, node.getLChild());
            }
        }else {
            if (node.getRChild() == null) {
                node.setRChild(new BinaryTreeNode(num));
            }else {
                sort(num, node.getRChild());
            }
        }
    }

    /**
     * 二叉排序树查找
     * @param key
     * @param node
     * @return
     */
    public BinaryTreeNode searchBST(int key, BinaryTreeNode node) {
        if (node == null) {
            return null;
        }
        if (node.getData() == key) {
            return node;
        }

        if (key < node.getData()) {
            // 往左查找
            return searchBST(key, node.getLChild());
        }else {
            return searchBST(key, node.getRChild());
        }
    }

    /**
     * 在二叉排序树中插入一个值
     * @param key
     */
    public void insertKey(int key) {
        // 查看是否含有该值
        if (searchBST(key, root) != null) {
            return;
        }

        // 不含有
        sort(key, root);
    }

    /**
     * 删除key值
     * @param key
     */
    public void deleteKey(int key) {
        BinaryTreeNode binaryTreeNode = searchBST(key, root);
        // 没有此key
        if (binaryTreeNode == null) {
            return;
        }

        BinaryTreeNode parentNode = searchParentNode(key, root, null);
        // 查看此key是否是叶子节点
        if (binaryTreeNode.getLChild() == null || binaryTreeNode.getRChild() == null) {
            // 是叶子节点
            if (parentNode.getLChild().getData() == key) {
                parentNode.setLChild(null);
            }else {
                parentNode.setRChild(null);
            }
        }

        // 查看是否仅有左子树或者右子树
        //if (parentNode.getLChild().getData() == key && parentNode) {
        //    // 仅有左子树
        //    parentNode.setLChild(parentNode.getLChild().getLChild());
        //}else if (parentNode.getRChild() != null && parentNode.getLChild() == null) {
        //    // 仅有右子树
        //    parentNode.setRChild(parentNode.getRChild().get);
        //}
    }

    private BinaryTreeNode searchParentNode(int key, BinaryTreeNode node, BinaryTreeNode parentNode) {
        if (key < node.getData()) {
            return searchParentNode(key, node.getLChild(), node);
        }else if (key > node.getData()) {
            return searchParentNode(key, node.getRChild(), node);
        }else {
            return parentNode;
        }
    }

}