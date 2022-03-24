package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author - ZwZ
 * @date - 2020/11/23 - 11:08
 * @Description:452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标
 * 由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭
 * 若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足 xstart≤ x ≤ xend，则该气球会被引爆
 * 可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量
 * 给你一个数组 points ，其中 points [i] = [Xstart,Xend] ，返回引爆所有气球所必须射出的最小弓箭数
 * <p>
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * <p>
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * <p>
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * <p>
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * <p>
 * 提示：
 * 0 <= points.length <= 104
 * points[i].length == 2
 * -2^31 <= xstart <xend <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons
 */
public class FindMinArrowShots {
    /**
     * 每次都取当前气球最右边界的坐标，可以有更大可能射爆下一个气球
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        //根据Xend从小到大对points中的元素排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] point1, int[] point2) {
                /*没有用return point1[1] - point2[1]
                因为有一个测试用例是[[-2147483646,-2147483645],[2147483646,2147483647]]
                -2147483645 - 2147483647  补码 - 补码  会越界反而结果是一个正数*/
                return point1[1] < point2[1] ? -1 : 1;
            }
        });
        int total = 1;
        int pos = points[0][1];
        for (int[] point : points) {
            if (point[0] > pos) {
                pos = point[1];
                total++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        FindMinArrowShots arrowShots = new FindMinArrowShots();
        int[][] points = new int[][]{{10,16},{2,8},{1,6},{7,12}};
        arrowShots.findMinArrowShots(points);
    }
}
