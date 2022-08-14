package writtenExam.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main2 {
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
