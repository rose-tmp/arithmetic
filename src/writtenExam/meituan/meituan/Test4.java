package writtenExam.meituan.meituan;

import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//样本数量
        int k = sc.nextInt();//类别数量
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        //key:样本编号 value:该类别样本的编号集合
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            List<Integer> temp;
            if (map.containsKey(list.get(i))) {
                temp = map.get(list.get(i));
                //加入当前编号
            } else {
                temp = new ArrayList<>();
            }
            temp.add(i + 1);
            map.put(list.get(i), temp);
        }
        //对所有样本list排序
        for (Integer i : map.keySet()) {
            List<Integer> temp = map.get(i);
            temp.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1 - o2;
                }
            });
            map.put(i, temp);
        }
        //训练样本
        List<Integer> trainList = new ArrayList<>();
        //测试样本
        List<Integer> testList = new ArrayList<>();
        for (Integer i : map.keySet()) {
            List<Integer> temp = map.get(i);
            int index = 0;
            if (temp.size() % 2 == 0) {
                index = temp.size() / 2 - 1;
            } else {
                index = temp.size() / 2;
            }
            for (int j = 0; j < temp.size(); j++) {
                if (j <= index) {
                    trainList.add(temp.get(j));
                } else {
                    testList.add(temp.get(j));
                }
            }
        }
        testList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        trainList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < trainList.size(); i++) {
            System.out.print(trainList.get(i));
            System.out.print(" ");
        }
        System.out.println();
        for (int i = 0; i < testList.size(); i++) {
            System.out.print(testList.get(i));
            System.out.print(" ");
        }
    }
}
