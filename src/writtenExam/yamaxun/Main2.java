package writtenExam.yamaxun;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-22 14:59
 * @desc:
 */
public class Main2 {
    public static int minimumGroups(List<Integer> awards, int k) {
        // Write your code here
        awards.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int ans = 0;
        //1 2 4 5 6 8 9
        int index = 0;
        while (index < awards.size()) {
            int temp = index + 1;
            while (temp < awards.size() && awards.get(temp) - awards.get(index) <= k) {
                temp++;
            }
            index = temp;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> awards = new ArrayList<>();
        awards.add(1);
        awards.add(13);
        awards.add(6);
        awards.add(8);
        awards.add(9);
        awards.add(3);
        awards.add(5);
        int ans = Main2.minimumGroups(awards,4);
        System.out.println(ans);
    }
}
