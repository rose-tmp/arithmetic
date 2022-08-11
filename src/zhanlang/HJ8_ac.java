package zhanlang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/4-21:31 - 周一
 * @Description:
 */
public class HJ8_ac {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        //key:index value:总和
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>(new Com());
        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }
        //int num = list.get(0);
        for (int i = 1; i < list.size(); i += 2) {
            map.put(list.get(i), list.get(i + 1) + map.getOrDefault(list.get(i), 0));
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    static class Com implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
