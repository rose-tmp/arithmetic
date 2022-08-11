package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/3/28 - 15:02
 * @Description:56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * <p>
 * 示例2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 */
public class Merge {
    /**
     * @date 20220723
     */
    public int[][] merge2(int[][] intervals) {
        List<int[]> list = new ArrayList<>();
        int index = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        /**
         * 如果出现了当前右边界大于下一个的左边界的情况就一直while下去找到最大的有边界
         * */
        while (index < intervals.length) {
            int left = intervals[index][0];
            int right = intervals[index][1];
            while (index + 1 < intervals.length && right >= intervals[index + 1][0]) {
                right = Math.max(right, intervals[index + 1][1]);
                index++;
            }
            list.add(new int[]{left, right});
            index++;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }


    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][0];
        }
        if (intervals.length == 1) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) {
                    return o1[0] - o2[0];
                } else {
                    return o1[1] - o2[1];
                }
            }
        });
        //i是前一个指针  j是后一个指针
        for (int i = 0, j = 1; j < intervals.length; i++, j++) {
            if (intervals[i][1] >= intervals[j][0]) {
                intervals[j][0] = intervals[i][0];
                intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
            } else {
                list.add(intervals[i]);
            }
        }
        //for结束后，intervals中最后一处的一维数组是j指向的，并没有被加入到list中
        list.add(intervals[intervals.length - 1]);
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
