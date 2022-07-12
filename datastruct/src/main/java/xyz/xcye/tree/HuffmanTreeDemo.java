package xyz.xcye.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author qsyyke
 * @date Created in 2022/7/10 10:46
 */


public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        Node huffmanTree = new HuffmanTree().createHuffmanTree(arr);
        System.out.println(huffmanTree);
    }
}

class HuffmanTree {

    public Node createHuffmanTree(int[] arr) {
        List<Node> nodeList = new ArrayList<>();

        //1.将节点放入集合中
        for (int value : arr) {
            nodeList.add(new Node(value));
        }

        //2.对集合中的元素进行排序
        Collections.sort(nodeList);

        /*
         * 依次取出最小的一个node节点和次小的node节点，然后将他们重新组成一个新的node节点，
         * 该新node节点的左右子树分别为最小的node节点和次小node节点
         * */

        //当该nodeList集合长度小于1时，就完成了
        while (nodeList.size() > 1) {

            //最小和次小的组合成一个新node节点
            Node parentNode = new Node(nodeList.get(0).getValue() + nodeList.get(1).getValue());

            parentNode.setLeft(nodeList.get(0));
            parentNode.setRight(nodeList.get(1));

            //重新将该parentNode加入到集合中
            nodeList.add(parentNode);

            //从nodeList集合中，移除最小的和次小的node节点
            nodeList.remove(0);
            nodeList.remove(0);

            //重新排序
            Collections.sort(nodeList);
        }

        return nodeList.get(0);
    }
}

@Data
class Node implements Comparable<Node> {

    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}
