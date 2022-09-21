package writtenExam.ningmengweiqu;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ZwZ
 * @date: 2022-09-04 15:12
 * @desc:
 */
public class Main5 {
    /*public static int interBreakProblem(int n) {
        if (n == 2 || n == 3)
            return n - 1;
        if (n == 4) return n;
        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        return result * n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(interBreakProblem(n));
    }*/
    public static void main(String[] args) {
        Set<String>  set = new HashSet<>();
        set.add("AA");
        set.add("BB");
        set.add(new String("AA"));
        System.out.println(set);
    }
}
