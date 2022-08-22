package writtenExam.yamaxun;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-22 14:39
 * @desc:
 */
public class Main1 {
    public static long findMinHealth(List<Integer> power, int armor) {
        // Write your code here
        long sum = 0;
        int max = 0;
        for (Integer i : power) {
            sum += i;
            max = Math.max(max, i);
        }
        if (armor > max) {
            sum -= max;
        } else {
            sum -= armor;
        }
        return sum + 1;
    }

    public static void main(String[] args) {
        List<Integer> power = new ArrayList<>();
        power.add(1);
        power.add(2);
        power.add(3);
       // power.add(7);
        long ans ;
        ans = Main1.findMinHealth(power,1);
        System.out.println(ans);
    }
}
