package xyz.xcye.graph;

import lombok.Data;

import java.util.*;

/**
 * @author qsyyke
 * @date Created in 2022/7/11 21:50
 */


public class GraphDemo {
    public static void main(String[] args) {
        Graph graph = new Graph();
        //graph.createMGraph();
        //graph.graphList();

        //System.out.println("进行深度优先遍历");
        //graph.dfsTravers();
        //System.out.println("进行广度优先遍历");
        //graph.bfsTravers();

        System.out.println("创建邻接表");
        graph.createALGraph();
        VertexNode[] adgList = graph.getAdgList();
        System.out.println("进行深度优先遍历");
        graph.nodeDFSTraverse();
        System.out.println("进行广度优先遍历");
        graph.nodeBFS();

    }
}

@Data
class Graph {
    /** 最大的顶点数 **/
    private int MAXVEX;

    /** 邻接矩阵 **/
    private int[][] arc;

    /** 顶点表 **/
    private String[] vexs;

    private VertexNode[] adgList;

    /** 顶点数和边数 **/
    private int numVertexes, numEdges;

    private boolean[] visited;

    public int[][] createMGraph() {
        System.out.println("请输入顶点数和边数");
        Scanner scanner = new Scanner(System.in);
        numVertexes = scanner.nextInt();
        numEdges = scanner.nextInt();

        // 初始化邻接矩阵
        arc = new int[numVertexes][numVertexes];
        vexs = new String[numVertexes];
        visited = new boolean[numVertexes];

        System.out.println("请输入顶点信息");
        for (int i = 0; i < numVertexes; i++) {
            vexs[i] = scanner.next();
        }

        System.out.println("你输入的顶点信息为" +
                String.join(",", vexs));

        System.out.println("请输入顶点(vi,vj)坐标和权值：有向");
        for (int i = 0; i < numEdges; i++) {
            String[] split = scanner.next().split(",");
            int firstPoint = Integer.parseInt(split[0]);
            int secondPoint = Integer.parseInt(split[1]);
            int graphValue = Integer.parseInt(split[2]);
            arc[firstPoint][secondPoint] = graphValue;
        }

        return arc;
    }

    public void graphList() {
        for (int i = 0; i < arc.length; i++) {
            for (int j = 0; j < arc.length; j++) {
                if (arc[i][j] != 0) {
                    System.out.println(vexs[i] + vexs[j] + "=" + arc[i][j] + " ");
                }
            }
        }
    }

    /**
     * 深度优先遍历相当于，从每一个顶点出发进行遍历
     * @param num
     */
    private void dfs(int num) {
        System.out.println(vexs[num]);
        visited[num] = true;
        for (int i = 0; i < arc.length; i++) {
            // 没有被访问过
            if (arc[num][i] != 0 && !visited[i]) {
                System.out.println(vexs[i]);
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历
     */
    public void dfsTravers() {
        for (int i = 0; i < numVertexes; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 广度优先遍历
     */
    public void bfsTravers() {
        Arrays.fill(visited, false);
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertexes; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.println(vexs[i]);

                // 将此节点入队列
                queue.add(i);

                // 遍历队列
                while (!queue.isEmpty()) {
                    // 从队列中弹出第一个
                    Integer firstVertexes = queue.removeFirst();
                    for (int j = 0; j < numVertexes; j++) {
                        if (arc[i][j] != 0 && !visited[j]) {
                            // 获取到没有访问到的顶点，放入队列中
                            queue.add(j);
                            visited[j] = true;
                            System.out.println(vexs[j]);
                        }
                    }
                }
            }
        }
    }


    // -----------邻接表创建和遍历
    public void createALGraph() {
        System.out.println("请输入顶点数和边数");
        Scanner scanner = new Scanner(System.in);
        numVertexes = scanner.nextInt();
        numEdges = scanner.nextInt();

        // 初始化邻接表
        adgList = new VertexNode[numVertexes];
        visited = new boolean[numVertexes];

        System.out.println("请输入顶点信息");
        for (int i = 0; i < numVertexes; i++) {
            VertexNode vertexNode = new VertexNode();
            vertexNode.setData(scanner.next());
            vertexNode.setFirstEdge(null);
            adgList[i] = vertexNode;
        }

        // 创建边信息
        for (int i = 0; i < numEdges; i++) {
            System.out.println("请输入边("+ adgList[i].getData() +",vj)中的(j)逗号分割开");
            String scannerStr = scanner.next();
            if ("no".equals(scannerStr)) {
                break;
            }
            String[] split = scannerStr.split(",");

            EdgeNode edgeNode = new EdgeNode();
            for (int j = 1; j < split.length; j++) {
                createEdgeNode(edgeNode, Integer.parseInt(split[j]), 99);
            }
            edgeNode.setAdjvex(Integer.parseInt(split[0]));
            edgeNode.setWeight(99);
            adgList[i].setFirstEdge(edgeNode);
        }
    }

    private void createEdgeNode(EdgeNode edgeNode, int adjvex, int weight) {
        EdgeNode newEdgeNode = new EdgeNode();
        newEdgeNode.setWeight(weight);
        newEdgeNode.setAdjvex(adjvex);
        edgeNode.setNext(newEdgeNode);
    }

    /**
     * 邻接表的深度优先遍历
     */
    public void nodeDFSTraverse() {
        Arrays.fill(visited, false);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                // 没有访问
                nodeDfs(i);
            }
        }
    }

    private void nodeDfs(int num) {
        visited[num] = true;

        // 打印顶点信息
        System.out.println(adgList[num].getData());

        EdgeNode edgeNode = adgList[num].getFirstEdge();
        while (edgeNode != null) {
            if (!visited[edgeNode.getAdjvex()]) {
                // 没有被访问
                nodeDfs(edgeNode.getAdjvex());
            }
            edgeNode = edgeNode.getNext();
        }
    }

    /**
     * 邻接表的广度优先遍历
     */
    public void nodeBFS() {
        Arrays.fill(visited, false);
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numVertexes; i++) {
            if (!visited[i]) {
                // 入队列
                visited[i] = true;
                System.out.println(adgList[i].getData());
                queue.add(i);

                while (!queue.isEmpty()) {
                    // 出队列
                    Integer first = queue.removeFirst();

                    EdgeNode edgeNode = adgList[first].getFirstEdge();
                    while (edgeNode != null) {
                        if (!visited[edgeNode.getAdjvex()]) {
                            queue.add(edgeNode.getAdjvex());
                            visited[edgeNode.getAdjvex()] = true;
                            System.out.println(adgList[edgeNode.getAdjvex()].getData());
                        }
                        edgeNode = edgeNode.getNext();
                    }
                }
            }
        }
    }

}

/**
 * 边表节点
 */
@Data
class EdgeNode {
    /** 邻接点域，存储该顶点对应的下标 **/
    private int adjvex;

    /** 用于存储权值，对于非网图可以不需要 **/
    private int weight;

    /** 链域，指向下一个邻接点 **/
    private EdgeNode next;
}

/**
 * 顶点表结构
 */
@Data
class VertexNode {
    /** 存储顶点信息 **/
    private String data;

    /** 存储边表信息 **/
    private EdgeNode firstEdge;
}