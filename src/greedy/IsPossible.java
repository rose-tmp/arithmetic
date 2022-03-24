package greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/12/4 - 13:43
 * @Description:659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，
 * 其中每个子序列都由连续整数组成且长度至少为 3。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 *
 * 示例 2：
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 *
 * 示例 3：
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 *
 * 提示：
 * 输入的数组长度范围为 [1, 10000]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 */
public class IsPossible {
    /**
     * 当遍历到数组中的某一个值为x元素的时候  如果此时存在以x-1结尾的序列那么我们就可以将它加在其后面
     * 如果不存在的话，那我们就要以x开头重新构建一个序列出来  因为子序列的长度不能小于3 所以就要看此时数组中是否存在x+1和x+2
     * 至于如何判断是否存在以x-1结尾的序列这个可以定义一个map映射
     * 而每次遍历到数组中的任一元素的时候，如何知道它是否已经在某个子序列中
     * 所以还需要借助一个map对数组中的元素进行标记   即记录每个元素出现的次数 其随着元素不断的加入子序列而改变
     * */
    public boolean isPossible(int[] nums) {
        //nums.length < 3的这种情况是没有必要判断的  以下代码：
        //if (temp1 > 0 && temp2 > 0) {
        //    ...
        //}
        //就可以完成这个判断直接返回false 当然如果这里加了这个判断可以使得程序更早的结束
        /*if (nums == null || nums.length < 3) {
            return false;
        }*/
        //存储数组中每个元素的数量 key:元素值 value:数量
        Map<Integer, Integer> countMap = new HashMap<>();
        //存储以每个元素结尾的  序列的数量 key:元素值 value:序列的数量
        Map<Integer, Integer> endMap = new HashMap<>();
        //初始化countMap
        for (int i = 0; i < nums.length; i++) {
            /*if (countMap.containsKey(nums[i])) {
                int temp = countMap.get(nums[i]);
                countMap.put(nums[i], temp++);
            } else {
                countMap.put(nums[i], 1);
            }*/
            //可以使用以下代码代替上边的代码  getOrDefault( , )如果存在key == nums[i]则返回其对应value 如果不存在就返回0
            int count = countMap.getOrDefault(nums[i], 0) + 1;
            countMap.put(nums[i], count);
        }
        //遍历nums的目的就是为其中的每个元素找到他们对应的一个子序列  即他们的归属
        for (int j = 0; j < nums.length; j++) {
            int count = countMap.getOrDefault(nums[j], 0);
            //只有count > 0和count == 0这两种情况
            //如果count > 0那么就要给它找一个子序列作为它的归属
            //如果count == 0说明这个数已经属于了某个子序列，那么就不需要再为他找归属
            if (count > 0) {
                //以num[j - 1]结尾的子序列
                int preEnd = endMap.getOrDefault(nums[j] - 1, 0);
                //将num[j]加入到以num[j] - 1结尾的子序列中 构成新的子序列
                if (preEnd > 0) {
                    countMap.put(nums[j], count - 1);
                    endMap.put(nums[j] - 1, preEnd - 1);
                    endMap.put(nums[j], endMap.getOrDefault(nums[j], 0) + 1);
                }
                //此时num[j]与后边的数构成新序列
                else {
                    int temp1 = countMap.getOrDefault(nums[j] + 1, 0);
                    int temp2 = countMap.getOrDefault(nums[j] + 2, 0);
                    //可以与后边的数构成新序列
                    if (temp1 > 0 && temp2 > 0) {
                        countMap.put(nums[j], count - 1);
                        countMap.put(nums[j] + 1, temp1 - 1);
                        countMap.put(nums[j] + 2, temp2 - 1);
                        endMap.put(nums[j] + 2, endMap.getOrDefault(nums[j] + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsPossible possible = new IsPossible();
        boolean res = possible.isPossible(new int[]{1,2});
        System.out.println(res);
    }
}