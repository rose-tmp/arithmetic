package zuoPrimary.tanXin;

import java.util.PriorityQueue;

/**
 * @author - ZwZ
 * @date - 2020/3/27 - 20:44
 * @Description: 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。一群人想整分整块金条，怎么分最省铜板?
 * 例如，给定数组{10, 20, 30]，代表-共三个人， 整块金条长度为10+20+30=60.金条要分成10, 20, 30三个部分。如果， 先把长度60的金条分成10和50，花费60再把长度50的金条分成20和30,花费50 -共花费110铜板。
 * 但是如果，先把长度60的金 条分成30和30，花费60再把长度30金条分成10和20，花费30 -共花费90铜板。输入-一个数组，返回分割的最小代价。
 * 思路：
 * 每次向下切分，可以看成一个二叉树，而这个二叉树的叶子节点就是数组中给定的所有数值
 * 那么这道题就可以转化为：怎么做可以使得二叉树的非叶子节点之和最小
 * 而恰恰哈夫曼树可以做到这一点，所以使用哈夫曼树数据结构，而哈夫曼树的构建是从叶节点开始的，我们如何找到数组中最小的两个值？
 * 答案：使用最小堆(堆结构经常使用在贪心算法中) Java中提供了最小堆：PriorityQueue
 */
public class Less_Money {
    public int getLessMoney(int[] arr) {
        if (arr == null)
            return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();//最小堆
        int sum = 0;
        int temp;
        for (int i = 0; i < arr.length; i++) {
            minHeap.offer(arr[i]);
        }
        while (minHeap.size() > 1) {
            temp = minHeap.poll() + minHeap.poll();//取两个最小值
            sum += temp;
            minHeap.offer(temp);
        }
        return sum;
    }

    public static void main(String[] args) {
        Less_Money money = new Less_Money();
        int[] arr = {10,20,30};
        System.out.println(money.getLessMoney(arr));
    }
}
