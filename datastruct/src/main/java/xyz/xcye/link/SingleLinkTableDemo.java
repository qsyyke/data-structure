package xyz.xcye.link;

import lombok.Data;

/**
 * @author qsyyke
 * @date Created in 2022/7/8 22:46
 */


public class SingleLinkTableDemo {
    public static void main(String[] args) {
        StudentNode aurora = new StudentNode("aurora", 1);
        StudentNode qsyyke = new StudentNode("qsyyke", 2);
        StudentNode haha = new StudentNode("haha", 3);

        SingleLinkTable linkTable = new SingleLinkTable();
        linkTable.addNode(aurora);
        linkTable.addNode(qsyyke);
        linkTable.addNode(haha);
        linkTable.listNode();

        StudentNode after = new StudentNode("after", 4);
        linkTable.addAfterNode(2, after);
        linkTable.listNode();

        linkTable.deleteNode(1);
        linkTable.listNode();
    }
}

@Data
class StudentNode {
    private String name;
    private int id;
    private StudentNode nextNode;


    public StudentNode(String name, int id) {
        this.name = name;
        this.id = id;
    }
}

@Data
class SingleLinkTable {
    private StudentNode headNode = new StudentNode("头结点",0);

    public void addNode(StudentNode node) {
        StudentNode tempNode = headNode;
        while (tempNode.getNextNode() != null) {
            tempNode = tempNode.getNextNode();
        }
        tempNode.setNextNode(node);
    }

    public void addAfterNode(int id, StudentNode node) {
        // 查看是否存在该链表
        StudentNode tempNode = headNode;
        while (tempNode.getNextNode() != null) {
            tempNode = tempNode.getNextNode();
            if (tempNode.getId() == id) {
                StudentNode afterNode = tempNode.getNextNode();
                tempNode.setNextNode(node);
                node.setNextNode(afterNode);
            }
        }
    }

    public void deleteNode(int id) {
        StudentNode tempNode = headNode;
        while (tempNode.getNextNode() != null) {
            if (tempNode.getNextNode().getId() == id) {
                tempNode.setNextNode(tempNode.getNextNode().getNextNode());
            }
            tempNode = tempNode.getNextNode();
        }
    }

    public void listNode() {
        StudentNode tempNode = headNode;
        while (tempNode != null) {
            System.out.print(tempNode.getName() + ":"
                    + tempNode.getId() + " ---> ");
            tempNode = tempNode.getNextNode();
        }
        System.out.println();
    }
}
