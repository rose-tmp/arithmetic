package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/3/23 - 20:23
 * @Description:412. Fizz Buzz
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果n是3的倍数，输出“Fizz”；
 * 2. 如果n是5的倍数，输出“Buzz”；
 * 3.如果n同时是3和5的倍数，输出 “FizzBuzz”。
 * <p>
 * 示例：
 * <p>
 * n = 15,
 * <p>
 * 返回:
 * [
 * "1",
 * "2",
 * "Fizz",
 * "4",
 * "Buzz",
 * "Fizz",
 * "7",
 * "8",
 * "Fizz",
 * "Buzz",
 * "11",
 * "Fizz",
 * "13",
 * "14",
 * "FizzBuzz"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fizz-buzz
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        if (n <= 0) {
            return list;
        }
        for (int i = 1; i < n + 1; i++) {
            if (i % 3 != 0 && i % 5 != 0) {
                list.add(String.valueOf(i));
            } else {
                if (i % 3 == 0 && i % 5 == 0) {
                    list.add("FizzBuzz");
                } else if (i % 3 == 0) {
                    list.add("Fizz");
                } else {
                    list.add("Buzz");
                }
            }
        }
        return list;
    }
}
