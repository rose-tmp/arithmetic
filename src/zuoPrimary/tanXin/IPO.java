package zuoPrimary.tanXin;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author - ZwZ
 * @date - 2020/3/27 - 21:42
 * @Description:有N个项目，每个项目对应一个花费，一个收益
 * 给定一个资金M,最多做项目的次数K,每次只能做一个项目，并且做完项目之后立即可以获得收益并且可以拿着收益和本金再去做其他项目
 * 求：最多获得的最大钱数
 * 思路：每次做自己目前的资金可以做的项目中最大收益的项目，直到做完K次或者目前的资金不足以做任何项目的时候停止
 * 即贪心算法，每一步都要求是最好的，而很多时候贪心算法都是使用堆这个数据结构去实现
 */
public class IPO {
    /** 
    * @Author: ZwZ
    * @Description:表示一个项目 
    * @Param:  
    * @return:  
    * @Date: 2020/3/27-22:01
    */
    private static class Node{
        public int cost;//花费
        public int income;//收益
    }
    /** 
    * @Author: ZwZ
    * @Description:costs:表示各个项目花费的数组
     * incomes:表示各个项目收益的数组 M:表示初始本金  K：最大做任务次数
     * 通过Node.cost创建小根堆，每次从小根堆中拿出当前本金可以做的项目 并且把这些项目存入以Node.income创建的大根堆
     * 每次从大根堆里拿出根节点，这个项目就是要做的项目 
    * @Param: [costs, incomes, M, K] 
    * @return: int 
    * @Date: 2020/3/27-22:04
    */
    public int getMaxIncome(int[] costs,int[] incomes,int M,int K){
        if(M <= 0 || K <= 0)
            return 0;
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;//创建小根堆 至于为什么 看PriorityQueue源代码offer()-->siftUp(int k, E x)--->siftUpUsingComparator(k, x)中调用了比较器的compare(Node o1, Node o2),在这个代码逻辑中就是堆的向上调整，如果o1.cost - o2.cost >= 0 直接跳出循环,否则向上调整为大根堆....具体看源代码
            }
        });//以cost为指标建立的小根堆
        PriorityQueue<Node> maxHeap = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.income - o1.income;//创建大根堆
            }
        });//以income为指标建立的大根堆
        Node[] nodes = new Node[costs.length];//节点数组
        //为节点数组中的节点赋初值
        for (int i = 0; i < costs.length; i++) {
            nodes[i].cost = costs[i];
            nodes[i].income = incomes[i];
        }
        //小根堆初始化
        for (int i = 0; i < nodes.length; i++) {
            minHeap.offer(nodes[i]);
        }
        //控制做项目的次数不超过K
        for (int i = 0; i < K; i++) {
            //满足要求的节点存入大根堆
            while(!minHeap.isEmpty() && minHeap.peek().cost < M){
                maxHeap.offer(minHeap.poll());//出小根堆并且加入大根堆
            }
            if(maxHeap.isEmpty())
                return M;
            M += maxHeap.poll().income;//做项目并取得收益
        }
        return M;
    }
}
