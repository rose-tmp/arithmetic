package zhanlang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-05 16:21
 * @desc:
 */
public class HJ43 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rowNum = sc.nextInt();
        int colNum = sc.nextInt();
        int[][] arr = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            int[] cur = new int[colNum];
            for (int j = 0; j < colNum; j++) {
                cur[j] = sc.nextInt();
            }
            arr[i] = cur;
        }
        dfs(new ArrayList<>(), 0, 0, arr);
        for (int i = 0; i < res.size(); i++) {
            System.out.println("(" + res.get(i)[0] + "," + res.get(i)[1] + ")");
        }
    }

    static List<int[]> res = new ArrayList<>();

    public static void dfs(List<int[]> list, int i, int j, int[][] arr) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length) {
            return;
        }
        if (i == arr.length - 1 && j == arr[0].length - 1) {
            list.add(new int[]{i, j});
            if (list.size() < res.size() || res.size() == 0) {
                res = new ArrayList<>(list);
            }
            return;
        }
        if (arr[i][j] == 1) {
            return;
        } else {
            arr[i][j] = 1;//标记走过的点
            list.add(new int[]{i, j});
            dfs(list, i - 1, j, arr);
            dfs(list, i + 1, j, arr);
            dfs(list, i, j - 1, arr);
            dfs(list, i, j + 1, arr);
            arr[i][j] = 0;
            list.remove(list.size() - 1);
        }
    }
}
