package zhanlang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/3-21:37 - 周日
 * @Description:
 */
public class HJ3_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        while (N > 0) {
            N--;
            set.add(sc.nextInt());
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int s : set) {
            res[i] = s;
            i++;
        }
        Arrays.sort(res);
        for (int j = 0; j < res.length; j++) {
            System.out.println(res[j]);
        }
    }
}
