package sword_zhuanxiang.bingchaji;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author: ZwZ
 * @date: 2022-08-16 15:09
 * @desc:并查集模板 这个模板是比较”重量级“的,轻量级的可以看一下UnionFind2中的写法
 * <p>
 * 时间复杂度：
 *  union() findHead() isSameSet()的复杂度：
 *     首先在并查集的union()和isSameSet()中都调用了findHead() 然后由union()和isSameSet()的逻辑可知，他们的复杂度的就取决于findHead()
 *     的复杂度。findHead()的复杂度是多少，它俩的复杂度就是多少。
 *     1. 如果没有使用路径压缩进行优化的话，毫无疑问findHead()的平均时间复杂度就是o(logN),最坏的时间复杂度为o(N)
 *     2. 但是用了路径压缩之后，如何量化的评估findHead()时间复杂度这个问题左程云说整整证明了25年，这个数学问题过程很复杂，结论就是：
 *        当findHead()被调用的次数 > 样本总量N之后，它单次调用的平均时间复杂度为o(1),所以整体的时间复杂度为o(N)
 * 关于复杂度可以看一下(它总结了一个表格) https://leetcode.cn/problems/number-of-provinces/solution/jie-zhe-ge-wen-ti-ke-pu-yi-xia-bing-cha-0unne/
 */
public class UnionFind {
    static class Node {
        // 可以是任何类型 Object,String,Integer...
        // String str;
        // int val;
    }

    //node1:某节点 node2:此节点所在集合的最上方节点
    public HashMap<Node, Node> fatherMap;
    //Integer指当前节点所在的整个集合的大小
    public HashMap<Node, Integer> sizeMap;

    public UnionFind(List<Node> nodes) {
        fatherMap = new HashMap<Node, Node>();
        sizeMap = new HashMap<Node, Integer>();
        makeSets(nodes);
    }

    public void makeSets(List<Node> nodes) {
        fatherMap.clear();
        sizeMap.clear();
        for (Node node : nodes) {
            //一开始的时候每一个节点单独构成一个集合
            fatherMap.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    /**
     * 查找某个节点所在的集合的最上边的节点
     * <p>
     * 在查找过程中会将集合进行压缩，即查询时走过的所有节点都挂到根节点上去->这就是并查集中非常重要的优化过程，即路径压缩
     * 可以详见 https://www.bilibili.com/video/BV13g41157hK?p=13&vd_source=066f093757bbcc5407444cf0b0f5c8c0
     * <p>
     * 也可以不使用递归：使用一个栈或者其他数据结构存储每次查询向上跑的时候经过的节点
     * 当找到根节点的时候，就在数据结构中依次拿出来节点将它们的father改成根节点 ->详见findHead2
     */
    private Node findHead(Node node) {
        Node father = fatherMap.get(node);
        //如果它的上边的节点不是它自身就一直想上找，直到找到指向自身的那个节点 即根节点
        if (father != node)
            father = findHead(father);
        fatherMap.put(node, father);
        return father;
    }

    /**
     * findHead一样的逻辑
     * 只不过不是用的递归去完成的路径压缩 而是自行维护一个stack
     */
    private Node findHead2(Node node) {
        Stack<Node> path = new Stack<>();
        //记录沿途上的所有元素
        while (node != fatherMap.get(node)) {
            path.push(node);
            node = fatherMap.get(node);
        }
        /*
        * 第一个while执行完毕后，node指向father
        * 然后将沿途节点的father都设置成node
        * */
        while (!path.isEmpty()) {
            fatherMap.put(path.pop(),node);
        }
        return node;
    }

    /**
     * 合并两个节点所在的集合
     *
     * union()的过程将小的集合挂在大的集合上这个操作也和findHead()方法中的路径压缩一样属于并查集的优化方式
     * 名字叫做：按秩合并 即在并查集中合并两个连通分量时，将「秩」小的连通分量合并到「秩」大的连通分量上面
     */
    public void union(Node a, Node b) {
        if (a == null || b == null) {
            return;
        }
        //找到各自所在集合的头节点
        Node aHead = findHead(a);
        Node bHead = findHead(b);
        if (aHead != bHead) {
            int aSetSize = sizeMap.get(aHead);
            int bSetSize = sizeMap.get(bHead);
            //小的集合挂在大集合上
            if (aSetSize <= bSetSize) {
                fatherMap.put(aHead, bHead);
                sizeMap.put(bHead, aSetSize + bSetSize);
            } else {
                fatherMap.put(bHead, aHead);
                sizeMap.put(aHead, aSetSize + bSetSize);
            }
        }
    }

    /**
     * 判断两个节点是否在相同的集合中
     */
    public boolean isSameSet(Node a, Node b) {
        return findHead(a) == findHead(b);
    }
}
