package writtenExam.honor;

import java.util.*;

public class Main1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.length() == 0) {
            System.out.println("error.0001");
            return;
        }
        String[] arr = str.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (!isValid(arr[i])) {
                System.out.println("error.0001");
                return;
            }
        }

        // key:次数 val:名字
        Map<Integer, List<String>> map1 = new HashMap<>();
        // key:名字 val:次数
        Map<String, Integer> map2 = new HashMap<>();
        //统计每个名字的出现次数
        for (String s : arr) {
            if (map2.containsKey(s)) {
                map2.put(s, 1 + map2.get(s));
            } else {
                map2.put(s, 1);
            }
        }
        //寻找最大次数
        int max = 0;

        //初始化map1
        for (String k : map2.keySet()) {
            int count = map2.get(k);
            List<String> list;
            if (map1.containsKey(count)) {
                list = map1.get(count);
            } else {
                list = new ArrayList<>();
            }
            list.add(k);
            map1.put(count, list);

            max = Math.max(max, map2.get(k));
        }

        List<String> ans = map1.get(max);
        if (ans.size() == 1) {
            System.out.println(ans.get(0));
        } else {
            Collections.sort(ans);
            System.out.println(ans.get(0));
        }
    }

    /**
     * @return 是否合法
     */
    private static boolean isValid(String str) {
        if (str.length() == 0) {
            return false;
        }
        if (!Character.isUpperCase(str.charAt(0))) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (!Character.isLowerCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

