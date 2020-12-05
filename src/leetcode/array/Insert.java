package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/11/4 - 18:56
 * @Description:57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠
 * （如果有必要的话，可以合并区间）。
 * <p>
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * <p>
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insert-interval
 */
public class Insert {
    /*public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null || newInterval[1] <= newInterval[0]) {
            return intervals;
        }
        //寻找newInterval[0]落在intervals中得哪一个区间中
        int firstRange = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] <= newInterval[0] && newInterval[0] <= intervals[i][1]) {
                firstRange = i;
                break;
            }
        }
        //寻找newInterval[1]落在intervals中哪一个区间中
        int secondRange = -1;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][0] <= newInterval[1] && newInterval[1] <= intervals[i][1]) {
                secondRange = i;
                break;
            }
        }
        //newInterval[0]没有在任何一个区间内
        if (firstRange == -1) {
            int[][] res = new int[intervals.length + 1][2];
            //寻找newInterval区间应该插入得位置
            int index = -1;
            for (int i = 0; i < intervals.length - 1; i++) {
                if (newInterval[0] > intervals[i][1] && newInterval[1] < intervals[i + 1][0]) {
                    index = i + 1;
                }
            }
            //为res赋值
            for (int i = 0; i < res.length; i++) {
                if (i < index) {
                    res[i][0] = intervals[i][0];
                    res[i][1] = intervals[i][1];
                } else if (i == index) {
                    res[i][0] = newInterval[0];
                    res[i][1] = newInterval[1];
                } else {
                    res[i][0] = intervals[i - 1][0];
                    res[i][1] = intervals[i - 1][1];
                }
            }
            return res;
        }
        //newInterval区间被intervals中得某区间包含
        else if (firstRange == secondRange) {
            return intervals;
        }
        else if(secondRange == -1){

        }
        //newInterval跨了多个区间
        else {
            int[][] res = new int[firstRange + intervals.length - 1 - secondRange][2];
            for (int i = 0; i < res.length; i++) {
                if (i < firstRange) {
                    res[i][0] = intervals[i][0];
                    res[i][1] = intervals[i][1];
                } else if (i == firstRange) {
                    res[i][0] = intervals[i][0];
                    res[i][1] = intervals[secondRange][1];
                } else {
                    res[i][0] = intervals[i + secondRange - firstRange][0];//secondRange - firstRange为偏移量
                    res[i][1] = intervals[i + secondRange - firstRange][1];
                }
            }
            return res;
        }
    }*/
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || newInterval == null || newInterval[1] < newInterval[0]) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        int left = newInterval[0];
        int right = newInterval[1];
        int index = 0;
        //此时遍历到得intervals[index]和newInterval没有交集 且在其左侧
        while (index < intervals.length && intervals[index][1] < left) {
            list.add(new int[]{intervals[index][0], intervals[index][1]});
            index++;
        }
        //有交集的时候要找到交集的左右边界
        while (index < intervals.length && intervals[index][0] <= right) {
            left = Math.min(intervals[index][0], left);
            right = Math.max(intervals[index][1], right);
            index++;
        }
        list.add(new int[]{left, right});
        //将交集左边没有加入list的区间加入
        while(index < intervals.length){
            list.add(new int[]{intervals[index][0],intervals[index][1]});
            index++;
        }
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < res.length; i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}
