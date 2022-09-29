package interview.huawei;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author: ZwZ
 * @date: 2022-09-27 15:53
 * @desc:你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；
 * 并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * 提示：
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class Demo1 {
    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        System.out.println(demo1.isFinished(new int[][]{{1, 0}}, 2));
        System.out.println(demo1.isFinished(new int[][]{{1, 0}, {0, 1}}, 2));
        System.out.println(demo1.isFinished(new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}}, 5));
        System.out.println(demo1.isFinished(new int[][]{{1, 0}, {2, 1}, {3, 2}, {4, 3}, {0, 4}}, 5));
    }

    public boolean isFinished(int[][] prerequisites, int n) {
        List<Integer>[] list = new ArrayList[n];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        int[] inSums = new int[n];
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int next = prerequisites[i][0];

            List<Integer> temp = list[pre];
            temp.add(next);

            inSums[next]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inSums.length; i++) {
            if (inSums[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = 0; i < list[cur].size(); i++) {
                inSums[list[cur].get(i)]--;
                if (inSums[list[cur].get(i)] == 0) {
                    queue.offer(list[cur].get(i));
                }
            }
            count++;
        }
        return count == n;
    }
}
