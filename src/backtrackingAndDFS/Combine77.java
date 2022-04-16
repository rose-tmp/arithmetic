package backtrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/4/14-20:24 - 周四
 * @Description:剑指 Offer II 080. 含有 k 个元素的组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例 1:
 * 输入:n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 示例 2:
 * 输入:n = 1, k = 1
 * 输出: [[1]]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * 注意：本题与主站 77题相同：https://leetcode-cn.com/problems/combinations/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/uUsW3B
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combine77 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(new ArrayList<>(), n, k, 1);
        return res;
    }

    /**
     * @Param: index 当前遍历到的位置(只取当前位置之后的数与其形成一个组合 从而避免出现[1,2]和[2,1]都加入res的情况)
     */
    public void dfs(List<Integer> list, int n, int k, int index) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            boolean flag = false;
            //避免重复(如加入[1,1])
            for (Integer integer : list) {
                if (integer == i) {
                    flag = true;
                    break;
                }
            }
            //重复
            if (flag) {
                continue;
            } else {
                list.add(i);
                dfs(list, n, k, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        Combine77 combine77 = new Combine77();
        combine77.combine(4, 2);
    }
}
