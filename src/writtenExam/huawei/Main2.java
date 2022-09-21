package writtenExam.huawei;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-07 21:20
 * @desc:
 */

public class Main2 {
    static int X1[] = {-1, 0, 1, 0};
    static int Y1[] = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        char map[][] = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = sc.next().toCharArray();
        }
        int startx = 0;
        int starty = 0;
        int endx = 0;
        int endy = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'S') {
                    startx = i;
                    starty = j;
                }
                if (map[i][j] == 'E') {
                    endx = i;
                    endy = j;
                }
            }
        }
        //[x,y,d,time]
        boolean jud[][] = new boolean[m][n];

        Queue<int[]> queue = new PriorityQueue<>(((o1, o2) -> o1[3] - o2[3]));
        queue.add(new int[]{startx, starty, -1, -1});
        while (!queue.isEmpty()) {
            int arr[] = queue.poll();
            int x1 = arr[0];
            int y1 = arr[1];
            //已经走过
            if (jud[x1][y1])
                continue;
            jud[x1][y1] = true;
            int direc = arr[2];
            //从start位置到达该位置时间花费
            int times = arr[3];
            if (x1 == endx && y1 == endy) {
                System.out.println(times);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int xnew = x1 + X1[i];
                int ynew = y1 + Y1[i];
                if (xnew >= 0 && xnew < m && ynew >= 0 && ynew < n) {
                    if (!jud[xnew][ynew] && map[xnew][ynew] != 'X') {
                        int timenew = times + 1;
                        //说明改变了方向
                        if (i != direc)
                            timenew++;
                        queue.add(new int[]{xnew, ynew, i, timenew});
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
