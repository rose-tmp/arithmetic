package writtenExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-09-01 11:37
 * @desc:
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.substring(1, str.length() - 1);
        String[] sArr = str.split(",");
        int[] arr = new int[sArr.length];
        for (int i = 0; i < sArr.length; i++) {
            if (sArr[i].equals("#")) {
                //空节点
                arr[i] = Integer.MAX_VALUE;
            } else {
                arr[i] = Integer.parseInt(sArr[i]);
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            boolean isB = isBalance(arr, i);
            if (!isB) {

            }
        }
    }

    /**
     * @return 以arr[index]为根节点的树是否是平衡二叉树
     */
    private static boolean isBalance(int[] arr, int index) {
        if (index >= arr.length) {
            return false;
        }
        if (arr[index] == Integer.MAX_VALUE) {
            return true;
        }
        int lHigh = getHigh(arr, index * 2 + 1);
        int rHigh = getHigh(arr, index * 2 + 2);
        if (Math.abs(lHigh - rHigh) < 2) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * @return 以arr[index]为根节点的树的高度
     */
    private static int getHigh(int[] arr, int index) {
        if (index >= arr.length) {
            return 0;
        }
        if (arr[index] == Integer.MAX_VALUE) {
            return 0;
        }
        int lHigh = getHigh(arr, 1 + index * 2);
        int rHigh = getHigh(arr, 2 + index * 2);
        return 1 + Math.max(lHigh, rHigh);
    }
}
