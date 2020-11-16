package leetcode.greedy;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/16 - 14:53
 * @Description:406. 根据身高重建队列
 * <p>
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * <p>
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 */
public class ReconstructQueue {
    /**
     * 每次确定一个当前数组中未确定位置的人中最小身高的位置
     *
     * 安排位置的原则是：先安排身高较小的人的位置
     * 假设此时身高最小的人为[h,k]那么它就应该被安排在数组中当前状态下
     * 从左向右数第k + 1个空位置上
     */
    public int[][] reconstructQueue(int[][] people) {
        /*对people进行排序
        当h1 < h2 时，按照h的大小升序排列
        当h1 == h2时，按照k降序排列 因为要使得最终排在后面的那一对先安排位置
        而我们安排位置是按照前边的空位置安排的，所以只有这样才能完全安排上位置
        例如：[7,0]和[7,1]应该先安排[7,1]的位置
        如果先安排[7,0]的位置  那么[7,1]的位置就安排不上了  (可以带入程序试一下)
        */
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o2[1] - o1[1];
                }
            }
        });
        int length = people.length;
        int[][] ans = new int[length][];
        for (int[] person : people) {
            int spaces = person[1] + 1;
            //为该元素在ans中寻找位置
            for (int i = 0; i < length; ++i) {
                if (ans[i] == null) {
                    --spaces;
                    if (spaces == 0) {
                        ans[i] = person;
                        break;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ReconstructQueue queue = new ReconstructQueue();
        int[][] people = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        queue.reconstructQueue(people);
    }
}
