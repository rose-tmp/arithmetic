package sword_zhuanxiang.bingchaji;

/**
 * @author: ZwZ
 * @date: 2022-08-18 22:53
 * @desc:并查集相较于UnionFind1中代码,“瘦身”之后的模板
 */
public class UnionFind2 {
    /**
     * 需要使用到并查集的方法
     */
    public void main1() {
        //在该方法中定义一个parents数组
        int[] parents = new int[10];

        //对并查集中的parents数组进行初始化
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        /*
         * ......
         * 逻辑代码
         * ......
         * */
    }

    /**
     * @param parent parent[i]存储了i节点的从属的集合中的”老大“是哪个节点
     * @return 返回i的最上层的节点 这个代码的逻辑和UnionFind.java中的findHead一模一样
     * 即在查找过程中会将集合进行压缩，即查询时走过的所有节点都挂到根节点上去
     */
    private int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        //找它的爹
        int f = find(parent, parent[i]);
        //更新它的爹
        parent[i] = f;
        return f;
    }

    /**
     * 将i和j所在的两个集合合并
     *
     * @param parent parent[i]=j表示i节点的从属的集合中的”老大“是节点j
     */
    private void union(int[] parent, int i, int j) {
        //这里是随机挂的，并没有和UnionFind中一样遵循"小挂大"的原则
        parent[find(parent, i)] = find(parent, j);
    }
}
