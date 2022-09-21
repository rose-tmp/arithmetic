package greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: ZwZ
 * @date: 2022-09-16 19:00
 * @desc:435. 无重叠区间
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 *
 * 提示:
 *
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 */
public class EraseOverlapIntervals435 {
    /**
     * 贪心算法
     * 可以把每个区间画成横线在纸上画一下，便于理解
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        //根据第一个位置上的元素从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 要删除的区间的数量
        int res = 0;
        //上一个选中的右边界
        int right = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            /*如果当前位置所表示的区间没有和上一个区间重叠
             *那么这个时候不需要删除任何一个区间，此时更新一下右边界
             * */
            if (intervals[i][0] >= right) {
                right = intervals[i][1];
            } else {
                /*
                 *如果当前位置所表示的区间和上一个重叠
                 * 那么这个时候就删除右边界较大的那个区间
                 * 因为这样可以使得后边待选的区间才可以尽可能的保留更多的区间来
                 * */
                res++;
                right = Math.min(right, intervals[i][1]);
            }
        }
        return res;
    }
}
