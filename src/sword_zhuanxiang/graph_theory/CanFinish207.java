package sword_zhuanxiang.graph_theory;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-18 14:41
 * @desc:207. 课程表
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，
 * 表示如果要学习课程 ai 则 必须 先学习课程  bi 。
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 * 示例 2：
 * <p>
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= numCourses <= 105
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 */
public class CanFinish207 {
    /**
     * 根据课程与课程之间的关系构造一个有向图，然后将该问题转化为:
     * 检测有向图中是否包含环的问题 ——>而这个问题的求解完全可以用FindOrder113的代码和思路
     * 只不过把返回值改一下就ok
     * 思路：使用广度优先遍历完成拓扑排序的过程，遍历完之后，如果遍历过的节点的总数等于节点总数，则说明全部遍历完了
     * 这就说明不存在环；如果不等于则说明存在环，因为只有存在环的时候才会导致求拓扑序的过程中没能遍历完所有的节点
     * bfs
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        //已经访问过的节点的总数
        int visitedSum = 0;
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0的节点存入queue
        for (int i = 0; i < numCourses; i++) {
            if (inSum[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            visitedSum++;
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

        //说明没有把所有的节点的访问到，而造成这个的唯一原因就是存在环
        if (visitedSum != numCourses) {
            return false;
        }
        return true;
    }
}
