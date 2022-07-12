package xyz.xcye.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qsyyke
 * @date Created in 2022/7/10 08:24
 */


public class BinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        HeroNode node6 = new HeroNode(6,"6吴用");
        HeroNode node7 = new HeroNode(7,"7吴用");
        HeroNode node8 = new HeroNode(8,"8吴用");
        HeroNode node9 = new HeroNode(9,"9吴用");
        HeroNode node10 = new HeroNode(10,"10吴用");
        HeroNode node11 = new HeroNode(11,"11吴用");

        //手动构建二叉树
        root.setLeft(node2);
        node2.setLeft(node7);
        node2.setRight(node6);
        node6.setRight(node10);
        node7.setLeft(node8);
        node7.setRight(node9);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        node4.setRight(node11);

        BinaryTree binaryTree = new BinaryTree(root);
        System.out.println("前序遍历");
        binaryTree.preList();

        //System.out.println("中序遍历");
        //binaryTree.middleList();
        //
        //System.out.println("后续遍历");
        //binaryTree.nextList();

        System.out.println("--------------线索二叉树");
        //Demo
        HeroNode root1 = new HeroNode(1,"1name");
        HeroNode heroNode3 = new HeroNode(3,"3name");
        HeroNode heroNode8 = new HeroNode(8,"8name");
        HeroNode heroNode6 = new HeroNode(6,"6name");
        HeroNode heroNode10 = new HeroNode(10,"10name");
        HeroNode heroNode14 = new HeroNode(14,"14name");

        root1.setRight(heroNode6);
        heroNode6.setLeft(heroNode14);

        root1.setLeft(heroNode3);
        heroNode3.setLeft(heroNode8);
        heroNode3.setRight(heroNode10);

        BinaryTree tree = new BinaryTree(root1);
        tree.clueBinaryTreeByPre();
        System.out.println(root);

    }
}

@Data
class BinaryTree {
    private HeroNode root;

    private List<HeroNode> preListStorage;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public void preList() {
        preListStorage = new ArrayList<>();
        if (this.root == null) {
            return;
        }
        root.preList(preListStorage);
        System.out.println();
    }

    public void middleList() {
        if (this.root == null) {
            return;
        }
        root.middleList();
    }

    public void nextList() {
        if (this.root == null) {
            return;
        }
        root.nextList();
    }

    public void clueBinaryTreeByPre() {
        if (this.root == null) {
            return;
        }
        //root.clueBinaryTreeByPre(root);
        root.threadedNodes(root);
    }
}

@Data
class HeroNode {
    private int id;
    private String name;
    /**
     * 左节点
     **/
    private HeroNode left;
    /**
     * 右节点
     **/
    private HeroNode right;

    /**
     * 以下两个为线索二叉树标志
     */
    private int leftType;
    private int rightType;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void preList(List<HeroNode> preListStorage) {
        System.out.print("(" + this.id + "," + this.name + ") ");
        preListStorage.add(this);
        if (this.left != null) {
            // 存在左子树
            this.left.preList(preListStorage);
        }
        if (this.right != null) {
            // 存在右子树
            this.right.preList(preListStorage);
        }
    }

    HeroNode preNode;

    //线索化二叉树
    public void threadedNodes(HeroNode node) {
        if(node == null) {
            return;
        }
        threadedNodes(node.getLeft());
        if(node.getLeft() == null) {
            node.setLeft(preNode);
            node.setLeftType(1);
        }

        if (preNode != null && preNode.getRight() == null) {
            preNode.setRight(node);
            preNode.setRightType(1);
        }
        preNode = node;
        threadedNodes(node.getRight());
    }

    public void clueBinaryTreeByPre(HeroNode preNode) {
        if (this.left != null) {
            // 存在左子树
            this.leftType = 0;
            this.left.clueBinaryTreeByPre(this);
        }else {
            this.leftType = 1;
            this.left = preNode;
        }
        if (this.right != null) {
            // 存在右子树
            this.rightType = 0;
            this.right.clueBinaryTreeByPre(this);
        }else {
            this.rightType = 1;
            this.right = preNode;
        }
    }

    public void middleList() {
        if (this.left != null) {
            this.left.middleList();
        }
        System.out.println(this.id + "--->" + this.name);
        if (this.right != null) {
            this.right.middleList();
        }
    }

    public void nextList() {
        if (this.left != null) {
            this.left.nextList();
        }
        if (this.right != null) {
            this.right.nextList();
        }
        System.out.println(this.id + "--->" + this.name);
    }
}
