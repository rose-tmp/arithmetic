package easy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ZwZ
 * @date: 2022-09-24 20:43
 * @desc:
 */
public class IsHappy {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (true) {
            int sum = 0;
            while (n != 0) {
                sum += (n % 10) * (n % 10);
                n = n / 10;
            }
            n = sum;
            if (set.contains(n)) {
                return false;
            }
            else if (n == 1) {
                return true;
            }else {
            set.add(n);
            }
        }
    }

    public static void main(String[] args) {
        IsHappy isHappy = new IsHappy();
        System.out.println(isHappy.isHappy(19));
    }
}
