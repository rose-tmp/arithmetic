import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-06 23:18
 * @desc:
 */
public class Main3 {
    static class Node {
        int val;
        Node[] childs;

        public Node(int val) {
            this.val = val;
            this.childs = new Node[3];
        }
    }

    List<Node> res = new ArrayList<>();

    public List<Node> getList(Node root) {
        dfs(root, new ArrayList<>(), 7);
        return res;
    }

    /**
     * dfs函数完成深搜的过程
     *
     * @param target 需要寻找的节点的值
     * @param list   深搜的过程中走过的节点集合
     */
    public void dfs(Node root, List<Node> list, int target) {
        if (root.val == target) {
            res = new ArrayList<>(list);
            return;
        }
        for (int i = 0; i < root.childs.length; i++) {
            list.add(root);
            dfs(root.childs[i], list, target);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Main3 main3 = new Main3();
        Node root = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.childs[0] = node1;
        root.childs[1] = node2;
        root.childs[2] = node3;
        node1.childs[0] = node4;
        node1.childs[1] = node5;
        node2.childs[0] = node6;
        node6.childs[0] = node7;

        List<Node> list = main3.getList(root);
        for (Node n : list) {
            System.out.println(n.val);
        }
    }
}
