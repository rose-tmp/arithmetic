package sword_zhuanxiang.graph_theory.minimumSpanningTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-08-18 17:40
 * @desc: 题目描述
 * 如题，给出一个无向图，求出最小生成树，如果该图不连通，则输出orz.
 * 输入
 * 第一行包含两个整数N,M，表示该图共有N个结点和M条无向边。
 * 接下来M行每行包含三个整数xi，yi，zi，表示有一条长度为z的无向边连接结点x，y.
 * 输出
 * 如果该图连通，则输出一个整数表示最小生成树的各边的长度之和.如果该图不连通则输出orz
 * 样例输入
 * 4 5
 * 1 2 2
 * 1 3 2
 * 1 4 3
 * 2 3 4
 * 3 4 3
 * 样例输出
 * 7
 */
public class Kruskal {
    /**
     * 存储边信息的数据结构
     */
    static class Edge {
        /**
         * 对应每行的三个整数xi，yi，zi
         */
        int x;
        int y;
        int val;

        public Edge(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }
    /**
     * Kruskal算法的思路（贪心）：
     *  1. 将每一条边加入到一个集合中然后根据权值从小到大排序；->在该代码中利用Edge类保存每条边的信息+然后List保存每一个Edge去实现
     *  2. 从集合中按从小到大的顺序去拿边，如果拿到的这条边选中之后不会构成环,那么这条边就选中;如果构成了,那么就不选,继续向下寻找其他边
     *     -> 如何判断选中之后是否构成一个环？采用实现一个并查集的数据结构去解决
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();//节点个数
        int M = sc.nextInt();//边的条数

        List<Edge> list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            list.add(new Edge(x, y, z));
        }

        //对于list中存储的数据根据每条边的权值进行排序
        list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.val - o2.val;
            }
        });

        //并查集 parents[i]:i号节点所属集合中的”老大“
        int[] parents = new int[N + 1];
        //并查集初始化
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        int sum = 0;//最终的权值和
        int count = 0;//选中的边的数量

        //开始从list中选择边
        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);
            /*说明这两个点在同一个集合中,
             * 意味着一定形成了环,所以这条边不能选
             * */
            if (find(parents, edge.x) == find(parents, edge.y)) {
                continue;
            }
            sum += edge.val;//因为这条边需要选中,所以要将权值添加到总和上
            count++;//选中的边的数量最终如果等于N-1则说明已经构成了一颗生成树,以此作为返回时机的判断条件
            //将选中的边两端的节点加入到同一个集合中
            union(parents, edge.x, edge.y);

            //已经构成了一颗最小生成树
            if (count == N - 1) {
                System.out.println(sum);
                break;
            }
        }
        if (count != N - 1) {
            System.out.println("orz");
        }
    }

    /**
     * 并查集中的find方法
     */
    private static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        int f = find(parent, parent[i]);
        parent[i] = f;
        return f;
    }

    /**
     * 并查集中的union方法
     */
    private static void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }
}
