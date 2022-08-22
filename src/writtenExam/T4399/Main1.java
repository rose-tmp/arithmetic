package writtenExam.T4399;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author: ZwZ
 * @date: 2022-08-18 19:50
 * @desc:
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        str1 = str1.substring(1, str1.length() - 1);
        String str2 = sc.nextLine();
        str2 = str2.substring(1, str2.length() - 1);
        String[] arr1 = str1.split(",");
        String[] arr2 = str2.split(",");

        int[] arr = new int[arr1.length];
        int[] order = new int[arr2.length];

        for (int i = 0; i < arr1.length; i++) {
            arr[i] = Integer.parseInt(arr1[i]);
        }

        for (int i = 0; i < arr2.length; i++) {
            order[i] = Integer.parseInt(arr2[i]);
        }

        boolean ans = isStackOutOrder(arr, order);
        System.out.println(ans);
    }

    public static boolean isStackOutOrder(int[] arr, int[] order) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;       //i-->order[]  所要进行判断的数组
        int j = 0;       //j-->arr[]    主数组
        //进行判断
        for (; j < arr.length; ) {
            stack.push(arr[j++]);      //将arr[j]入栈后，j立即++ , 此时j为堆栈内元素个数
            for (; i < j; ) {          //i<j : i小于当前栈内元素个数(j)    防止空栈异常--->出栈若干个后栈为空(防止i减过头)--->不能用i<order.length
                if (order[i] == stack.peek()) {      //需防止空栈异常(上一步)
                    stack.pop();
                    i++;              //只有元素出栈后才i++
                } else {
                    break;           //若没有元素相等，则继续比较，不进行i++操作
                }
            }
        }
        //判断完成，输出结果
        if (i == order.length) {    //或者 if(stack.isEmpty()) 进行判断
            return true;
        } else return false;
    }
}


