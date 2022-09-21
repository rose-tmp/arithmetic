package writtenExam.Thoughtworks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-27 21:10
 * @desc:
 */
class Person {
    private String name = "person";
    int age = 0;
}

public class Main4 extends Person {
    /*public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        //存放偶数位置上的元素 key:元素 val:元素出现次数
        Map<Integer, Integer> map1 = new HashMap<>();
        //存放奇数位置上的元素 key:元素 val:元素出现次数
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if (i % 2 == 0) {
                int count = map1.getOrDefault(arr[i], 0);
                map1.put(arr[i], count + 1);
            } else {
                int count = map2.getOrDefault(arr[i], 0);
                map2.put(arr[i], count + 1);
            }
        }
        //偶数位置元素信息 arr[0]:元素值 arr[1]:元素出现次数
        List<int[]> list1 = new ArrayList<>();
        //奇数位置元素信息 arr[0]:元素值 arr[1]:元素出现次数
        List<int[]> list2 = new ArrayList<>();
        for (int num : map1.keySet()) {
            list1.add(new int[]{num, map1.get(num)});
        }
        for (int num : map2.keySet()) {
            list2.add(new int[]{num, map2.get(num)});
        }
        //将元素根据出现次数倒序排序排列
        list1.sort(((o1, o2) -> o2[1] - o1[1]));
        list2.sort(((o1, o2) -> o2[1] - o1[1]));

        int[] arr1 = list1.get(0);
        int[] arr2 = list2.get(0);
        if (arr1[0] == arr2[0]) {
            if (arr1[1] + list2.get(1)[1] > arr2[1] + list1.get(1)[1]) {
                arr2 = list2.get(1);
            } else {
                arr1 = list1.get(1);
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                if (arr[i] != arr1[0])
                    count++;
            } else {
                if (arr[i] != arr2[0])
                    count++;
            }
        }
        System.out.println(count);
    }*/

    /*public static void main(String[] args) {
        System.out.println(10|12);
        System.out.println(15&14);
        System.out.println(~(-15));

    }*/

    public static void main(String[] args) {
        List<A> list1 = new ArrayList<>();
        List<B> list2 = new ArrayList<>();
        List<C> list3 = new ArrayList<>();
        List list4 = new ArrayList<>();

        //method(list1);
    }

    public static void method(List<? extends B> list) {

    }

}

class A {
}

class B extends A {
}

class C extends B {
}
