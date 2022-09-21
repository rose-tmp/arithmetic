package writtenExam.zhongxing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-08 19:51
 * @desc:
 */

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new int[]{sc.nextInt(), sc.nextInt()});
        }

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o2[1] - o1[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });

        int res = n;
        for (int i = 0; i < list.size(); ) {
            int j = i + 1;
            while (j < list.size() && list.get(j)[1] <= list.get(i)[1]) {
                res--;
                j++;
            }
            i = j;
        }
        System.out.println(res);
    }
}
