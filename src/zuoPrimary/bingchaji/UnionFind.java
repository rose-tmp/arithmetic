package zuoPrimary.bingchaji;

import java.util.HashMap;
import java.util.List;

/**
 *  
 *
 * @Author: ZwZ
 * @Description:并查集(Disjoint-Set)是一种可以动态维护若干个不重叠的集合， 并支持合并与查询两种操作的一种数据结构
 * 基本操作:
 * 1. 合并(Union/Merge)[1]：合并两个集合。
 * 2. 查询(Find/Get)：查询元素所属集合。
 * 实际操作时，我们会使用一个点来代表整个集合，即一个元素的根结点(可以理解为父亲，这个节点的父亲是他自己)。 
 * @Param:  
 * @return:  
 * @Date: 2020/3/27-12:12
 */
public class UnionFind {

    public static class Node {
        // whatever you like 可以是任何类型 Object,String,Integer...
    }

    public static class UnionFindSet {
        //node1:某节点 node2:此节点所在集合的最上方节点
        public HashMap<Node, Node> fatherMap;
        //Integer指当前节点所在的整个集合的大小
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet(List<Node> nodes) {
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
         *
         * 在查找过程中会将集合进行压缩，即查询时走过的所有节点都挂到根节点上去
         * 也可以不使用递归：使用一个栈或者其他数据结构存储每次查询向上跑的时候经过的节点
         * 当找到根节点的时候，就在数据结构中依次拿出来节点将它们的father改成根节点
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
         * 判断两个节点是否在相同的集合中
         */
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        /**
         * 合并两个节点所在的集合
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
    }
}
