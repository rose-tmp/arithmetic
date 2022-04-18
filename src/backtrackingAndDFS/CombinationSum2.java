package backtrackingAndDFS;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/4/18-19:21 - 周一
 * @Description:剑指 Offer II 082. 含有重复元素集合的组合
 * 给定一个可能有重复数字的整数数组candidates和一个目标数target，找出candidates中所有可以使数字和为target的组合。
 * candidates中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合。
 * <p>
 * 示例1:
 * 输入: candidates =[10,1,2,7,6,1,5], target =8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * <p>
 * 示例2:
 * 输入: candidates =[2,5,2,1,2], target =5,
 * 输出:
 * [
 * [1,2,2],
 * [5]
 * ]
 * <p>
 * 提示:
 * 1 <=candidates.length <= 100
 * 1 <=candidates[i] <= 50
 * 1 <= target <= 30
 * <p>
 * 注意：本题与主站 40题相同：https://leetcode-cn.com/problems/combination-sum-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sjJUc
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CombinationSum2 {
    List<List<Integer>> res = new ArrayList<>();

    /**
     * 与CombinationSum不同的地方在于重复元素的删除
     * 思路：
     * 1.排序
     * 2.遍历到的元素如果之前出现过就直接剪枝
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        fuck(new ArrayList<>(), 0, candidates, target);
        return res;
    }

    /***
     *
     * @param list
     * @param index
     * @param candidates
     * @param target
     */
    public void fuck(List<Integer> list, int index, int[] candidates, int target) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        if (sum == target) {
            res.add(new ArrayList<>(list));
        }
        //剪枝
        if (sum > target){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            //在回溯树的同一层上(只要在同一个for内的所有元素都在同一层)如果某个元素和上一个元素的值相同，则一定会出现重复，直接跳过
            if (i > index && candidates[i - 1] == candidates[i]) {
                continue;
            }
            list.add(candidates[i]);
            fuck(list, i + 1, candidates, target);
            list.remove(list.size() - 1);
        }
    }
}
