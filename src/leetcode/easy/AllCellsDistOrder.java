package leetcode.easy;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/17 - 13:28
 * @Description:1030. 距离顺序排列矩阵单元格
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为(r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * <p>
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * <p>
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * <p>
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/matrix-cells-in-distance-order
 */
public class AllCellsDistOrder {
    public int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {
        Map<Integer, List<int[]>> map = new HashMap<>();//key 距离  value 位置
        List<Integer> dis = new ArrayList<>();
        //行
        for (int r = 0; r < R; r++) {
            //列
            for (int c = 0; c < C; c++) {
                int distance = Math.abs(r - r0) + Math.abs(c - c0);
                if (!dis.contains(distance)) {
                    dis.add(distance);
                }
                if (map.containsKey(distance)) {
                    map.get(distance).add(new int[]{r, c});
                } else {
                    map.put(distance, new ArrayList<>());
                    map.get(distance).add(new int[]{r, c});
                }
            }
        }
        Collections.sort(dis);
        int[][] ans = new int[R * C][2];
        int index = 0;
        for (int i = 0; i < ans.length; ) {
            List<int[]> temp = map.get(dis.get(index));
            ++index;
            for (int j = 0; j < temp.size(); j++) {
                ans[i] = temp.get(j);
                i++;
            }
        }
        return ans;
    }
    /**
     * 先将二维数组打平 并将二维数组中的坐标存入结果数组中
     * 然后使用Arrays.sort对数组中的元素进行排序
     * */
    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] ret = new int[R * C][];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                ret[i * C + j] = new int[]{i, j};
            }
        }
        Arrays.sort(ret, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return (Math.abs(a[0] - r0) + Math.abs(a[1] - c0)) - (Math.abs(b[0] - r0) + Math.abs(b[1] - c0));
            }
        });
        return ret;
    }
}
