/**
 * @author: ZwZ
 * @date: 2022-09-03 20:33
 * @desc:
 */
public class Main1 {
    /*static class ShowMeBug {
        public final int[][] arr;

        public ShowMeBug(int[][] arr) {
            this.arr = arr;
        }

        public static void main(String[] args) {
            int[][] arr = new int[][]{
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            };

            final ShowMeBug showMeBug = new ShowMeBug(arr);

            showMeBug.updateNextState(arr);
        }

        *//**
     * 更新arr下个tick的生存状态
     *
     * @param arr 当前状态下的数组
     *//*
        public void updateNextState(int[][] arr) {
            int M = arr.length;
            int N = arr[0].length;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = getNextVal(arr, i, j);
                }
            }
        }

        *//**
     * 计算某细胞下个tick的状态
     *
     * @param arr 当前状态下的数组
     * @param i   行索引
     * @param j   列索引
     * @return 生存返回1，否则返回0
     *//*
        private int getNextVal(int[][] arr, int i, int j) {
            int nextVal = 0;
            //TODO:请完善该函数，要求：需要调用countLivingNeighbors
            int count = countLivingNeighbors(arr, i, j);
            if (arr[i][j] == 0) {
                if (count >= 3) {
                    nextVal = 1;
                }
            } else {
                if (count < 2) {
                    nextVal = 0;
                } else if (count == 2 || count == 3) {
                    nextVal = 1;
                } else {
                    nextVal = 0;
                }
            }
            return nextVal;
        }

        */

    /**
     * 计算某细胞的存活邻居数量
     *
     * @param arr 当前状态下的数组
     * @param i   行索引
     * @param j   列索引
     * @return 该元素存活的邻居数量
     *//*
        private int countLivingNeighbors(int[][] arr, int i, int j) {
            int count = 0;
            //TODO:请完善该函数
            int[][] dict = new int[][]{
                    {0, -1}, {0, 1},//同一行
                    {1, -1}, {1, 0}, {1, 1},//下一行
                    {-1, -1}, {-1, 0}, {-1, 1}//上一行
            };
            for (int m = 0; m < dict.length; m++) {
                int row = dict[m][0] + i;
                int col = dict[m][1] + j;
                if (row >= 0 && row < arr.length &&
                        col >= 0 && col < arr[0].length) {
                    if (arr[row][col] == 1) {
                        count++;
                    }
                }
            }

            return count;
        }

        private boolean[][] initFlag(int[][] arr) {
            boolean[][] flag = new boolean[arr.length][arr[0].length];
            int[][] dict = new int[][]{
                    {0, -1}, {0, 1},//同一行
                    {1, -1}, {1, 0}, {1, 1},//下一行
                    {-1, -1}, {-1, 0}, {-1, 1}//上一行
            };

            for (int k = 0; k < arr.length; k++) {
                for (int l = 0; l < arr[0].length; l++) {
                    int temp = 0;
                    for (int m = 0; m < dict.length; m++) {
                        int row = dict[m][0] + k;
                        int col = dict[m][1] + l;
                        if (row >= 0 && row < arr.length &&
                                col >= 0 && col < arr[0].length) {
                            if (arr[row][col] == 1) {
                                temp++;
                            }
                        }
                    }
                    //当前细胞为死亡状态
                    if (arr[k][l] == 0) {
                        //该细胞是活细胞
                        if (temp >= 3) {
                            flag[k][l] = true;
                        }
                    } else {
                        if (temp < 2) {
                            flag[k][l] = false;
                        } else if (temp == 2 || temp == 3) {
                            flag[k][l] = true;
                        } else {
                            flag[k][l] = false;
                        }
                    }
                }
            }
            return flag;
        }
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{};
        GameOfLife gol = new GameOfLife(arr);
        Thread1 t1 = new Thread1(gol);
        t1.start();
    }

    public static class Thread1 extends Thread {
        GameOfLife gol;

        public Thread1(GameOfLife gol) {
            this.gol = gol;
        }

        @Override
        public void run() {
            TimerTask timerTask = new TimerTask() {

                @Override
                public void run() {
                    gol.updateNextState(arr);
                }
            };
        }
    }*/
}
