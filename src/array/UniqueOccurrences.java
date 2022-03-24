package array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/10/28 - 9:53
 * @Description:1207. 独一无二的出现次数
 * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
 * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
 * <p>
 * 示例 1：
 * 输入：arr = [1,2,2,1,1,3]
 * 输出：true
 * 解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的出现次数相同。
 * <p>
 * 示例 2：
 * 输入：arr = [1,2]
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：arr = [-3,0,1,-3,1,1,1,-3,10,0]
 * 输出：true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-number-of-occurrences
 */
public class UniqueOccurrences {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        //map1记录每个元素出现的个数
        for (int i = 0; i < arr.length; i++) {
            if (map1.get(arr[i]) == null) {
                map1.put(arr[i], 1);
            } else {
                int temp = map1.get(arr[i]);
                map1.put(arr[i], ++temp);
            }
        }
        //遍历Map
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()
        ) {
            if(map2.get(entry.getValue()) == null){
                map2.put(entry.getValue(),entry.getKey());
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        UniqueOccurrences unique = new UniqueOccurrences();
        int[] arr = {1, 2, 2, 1, 1, 3};
        unique.uniqueOccurrences(arr);
    }
}
