package zuoPrimary.juzhen;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 12:17
 * @Description:结合Day11printMatrix
 * 将一个方阵旋转90° 旋转后的相对应位置的数字交换位置
 * 思路:依旧从左上角和右下角出发，一次旋转一圈，然后左上角和右下角同时向内靠拢
 */
public class Rotate {
    public void rotate(int[][] arr){
        int leftRow = 0;//左上角行号
        int leftCol = 0;
        int rightRow = arr.length - 1;
        int rightCol = arr[0].length - 1;//右下角列号(由于是方阵，所以右下角行号和列号相等)
        //按圈旋转
        while (leftRow <= rightRow){
            rotateEdge(arr,leftRow++,leftCol++,rightRow--,rightCol--);
        }
    }
    //旋转某一圈的所有数值
    private void rotateEdge(int[][] arr,int leftRow,int leftCol,int rightRow,int rightCol){
        int times = rightCol - leftCol;//旋转次数
        //通过for()控制一圈中每次旋转所对应的四个点的数值交换
        for (int i = 0; i < times; i++) {
            int temp = arr[leftRow][leftCol + i];
            arr[leftRow][leftCol + i] = arr[rightRow - i][leftCol];
            arr[rightRow - i][leftCol] = arr[rightRow][rightCol - i];
            arr[rightRow][rightCol - i] = arr[leftRow + i][rightCol];
            arr[leftRow + i][rightCol] = temp;
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        rotate.rotate(arr);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println(" ");
        }
    }

    /**
     * @author - ZwZ
     * @date - 2020/3/25 - 13:46
     * @Description:之字型打印矩阵 1, 2, 3, 4
     * 5, 6, 7, 8
     * 9,10,11,12
     * 13,14,15,16
     * 输出结果：1，2，5，9，6，3，4，7，10，13，14，11，8，12，15，16
     * 思路：引入两个指针，A指针向右走走到底之后向下走 B指针向下走走的到底之后向右走
     * 每次A B走完之后都可以连成一个线段，打印这个线段上的数值即可
     * 使用一个bool类型用来决定打印的方向
     */
    public static class ZhiPrintMatrix {
        public ArrayList<Integer> zhiPrintMatrix(int[][] arr) {
            ArrayList<Integer> list = new ArrayList<>();
            if (arr == null)
                return list;
            else {
                int p1Row = 0;
                int p1Col = 0;
                int p2Row = 0;
                int p2Col = 0;
                boolean flag = true;
                int rowSum = arr.length;//总行数
                int colSum = arr[0].length;//总列数
                //p1指针没有走到最后一行(也可以用：p2指针没有走到最后一列作为while的条件)
                while (p1Row != rowSum) {
                    printDiagonal(list, arr, p1Row, p1Col, p2Row, p2Col, flag);
                    p1Row = p1Col == colSum - 1 ? p1Row + 1 : p1Row;
                    p1Col = p1Col == colSum - 1 ? p1Col : p1Col + 1;
                    p2Col = p2Row == rowSum - 1 ? p2Col + 1 : p2Col;
                    p2Row = p2Row == rowSum - 1 ? p2Row : p2Row + 1;
                    flag = !flag;
                }
                return list;
            }
        }

        //打印对角线
        private void printDiagonal(ArrayList<Integer> list, int[][] arr, int p1Row, int p1Col, int p2Row, int p2Col, boolean flag) {
            if (flag) {
                while (p1Row != p2Row + 1) {
                    list.add(arr[p1Row++][p1Col--]);
                }
            } else {
                while (p2Row != p1Row - 1) {
                    list.add(arr[p2Row--][p2Col++]);
                }
            }
        }

        public static void main(String[] args) {
            ZhiPrintMatrix zhiPrintMatrix = new ZhiPrintMatrix();
            int[][] arr = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
            ArrayList list = zhiPrintMatrix.zhiPrintMatrix(arr);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        }
    }
}
