package sword_zhuanxiang.graph_theory;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-18 15:45
 * @desc:剑指 Offer II 113. 课程顺序
 * 现在总共有 numCourses 门课需要选，记为 0 到 numCourses-1。
 * 给定一个数组 prerequisites ，它的每一个元素 prerequisites[i] 表示两门课程之间的先修顺序。
 * 例如 prerequisites[i] = [ai, bi] 表示想要学习课程 ai ，需要先完成课程 bi 。
 * 请根据给出的总课程数  numCourses 和表示先修顺序的 prerequisites 得出一个可行的修课序列。
 * 可能会有多个正确的顺序，只要任意返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * <p>
 * 示例 1:
 * <p>
 * 输入: numCourses = 2, prerequisites = [[1,0]]
 * 输出: [0,1]
 * 解释: 总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例 2:
 * <p>
 * 输入: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释: 总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。
 * 示例 3:
 * <p>
 * 输入: numCourses = 1, prerequisites = []
 * 输出: [0]
 * 解释: 总共 1 门课，直接修第一门课就可。
 * <p>
 * 提示:
 * <p>
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * prerequisites 中不存在重复元素
 * <p>
 * 注意：本题与主站 210 题相同：https://leetcode-cn.com/problems/course-schedule-ii/
 */
public class FindOrder113 {
    /**
     * 该问题是《数据结构》中讲拓扑排序时最经典的例子,关于拓扑排序可以随便找个B站视频大致过一下
     * 首先要知道如果图中有环则一定不会存在拓扑排序
     * <p>
     * 思路：将所有课程构成一个图，寻找该图中的其中一个拓扑序
     * 使用bfs求拓扑序的过程:
     * 将所有入度为0的点存入到queue中，poll出一个元素后，将该元素的指向的所有节点的入度-1,如果-1后他们的入度为0
     * 则将其加入queue，如此反复。什么时候停止：当queue为空时停止
     * <p>
     * bfs
     *
     * @return 所有课程所构成的图的一个拓扑排序
     */
    public int[] findOrder1(int numCourses, int[][] prerequisites) {
        // key:课程编号 val:只有学完了编号为key的课程才能学的课程的集合
        Map<Integer, List<Integer>> map = new HashMap<>();
        //inSum[i]:节点i的入度
        int[] inSum = new int[numCourses];
        //构造有向图并存储每个节点的入度
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];

            List<Integer> list = map.getOrDefault(pre, new ArrayList<>());
            list.add(cur);
            map.put(pre, list);
            inSum[cur] += 1;//更新入度
        }

        int[] ans = new int[numCourses];
        int index = 0;
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0的节点存入queue
        for (int i = 0; i < numCourses; i++) {
            if (inSum[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[index++] = cur;
            if (map.get(cur) == null) {
                continue;
            }
            //将节点cur指向的节点的入度-1
            for (Integer i : map.get(cur)) {
                inSum[i] -= 1;
                if (inSum[i] == 0) {
                    queue.offer(i);
                }
            }
        }

        //没有把所有的节点都加入到ans中，则说明存在环
        if (index != numCourses) {
            return new int[0];
        }
        return ans;
    }

    /**
     * dfs
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        return null;
    }
}
