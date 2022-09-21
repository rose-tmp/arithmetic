import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/3-19:46 - 周日
 * @Description:
 */
public class Main2 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        dfs(graph,0,new ArrayList<>());
        return res;
    }

    public void dfs(int[][] graph, int index, List<Integer> list) {
        if (index == graph.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < graph[index].length; i++) {
            list.add(graph[index][i]);
            dfs(graph, i, list);
            list.remove(list.size() - 1);
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //用来记录有向图 key:课程编号 val:在有向图中该课程指向的课程，即这个课程是哪些课程的先修课程
        Map<Integer, List<Integer>> map = new HashMap<>();
        //in[i]记录了第i门课在图中的入度
        int[] in = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            List<Integer> list = map.getOrDefault(pre, new ArrayList<>());
            list.add(cur);
            map.put(pre, list);
            in[cur] = in[cur] + 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        //将入度为0的课程编号加入队列中
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[index++] = cur;
            if (!map.containsKey(cur)) {
                continue;
            }
            List<Integer> list = map.get(cur);
            //将该节点指向的其他所有节点的入度-1
            for (int i = 0; i < list.size(); i++) {
                int temp = list.get(i);
                in[temp] = in[temp] - 1;
                if (in[temp] == 0) {
                    queue.offer(temp);
                }
            }
        }
        //没有把全部的节点都放入res中，说明存在环，存在环就一定不可能有拓扑序
        if (index != numCourses) {
            return new int[0];
        }
        return res;
    }
}
