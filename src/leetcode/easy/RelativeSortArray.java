package leetcode.easy;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2020/11/14 - 13:48
 * @Description:1122. 数组的相对排序
 * 给你两个数组，arr1 和arr2，
 * <p>
 * arr2中的元素各不相同
 * arr2 中的每个元素都出现在arr1中
 * 对 arr1中的元素进行排序，使 arr1 中项的相对顺序和arr2中的相对顺序相同。未在arr2中出现过的元素需要按照升序放在arr1的末尾。
 * <p>
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 * <p>
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2中的元素arr2[i]各不相同
 * arr2 中的每个元素arr2[i]都出现在arr1中
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 */
public class RelativeSortArray {
    /**
     * 按照顺序遍历arr2 在遍历到arr2每一个位置的时候
     * 记录arr1中此元素出现的次数 然后将对应个数的该元素加入list集合
     * 一次遍历arr2结束后，list中存储了arr2中出现过，且在arr1中出现过的元素按照题目要求的排列
     * <p>
     * 然后寻找arr1中没有在arr2中出现过的元素 从小到大排序之后加入到最终结果的后面
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        //将arr2中出现的元素  按照顺序加入list中
        for (int i = 0; i < arr2.length; i++) {
            //遍历arr1寻找相同元素
            for (int j = 0; j < arr1.length; j++) {
                //此元素个数++
                if (arr1[j] == arr2[i]) {
                    count++;
                }
            }
            while (count != 0) {
                list.add(arr2[i]);
                count--;
            }
        }
        //寻找arr1中没有出现在arr2中的元素
        int[] temp = new int[arr1.length - list.size()];
        int index = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (!set.contains(arr1[i])) {
                temp[index] = arr1[i];
                index++;
            }
        }
        Arrays.sort(temp);
        //将list中的元素加入最终数组
        int i;
        for (i = 0; i < list.size(); i++) {
            arr1[i] = list.get(i);
        }
        for (int j = i, k = 0; k < temp.length; j++, k++) {
            arr1[j] = temp[k];
        }
        return arr1;
    }

    /**
     * 借助map集合对上述方法的优化
     */
    public int[] relativeSortArrayImprove(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < arr2.length; i++) {
            set.add(arr2[i]);
        }
        Map<Integer, Integer> map = new HashMap<>();//key arr2[i]  value arr1中该元素出现的次数
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], 0);
        }
        //为Map赋值
        for (int i = 0; i < arr1.length; i++) {
            if (map.containsKey(arr1[i])) {
                map.put(arr1[i], map.get(arr1[i]) + 1);
            }
        }
        int[] res = new int[arr1.length];
        int len = 0;
        int j = 0;
        //将arr2中出现过的元素按照arr1中对应出现的个数加入到最终返回数组中
        for (int i = 0; i < arr2.length; i++) {
            int count = map.get(arr2[i]);
            len += count;
            while (count > 0) {
                res[j] = arr2[i];
                j++;
                count--;
            }
        }
        //寻找arr1中没有出现在arr2中的元素
        int[] temp = new int[arr1.length - len];
        int index = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (!set.contains(arr1[i])) {
                temp[index] = arr1[i];
                index++;
            }
        }
        Arrays.sort(temp);
        for (int i = j,k = 0; k < temp.length; i++,k++) {
            res[i] = temp[k];
        }
        return res;
    }

    public static void main(String[] args) {
        RelativeSortArray sort = new RelativeSortArray();
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        sort.relativeSortArrayImprove(arr1,arr2);
    }
}
