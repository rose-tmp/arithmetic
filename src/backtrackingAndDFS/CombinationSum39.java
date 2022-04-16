package backtrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/4/15-16:33 - 周五
 * @Description:剑指 Offer II 081. 允许重复选择元素的组合
 * 给定一个无重复元素的正整数数组candidates和一个正整数target，找出candidates中所有可以使数字和为目标数target的唯一组合。
 * candidates中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为target 的唯一组合数少于 150 个。
 * <p>
 * 示例1：
 * 输入: candidates = [2,3,6,7], target = 7
 * 输出: [[7],[2,2,3]]
 * <p>
 * 示例2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * <p>
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 * <p>
 * 示例 4：
 * 输入: candidates = [1], target = 1
 * 输出: [[1]]
 * <p>
 * 示例 5：
 * 输入: candidates = [1], target = 2
 * 输出: [[1,1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 * <p>
 * <p>
 * 注意：本题与主站 39题相同：https://leetcode-cn.com/problems/combination-sum/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/Ygoe9J
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum39 {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(new ArrayList<>(), 0, 0, candidates, target);
        return res;
    }

    public void dfs(List<Integer> list, int index, int sum, int[] candidates, int target) {
        if (sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            dfs(list, i, sum + candidates[i], candidates, target);
            list.remove(list.size() - 1);
        }
    }
}
