package writtenExam.meituan.meituan;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();//数据组数
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int xSum = sc.nextInt();
            int ySum = sc.nextInt();
            list.add(new int[]{xSum, ySum});
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int count = getCount(list.get(i));
            ans.add(count);
        }
        for (Integer i : ans) {
            System.out.println(i);
        }
    }

    /***
     * @return 最大的礼盒数量
     */
    public static int getCount(int[] arr) {
        int xSum = arr[0];
        int ySum = arr[1];
        if (xSum < (ySum / 2)) {
            return xSum;
        }
        int count = 0;
        while (xSum > 0 && ySum > 0) {
            if (xSum > ySum) {
                xSum -= 2;
                ySum--;
            } else {
                xSum--;
                ySum -= 2;
            }
            count++;
        }
        return count;
    }
}
