package writtenExam.alibaba;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int k = sc.nextInt();
        //滑动窗口
        List<Integer> listK = new ArrayList<>();
        //存储结果
        List<Integer> res = new ArrayList<>();

        //大小为k个滑动窗口从arr的初始位置滑动到末尾
        for (int i = 0; i <= arr.length - k; i++) {
            if (listK.size() < k) {
                listK.add(arr[i]);
            }
            if (listK.size() == k) {
                //排序
                listK.sort(Comparator.comparingInt(o -> o));
                int min = listK.get(0);//滑动窗口中存储的元素的最小值
                int max = listK.get(k - 1);//滑动窗口中存储的元素的最大值
                int mid;//中位数
                if (k % 2 != 0) {
                    mid = listK.get(k / 2);
                } else {
                    mid = (listK.get(k / 2) + listK.get(k / 2 - 1)) / 2;
                }
                if ((max - mid) > (mid - min)) {
                    res.add(max);
                } else {
                    res.add(min);
                }

                listK.remove(0);//删除第0个位置上的元素->滑动窗口左移
            }
        }
        //输出
        for (int i = 0; i < listK.size(); i++) {
            if (i == 0) {
                System.out.print("[");
            }
            System.out.print(listK.get(i));
            if (i != listK.size() - 1) {
                System.out.print(",");
            } else {
                System.out.print("]");
            }
        }
    }
}
