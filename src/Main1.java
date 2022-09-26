import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-03 20:33
 * @desc:
 */
public class Main1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] inSum = new int[numCourses];//inSum[i]:i节点的入度
        //构造图
        for (int[] p : prerequisites) {
            List<Integer> list = map.getOrDefault(p[1], new ArrayList<>());
            list.add(p[0]);
            map.put(p[1], list);
            inSum[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inSum[i] == 0) {
                queue.offer(i);
            }
        }
        int[] ans = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            ans[index] = cur;
            index++;

            List<Integer> temp = map.get(cur);
            if (temp == null) {
                continue;
            }
            for (Integer i : temp) {
                --inSum[i];
                if (inSum[i] == 0) {
                    queue.add(i);
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }
        return ans;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    public int[][] dirt = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length || grid[i][j] == 0) {
            return 0;
        }
        int ans = 1;
        grid[i][j] = 0;
        for (int k = 0; k < dirt.length; k++) {
            int newX = i + dirt[k][0];
            int newY = j + dirt[k][1];
            ans += dfs(grid, newX, newY);
        }
        return ans;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int[] fa = new int[edges.length];
        for (int i = 0; i < fa.length; i++) {
            fa[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (find(fa, edges[i][0]) == find(fa, edges[i][1])) {
                return edges[i];
            }
            union(fa, edges[i][0], edges[i][1]);
        }
        return new int[0];
    }

    public void union(int[] fa, int i, int j) {
        fa[find(fa, i)] = find(fa, j);
    }

    public int find(int[] fa, int i) {
        if (fa[i] == i) {
            return i;
        }
        int f = find(fa, fa[i]);
        fa[i] = f;
        return f;
    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        if (nums.length == 0) {
            return 0;
        }
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    /*static class ShowMeBug {
        public final int[][] arr;

        public ShowMeBug(int[][] arr) {
            this.arr = arr;
        }

        public static void main(String[] args) {
            int[][] arr = new int[][]{
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            };

            final ShowMeBug showMeBug = new ShowMeBug(arr);

            showMeBug.updateNextState(arr);
        }

        *//**
     * 更新arr下个tick的生存状态
     *
     * @param arr 当前状态下的数组
     *//*
        public void updateNextState(int[][] arr) {
            int M = arr.length;
            int N = arr[0].length;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = getNextVal(arr, i, j);
                }
            }
        }

        *//**
     * 计算某细胞下个tick的状态
     *
     * @param arr 当前状态下的数组
     * @param i   行索引
     * @param j   列索引
     * @return 生存返回1，否则返回0
     *//*
        private int getNextVal(int[][] arr, int i, int j) {
            int nextVal = 0;
            //TODO:请完善该函数，要求：需要调用countLivingNeighbors
            int count = countLivingNeighbors(arr, i, j);
            if (arr[i][j] == 0) {
                if (count >= 3) {
                    nextVal = 1;
                }
            } else {
                if (count < 2) {
                    nextVal = 0;
                } else if (count == 2 || count == 3) {
                    nextVal = 1;
                } else {
                    nextVal = 0;
                }
            }
            return nextVal;
        }

        */

    /**
     * 计算某细胞的存活邻居数量
     *
     * @param arr 当前状态下的数组
     * @param i   行索引
     * @param j   列索引
     * @return 该元素存活的邻居数量
     *//*
        private int countLivingNeighbors(int[][] arr, int i, int j) {
            int count = 0;
            //TODO:请完善该函数
            int[][] dict = new int[][]{
                    {0, -1}, {0, 1},//同一行
                    {1, -1}, {1, 0}, {1, 1},//下一行
                    {-1, -1}, {-1, 0}, {-1, 1}//上一行
            };
            for (int m = 0; m < dict.length; m++) {
                int row = dict[m][0] + i;
                int col = dict[m][1] + j;
                if (row >= 0 && row < arr.length &&
                        col >= 0 && col < arr[0].length) {
                    if (arr[row][col] == 1) {
                        count++;
                    }
                }
            }

            return count;
        }

        private boolean[][] initFlag(int[][] arr) {
            boolean[][] flag = new boolean[arr.length][arr[0].length];
            int[][] dict = new int[][]{
                    {0, -1}, {0, 1},//同一行
                    {1, -1}, {1, 0}, {1, 1},//下一行
                    {-1, -1}, {-1, 0}, {-1, 1}//上一行
            };

            for (int k = 0; k < arr.length; k++) {
                for (int l = 0; l < arr[0].length; l++) {
                    int temp = 0;
                    for (int m = 0; m < dict.length; m++) {
                        int row = dict[m][0] + k;
                        int col = dict[m][1] + l;
                        if (row >= 0 && row < arr.length &&
                                col >= 0 && col < arr[0].length) {
                            if (arr[row][col] == 1) {
                                temp++;
                            }
                        }
                    }
                    //当前细胞为死亡状态
                    if (arr[k][l] == 0) {
                        //该细胞是活细胞
                        if (temp >= 3) {
                            flag[k][l] = true;
                        }
                    } else {
                        if (temp < 2) {
                            flag[k][l] = false;
                        } else if (temp == 2 || temp == 3) {
                            flag[k][l] = true;
                        } else {
                            flag[k][l] = false;
                        }
                    }
                }
            }
            return flag;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{};
        GameOfLife gol = new GameOfLife(arr);
        Thread1 t1 = new Thread1(gol);
        t1.start();
    }

    public static class Thread1 extends Thread {
        GameOfLife gol;

        public Thread1(GameOfLife gol) {
            this.gol = gol;
        }

        @Override
        public void run() {
            TimerTask timerTask = new TimerTask() {

                @Override
                public void run() {
                    gol.updateNextState(arr);
                }
            };
        }
    }*/
}
