package zhanlang;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author - ZwZ
 * @date - 2022/7/7-21:56 - 周四
 * @Description:
 */
public class HJ26_ac {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        char[] res = new char[str.length()];
        //标记res中对应位置是否占用
        boolean[] flag = new boolean[str.length()];
        Map<Character, String> map = new HashMap<>();//key:小写字母 value:该字母所对应的有序序列
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //大写字母转小写
            if (c >= 65 && c <= 90) {
                c = (char) (c + 32);
            }
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + str.charAt(i));
            } else {
                map.put(c, str.charAt(i) + "");
            }
            //记录非字母的位置
            if (str.charAt(i) < 65 || (str.charAt(i) > 90 && str.charAt(i) < 97) || (str.charAt(i) > 122)) {
                res[i] = str.charAt(i);
                flag[i] = true;
            }
        }
        //开始处理是字母的位置
        for (int i = 97; i <= 122; i++) {
            if (!map.containsKey((char) i)) {
                continue;
            }
            for (int j = 0; j < map.get((char) i).length(); j++) {
                //第一个没有被赋值的位置
                int index = 0;
                for (int k = 0; k < flag.length; k++) {
                    if (flag[k]) {
                        continue;
                    }
                    index = k;
                    break;
                }
                res[index] = map.get((char) i).charAt(j);
                flag[index] = true;
            }
        }
        for (char re : res) {
            System.out.print(re + "");
        }
        Lock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
}
