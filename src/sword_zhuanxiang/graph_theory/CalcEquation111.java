package sword_zhuanxiang.graph_theory;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-17 15:58
 * @desc:剑指 Offer II 111. 计算除法
 * 给定一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 示例 1：
 * <p>
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * 示例 2：
 * <p>
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * 示例 3：
 * <p>
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * 提示：
 * <p>
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 * <p>
 * 注意：本题与主站 399 题相同： https://leetcode-cn.com/problems/evaluate-division/
 */
public class CalcEquation111 {
    /***
     * bfs
     */
    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*用Map表示一张有向加权图 key:节点1 value:List<Pair>
         * 而value中的List<Pair>就记录了节点1作为出发点,与它相连的所有节点以及它们之间的权值
         * 这种存储图的方式和和邻接矩阵有点像
         * */
        Map<String, List<Pair>> map = new HashMap<>();
        // 根据方法所给的信息，构造一个有向加权图
        for (int i = 0; i < equations.size(); i++) {
            String node1 = equations.get(i).get(0);
            String node2 = equations.get(i).get(1);

            List<Pair> list1 = map.getOrDefault(node1, new ArrayList<>());
            list1.add(new Pair(values[i], node2));
            map.put(node1, list1);

            List<Pair> list2 = map.getOrDefault(node2, new ArrayList<>());
            list2.add(new Pair(1 / values[i], node1));
            map.put(node2, list2);
        }

        List<Double> ans = new ArrayList<>();
        //开始遍历每个问题
        for (List<String> q : queries) {
            //被除数 -> 出发节点
            String from = q.get(0);
            //除数 -> 所要到达的节点
            String to = q.get(1);

            if (!map.containsKey(from) || !map.containsKey(to)) {
                ans.add(-1.0);
                continue;
            }
            if (from.equals(to)) {
                ans.add(1.0);
                continue;
            }

            //与出发节点相连的所有的点以及该边的权值
            List<Pair> pairList = map.get(from);

            /*记录每个节点与from的距离,因为是层次遍历，所以每个节点和from的距离都要做单独的存储
             *不像dfs中，可以直接在深搜的过程中去用一个变量记录
             * */
            Map<String, Double> distanceMap = new HashMap<>();
            distanceMap.put(from, 1.0);
            Queue<Pair> queue = new LinkedList<>();
            //将出发节点的所有可到达节点加入队列并且把距离也加入到distanceMap中
            for (Pair p : pairList) {
                queue.offer(p);
                distanceMap.put(p.node, p.val);
            }

            //作为是否找到的标记 如果找到了queue中剩下的元素也不在需要进while循环
            boolean isFind = false;
            //作为访问标记
            Set<String> visited = new HashSet<>();
            visited.add(from);
            //开始操作
            while (!queue.isEmpty() && !isFind) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    Pair pair = queue.poll();
                    if (visited.contains(pair.node)) {
                        continue;
                    }
                    //找到了
                    if (to.equals(pair.node)) {
                        ans.add(distanceMap.get(pair.node));
                        isFind = true;
                        break;
                    }
                    visited.add(pair.node);
                    //将pair.node指向的所有点加入到queue中
                    for (Pair p : map.get(pair.node)) {
                        //更新该节点到from节点的距离 它就等于上一级节点到from的距离*当前节点对应边的权值
                        distanceMap.put(p.node, distanceMap.get(pair.node) * p.val);
                        queue.offer(p);
                    }
                }
            }
            if (!isFind) {
                ans.add(-1.0);
            }
        }
        double[] resArr = new double[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            resArr[i] = ans.get(i);
        }
        return resArr;
    }

    /**
     * 这道题是一个有向加权图的问题，图中的每条边都带有权值 并且是有向图
     * 思路："a/b=2.0"可以用a->b并且这条有向边的权值是2.0来表示，进而b->a这条边的权值为0.5
     * 这样本地中除法问题就转换成了一个有向图中寻找符合条件的路径的问题
     * <p>
     * dfs
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        /*用Map表示一张有向加权图 key:节点1 value:List<Pair>
         * 而value中的List<Pair>就记录了节点1作为出发点,与它相连的所有节点以及它们之间的权值
         * */
        Map<String, List<Pair>> map = new HashMap<>();
        // 根据方法所给的信息，构造一个有向加权图
        for (int i = 0; i < equations.size(); i++) {
            String node1 = equations.get(i).get(0);
            String node2 = equations.get(i).get(1);

            List<Pair> list1 = map.getOrDefault(node1, new ArrayList<>());
            list1.add(new Pair(values[i], node2));
            map.put(node1, list1);

            List<Pair> list2 = map.getOrDefault(node2, new ArrayList<>());
            list2.add(new Pair(1 / values[i], node1));
            map.put(node2, list2);
        }

        for (int i = 0; i < queries.size(); i++) {
            String node1 = queries.get(i).get(0);
            String node2 = queries.get(i).get(1);

            //问题等式中给的字符没有出现过
            if (!map.containsKey(node1) || !map.containsKey(node2)) {
                ans.add(-1.0);
                continue;
            }
            isFind = false;//复原标记位
            Set<String> param = new HashSet<>();
            /*把起始位置的节点加到visited中 因为这里不加的话，起始位置的节点在下面就没机会加了
             * 会造成结果的错误 例如示例中的输出如果不加的话输出为:[6.00000,0.50000,0.50000,-1.00000,1.00000,-1.00000]
             * 造成这个的原因就是因为b没有加到visited中，造成dfs(c)找到了b 然后进行了二次执行dfs(b)
             * */
            param.add(node1);
            dfs(map, param, node2, node1, 1);
            if (!isFind) {
                ans.add(-1.0);
            }
        }

        double[] resArr = new double[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            resArr[i] = ans.get(i);
        }
        return resArr;
    }

    List<Double> ans = new ArrayList<>();
    /*
     * 定义这个变量的目的是标记是否找到了给定问题式子
     * 以用来在没有找到时作为ans.add(-1)的依据
     *
     * 这个变量是在提交第二次之后没有通过所有实例才添加的
     * */
    boolean isFind = false;

    /**
     * @param map     有向加权图
     * @param visited 记录已经访问过的节点
     *                加这个字段作为标记为了防止进入死循环 例如在示例中:
     *                dfs(a)找到了b,dfs(b)找到了c,dfs(c)又到了b
     * @param target  想要寻找的结束点 即queries算式中的除数
     * @param cur     从node1出发当前遍历的节点
     * @param sum     遍历到node2时走过的路径的权值和
     */
    private void dfs(Map<String, List<Pair>> map, Set<String> visited, String target, String cur, double sum) {
        if (isFind) {
            return;
        }
        if (target.equals(cur)) {
            ans.add(sum);
            //设置标记位
            isFind = true;
            return;
        }
        List<Pair> list = map.get(cur);
        //继续向下寻找
        for (Pair pair : list) {
            if (visited.contains(pair.node)) {
                continue;
            }
            visited.add(pair.node);
            dfs(map, visited, target, pair.node, sum * pair.val);
            visited.remove(pair.node);
        }
    }

    /**
     * 用来表示每个节点相连的节点和它们之间边的权值
     */
    private class Pair {
        //该边的权值
        double val;
        //该边所指的节点
        String node;

        public Pair(double val, String node) {
            this.val = val;
            this.node = node;
        }
    }
}
