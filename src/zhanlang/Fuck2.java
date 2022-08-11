package zhanlang;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/7/6-21:26 - 周三
 * @Description:字节笔试
 */
public class Fuck2 {
    static class Node {
        List<Node> child = new ArrayList<>();//跟随它的人
        Node next;//自己所跟随的人

        int value;
    }

    public static int getMax(Node node) {
        if (node.child.size() == 0) {
            return 1;
        }
        int sum = 1;
        for (int i = 0; i < node.child.size(); i++) {
            sum += getMax(node.child.get(i));
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Node> list = new ArrayList<>();
        while (sc.hasNextInt()) {
            Node node = new Node();
            node.value = sc.nextInt();
            list.add(node);
        }

        int max = 1;
        for (int i = 0; i < list.size(); i += 2) {
            if (list.get(i).value == list.get(i + 1).value) {
                continue;
            }
            if (list.get(i + 1).value > 0) {
                //之前认过爹
                if (list.get(i).next != null) {
                    //之前的爹把它逐出家门
                    list.get(i).next.child.remove(list.get(i));
                }
                //A跟随B
                list.get(i).next = list.get(i + 1);
                //B多个儿子
                list.get(i + 1).child.add(list.get(i));
            }
            //主动和爹断绝，且没找到下一个爹，单飞了属于是
            if (list.get(i + 1).value == 0) {
                if (list.get(i).next != null) {
                    list.get(i).next.child.remove(list.get(i));
                }
                list.get(i).next = null;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            max = 1+ Math.max(max, getMax(list.get(i)));
        }
        System.out.println(max);
    }
}
