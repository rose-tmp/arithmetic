package writtenExam.alibaba;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-07 20:14
 * @desc:
 */
public class Main4 {
    static final int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        char[][] arr = new char[m][n];
        for (int i = 0; i < m; i++) {
            String s = scanner.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        int path = 0, f = -1, sum = 0;
        boolean[][] flags = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 'S') {
                    dfs(arr, 0, 0, path, f, sum, flags);
                }
            }
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    public static void dfs(char[][] matrix, int x, int y, int path, int f, int sum, boolean[][] flags) {
        if (matrix[x][y] == 'E') {
            res = Math.min(res, path + sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextx = x + dir[i][0];
            int nexty = y + dir[i][1];
            if (nextx < 0 || nextx >= matrix.length || nexty < 0 || nexty >= matrix[0].length) continue;
            if (matrix[nextx][nexty] == 'X' || flags[nextx][nexty]) continue;
            flags[nextx][nexty] = true;
            if (f == -1 || f == i) {
                dfs(matrix, nextx, nexty, path + 1, i, sum, flags);
            } else if (f != i) {
                dfs(matrix, nextx, nexty, path + 1, i, sum + 1, flags);
            }
            flags[nextx][nexty] = false;
        }
    }
}
