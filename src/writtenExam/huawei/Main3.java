package writtenExam.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-07 19:48
 * @desc:
 */
public class Main3 {
    public static void main(String[] args) {
        /*System.out.print(-1);
        System.out.print(" ");
        System.out.println(-1);*/
        Scanner sc = new Scanner(System.in);
        int numR = sc.nextInt();
        int numC = sc.nextInt();
        int startR = sc.nextInt();
        int startC = sc.nextInt();
        int[][] arr = new int[numR + 1][numC + 1];

        for (int i = 1; i <= numR; i++) {
            int[] temp = new int[numC + 1];
            for (int j = 1; j <= numC; j++) {
                temp[j] = sc.nextInt();
            }
            arr[i] = temp;
        }
        List<int[]> res = new ArrayList<>();
        if (arr[startR][startC] != 0) {
            System.out.print(-1 + " " + -1);
            return;
        } else {
            for (int i = startC; i <= numC; i++) {
                int r1 = startR - 1;
                int r2 = startR + 1;
                if (r1 >= 1 && arr[r1][i] != 1 && r2 <= numR && arr[r2][i] != 1)
                    res.add(new int[]{startR, i});
                if (r1 >= 1 && arr[r1][i] == 1) {
                    res.add(new int[]{r1, i});
                    break;
                }
            }
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i)[0]);
            System.out.print(" ");
            System.out.print(res.get(i)[1]);
        }
    }
}
