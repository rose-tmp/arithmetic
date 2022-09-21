package interview.honor;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-09 10:27
 * @desc:荣耀一面
 * 乘公交：输入所有公交站点信息、需要乘车的起点站、终点站，输出最少的乘坐站点数，
 * 即：int[][] lines, int source, int target，例如：
 * lineList =
 * 1,3,5,7
 * 2,4,6,8
 * 5,7,9,11
 * source = 3,target = 8；
 * 输出：0
 *
 * lineList =
 * 1,3,5,7
 * 1,4,6,8
 * 3,8,9,11
 * source = 1,target = 8；
 * 输出：2，因为乘坐了2号线，做了4,6,8三个站点；坐1号线到3站点转3号线到8站点
 */
public class Main1 {
    /***
     * 跟华为前天笔试的第二题有点像
     * 我这里写的代码在res和sum的处理上其实是有bug的
     * 但是整体思路是没毛病的，面试官也说没啥问题
     */
    public int getRes(int[][] lines, int source, int target) {
        //key:站点编号 val：
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length; j++) {
                List<Integer> list = map.getOrDefault(lines[i][j], new ArrayList<>());
                if (j + 1 < lines[0].length) {
                    list.add(lines[i][j + 1]);
                }
                map.put(lines[i][j], list);
            }
        }
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(source);

        int res = Integer.MAX_VALUE;
        int sum = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            if (cur == target) {
                res = Math.min(res, sum);
                break;
            }
            List<Integer> list = map.get(cur);
            for (int i = 0; i < list.size(); i++) {
                sum++;
                queue.add(list.get(i));
            }
        }
        return res;
    }
}
