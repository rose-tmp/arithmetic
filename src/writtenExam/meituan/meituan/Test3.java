package writtenExam.meituan.meituan;

import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> zList = new ArrayList<>();
        List<Integer> fList = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();//key:魔法编号 value：该魔法数量
        for (int i = 0; i < n; i++) {
            zList.add(sc.nextInt());
        }
        for (int i = 0; i < zList.size(); i++) {
            map.put(zList.get(i), 1 + map.getOrDefault(zList.get(i), 0));
        }
        int max = 1;
        for (int k : map.keySet()) {
            max = Math.max(map.get(k), max);
        }
        if (max > (n / 2)){
            System.out.println(0);
        }
    }
}
