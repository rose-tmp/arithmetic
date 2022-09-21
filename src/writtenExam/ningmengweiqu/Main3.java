package writtenExam.ningmengweiqu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-04 11:18
 * @desc:
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] temp = new int[2];
            temp[0] = sc.nextInt();
            temp[1] = sc.nextInt();
            list.add(temp);
        }
        list.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        long sum = 0;
        int pre = -1;
        for (int i = 0; i < list.size(); i++) {
            int[] cur = list.get(i);
            //该时刻已经做了其他工作
            if (cur[0] == pre) {
                continue;
            }
            sum += cur[1];
            pre = cur[0];
        }
        System.out.println(sum);
    }
}
