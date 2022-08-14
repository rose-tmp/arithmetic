package writtenExam.meituan2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//n个订单
        int t = sc.nextInt();//每个订单的耗时
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        list.sort(Comparator.comparingInt(o -> o));
        int pre = 0;//送完上一单后的时刻
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            //自己送来得及就自己送
            if (cur - pre >= t) {
                pre += t;
            }else {//来不及就使用魔法
                res++;
            }
        }
        System.out.println(res);
    }
}
