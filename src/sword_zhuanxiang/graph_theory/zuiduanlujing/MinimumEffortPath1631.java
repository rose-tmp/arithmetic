package sword_zhuanxiang.graph_theory.zuiduanlujing;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author: ZwZ
 * @date: 2022-09-26 16:49
 * @desc:
 */
public class MinimumEffortPath1631 {

    class Edge {
        int row;//横坐标
        int col;
        int cost;

        public Edge(int row, int col, int cost) {
            this.row = row;
            this.col = col;
            this.cost = cost;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        Queue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.cost - o2.cost;
            }
        });
        int[][] dirt = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] flag = new boolean[heights.length][heights[0].length];
        queue.add(new Edge(0, 0, 0));
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.row == heights.length - 1 && cur.col == heights[0].length - 1) {
                return cur.cost;
            }
            flag[cur.row][cur.col] = true;
            for (int i = 0; i < dirt.length; i++) {
                int nRow = cur.row + dirt[i][0];
                int nCol = cur.col + dirt[i][1];
                if (nRow < 0 || nCol < 0 || nRow >= heights.length || nCol >= heights[0].length || flag[nRow][nCol]) {
                    continue;
                }
                queue.offer(new Edge(nRow, nCol, Math.max(cur.cost, Math.abs(heights[nRow][nCol] - heights[cur.row][cur.col]))));
            }
        }
        return 0;
    }
}
