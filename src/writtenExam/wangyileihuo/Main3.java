package writtenExam.wangyileihuo;

/**
 * @author: ZwZ
 * @date: 2022-08-14 16:00
 * @desc:
 */
public class Main3 {


    public class Solution {
        /**
         * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
         *
         * @param n  int整型 方格矩阵Cn下标
         * @param x1 int整型 方格1在方格矩阵中的横坐标
         * @param y1 int整型 方格1在方格矩阵中的纵坐标
         * @param x2 int整型 方格2在方格矩阵中的横坐标
         * @param y2 int整型 方格2在方格矩阵中的纵坐标
         * @return long长整型
         */
        public long solution(int n, int x1, int y1, int x2, int y2) {
            // write code here
            int map[][] = new int[2][2];
            map[0][0] = 1;
            map[0][1] = 2;
            map[1][1] = 3;
            map[1][0] = 4;
            for (int i = 2; i <= n; i++) {
                map = expands(map);
            }
            long a = map[x1 - 1][y1 - 1];
            long b = map[x2 - 1][y2 - 1];
            return b - a;
        }

        private int[][] expands(int[][] map) {
            int valmap[][] = new int[map.length * 2][map.length * 2];
            int shun[][] = xuanzhuan(map, map.length, map.length);
            int map1[][] = new int[map.length][map.length];
            int map2[][] = new int[map.length][map.length];
            int ni[][] = nishizhen(map, map.length, map.length);
            int val = map.length * map.length;
            int len = map.length;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    shun[i][j] = val + 1 - shun[i][j];
                    ni[i][j] = val + 1 - ni[i][j] + (val) * 3;
                    map1[i][j] = val + map[i][j];
                    map2[i][j] = (val) * 2 + map[i][j];
                }
            }
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    valmap[i][j] = shun[i][j];
                    valmap[i + len][j] = ni[i][j];
                    valmap[i][j + len] = map1[i][j];
                    valmap[i + len][j + len] = map2[i][j];
                }
            }
            return valmap;
        }

        //逆时针90旋转矩阵
        public int[][] nishizhen(int arry1[][], int m, int n) {
            int arry3[][] = new int[n][m];
            int dst;
            for (int x = 0; x < m; x++) {
                dst = n - 1;//因为要从最后一行向前赋值
                for (int y = 0; y < n; y++, dst--) {
                    arry3[dst][x] = arry1[x][y];
                    //在内循环中保持列不变，实现一列一列的赋值，同时也是从最后一行向前赋值
                }
            }
            return arry3;
        }

        //顺时针90旋转矩阵
        public int[][] xuanzhuan(int arry1[][], int m, int n) {
            //m表示原数组的行，n表示原数组的列
            int arry2[][] = new int[n][m];
            int dst;
            dst = m - 1;//因为要从最后一列向前赋值
            for (int x = 0; x < m; x++, dst--) {
                for (int y = 0; y < n; y++) {
                    arry2[y][dst] = arry1[x][y];
                    //在内循环中保持列不变，实现一列一列的赋值，同时也是从最后一列向前赋值
                }
            }
            return arry2;
        }
    }
}
