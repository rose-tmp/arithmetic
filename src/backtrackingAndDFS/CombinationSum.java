package backtrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/15 - 10:24
 * @Description:39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 *     所有数字（包括 target）都是正整数。
 *     解集不能包含重复的组合。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 提示：
 *     1 <= candidates.length <= 30
 *     1 <= candidates[i] <= 200
 *     candidate 中的每个元素都是独一无二的。
 *     1 <= target <= 500
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 */
public class CombinationSum {
    List<List<Integer>> result = new ArrayList();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        backTrack(candidates,new ArrayList(),target,0);
        return result;
    }
    /**
     index可以达到不重复的目的 例如：
     [2,3,6,7] 7返回的是[2,2,3] [2,3,2],[3,2,2],[7]
     */
    public void backTrack(int[] candidates,List<Integer> list,int target,int index){
        int sum = 0;
        for(int i : list){
            sum += i;
        }
        if(sum == target){
            result.add(new ArrayList(list));
            return;
        }
        for(int i = index;i < candidates.length;i++){
            if(sum + candidates[i] > target){
                continue;
            }else{
                list.add(candidates[i]);
                backTrack(candidates,list,target,i);
                list.remove(list.size() - 1);
            }
        }
    }
}
