package zhanlang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/6-19:59 - 周三
 * @Description:字节笔试
 */
public class Fuck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int K = sc.nextInt();
        int P = sc.nextInt();

        List<Integer> list = new ArrayList<>();
        List<List<Integer>> pos = new ArrayList<>();//[0]:巫师横坐标 [1]:纵坐标
        while (K > 0) {
            list.add(sc.nextInt());
            list.add(sc.nextInt());
            K--;
        }
        for (int i = 0; i < list.size(); i += 2) {
            List<Integer> temp = new ArrayList<>();
            temp.add(list.get(i));
            temp.add(list.get(i + 1));
            pos.add(temp);
        }
        System.out.println(dfs(row, col, pos, 0, 0) % P);
    }

    /**
     * @return: 从[i, j]出发到[M, N]有多少路线
     */
    public static int dfs(int row, int col, List<List<Integer>> pos, int i, int j) {
        if (i == row && j == col) {
            return 1;
        }
        int sum = 0;

        if (i + 1 <= row && j <= col) {
            for (List<Integer> po : pos) {
                //曼哈顿距离太小
                if (Math.abs(i - po.get(0)) + Math.abs(j - po.get(1)) <= 1) {
                    return 0;
                }
            }
            //此时从[i,j]出发有多少条路线的问题就转换成了从[i+1,j]出发有多少条路线
            sum += dfs(row, col, pos, i + 1, j);
        }
        if (i <= row && j + 1 <= col) {
            for (List<Integer> po : pos) {
                //曼哈顿距离太小
                if (Math.abs(i - po.get(0)) + Math.abs(j - po.get(1)) <= 1) {
                    return 0;
                }
            }
            sum += dfs(row, col, pos, i, j + 1);
        }
        return sum;
    }
}
