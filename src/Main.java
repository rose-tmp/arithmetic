import java.util.Arrays;
import java.util.Scanner;

/**
 * @author - ZwZ
 * @date - 2022/7/3-16:08 - 周日
 * @Description:输入
 *
 * https://blog.csdn.net/DecafTea/article/details/113846564
 */
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        m.readString();
    }

    /**
     * 使用nextLine()读,然后存入String[]
     * 再用int = Integer.parseInt( string )把String[]中每一个string转化为int
     */
    public void readString() {
        Scanner sc = new Scanner(System.in);
        //读入字符串数组(每个数之间使用空格隔开的，当然这个随自己设定)
        String[] temp = sc.nextLine().split(" ");
        //字符串数组转整数数组
        int[] arr = new int[temp.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        //打印整数数组
        System.out.println(Arrays.toString(arr));
        //打印数组中的每一个元素,用逗号隔开
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ", ");
        }
    }

    /**
     * 用来读取Int类型的数
     * 这个while循环中有两个变量接收in.nextInt();
     * 也就意味着当读到两个int类型的数了之后就会直接sout然后继续while到下一个循环中去
     * 注意：这两个int类型的数可以在同一行用空格隔开 也可以换行，换多少行都无所谓
     */
    public void readInt() {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { //注意while处理多个case  int a = in.nextInt();
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println("a:" + a);
            System.out.println("b:" + b);
        }
    }
}
