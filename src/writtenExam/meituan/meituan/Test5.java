package writtenExam.meituan.meituan;

import java.util.Scanner;

public class Test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();//询问次数
        String sb = "wowMeiTuannauTieMwow";
        for (int q = 0; q < T; q++) {
            long index = sc.nextLong();
            long index2 = (index + 2) % 20;
            System.out.println(sb.charAt((int)index2));
        }
    }
}
