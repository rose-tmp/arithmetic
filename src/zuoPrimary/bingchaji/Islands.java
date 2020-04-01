package zuoPrimary.bingchaji;

/**
 * @author - ZwZ
 * @date - 2020/3/27 - 13:57
 * @Description:岛问题，一个矩阵中只有两个值：0 ，1 每个位置都可以与自己的上下左右四个位置相连，
 * 如果有一片1连在一起，这一部分就叫做岛，求一个矩阵中有多少个岛
 * 举例：
 * 001010
 * 111010
 * 100100
 * 000000
 * 这个矩阵中有三个岛
 */
public class Islands {
    /** 
    * @Author: ZwZ
    * @Description:此方法没有使用并查集，如果是这个问题用多个机器跑，计算出来的结果要合并的时候，由于合并的时候可能会导致
     * 岛数量的减少，这时就可以引入并查集的思想 
    * @Param: [m] 
    * @return: int 
    * @Date: 2020/3/27-14:17
    */
    public static int countIslands(int[][] m) {
        if (m == null || m[0] == null) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int res = 0;//岛数量
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (m[i][j] == 1) {
                    res++;
                    infect(m, i, j, row, col);
                }
            }
        }
        return res;
    }

    /** 
    * @Author: ZwZ
    * @Description:感染函数 
    * @Param: [m, row, col, rowSum, colSum] 
    * @return: void 
    * @Date: 2020/3/27-14:33
    */
    private static void infect(int[][] m, int row, int col, int rowSum, int colSum) {
        if (row < 0 || row >= rowSum || col < 0 || col >= colSum || m[row][col] != 1) {
            return;
        }
        m[row][col] = 2;//感染为2
        infect(m, row + 1, col, rowSum, colSum);
        infect(m, row - 1, col, rowSum, colSum);
        infect(m, row, col + 1, rowSum, colSum);
        infect(m, row, col - 1, rowSum, colSum);
    }

    public static void main(String[] args) {
        int[][] m1 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m1));

        int[][] m2 = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},};
        System.out.println(countIslands(m2));

    }
}
