package writtenExam.microsoft.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-19 19:12
 * @desc:
 */
public class Main1 {
    public int solution(int[] X, int[] Y, int W) {
        List<Integer> list = new ArrayList<>();
        for (int x : X) {
            list.add(x);
        }
        //从小到达排序
        list.sort(Comparator.comparingInt(o -> o));
        int ans = 0;
        while (list.size() != 0) {
            int firstX = list.get(0);
            int endX = firstX + W;
            int len = list.size();
            List<Integer> temp = new ArrayList<>();
            //将所有小于等于endX的元素删除 -> 压路机压一次
            for (int i = 0; i < len; i++) {
                if (list.get(i) <= endX) {
                    continue;
                }
                temp.add(list.get(i));
            }
            list.clear();
            list = temp;
            ans++;
        }
        return ans;
    }
}
