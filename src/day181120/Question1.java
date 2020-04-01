package day181120;

import java.util.HashMap;
import java.util.Map;

/**
 * //                       .::::.
 * //                     .::::::::.
 * //                    :::::::::::
 * //                 ..:::::::::::'
 * //              '::::::::::::'
 * //                .::::::::::
 * //           '::::::::::::::..
 * //                ..::::::::::::.
 * //              ``::::::::::::::::
 * //               ::::``:::::::::'        .:::.
 * //              ::::'   ':::::'       .::::::::.
 * //            .::::'      ::::     .:::::::'::::.
 * //           .:::'       :::::  .:::::::::' ':::::.
 * //          .::'        :::::.:::::::::'      ':::::.
 * //         .::'         ::::::::::::::'         ``::::.
 * //     ...:::           ::::::::::::'              ``::.
 * //    ```` '            ':::::::::'                  ::::..
 * //                       '.:::::'                    ':'````..
 *
 * @author - ZwZ呀咿呀咿哟
 * @date - 2018/11/20 - 23:33
 * @Description:从整数数组中寻找两个数，之和等于给出的的一个数
 * https://leetcode-cn.com/problems/two-sum/solution/
 */
public class Question1 {
    //暴力搜索(时间复杂度O(n2))
    public static int[] twoSum(int[] nums, int target) {
        int[] reslut = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int n = target - nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == n && !(i == j)) {
                    reslut[0] = i;
                    reslut[1] = j;
                    return reslut;
                }
            }
        }
        throw new IllegalArgumentException("没有满足条件的两位数");
    }
    //二遍哈希表
    public static int[] twoSums2(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0;i<nums.length;i++){
            map.put(nums[i],i);
        }
        for(int i = 0;i<nums.length;i++){
            int n = target - nums[i];
            if(map.containsKey(n) && map.get(n) != i){
                return new int[] {i,map.get(n)};
            }
        }
        throw new IllegalArgumentException("数组中没有要找的两个数");
    }
    //一遍哈希表
    public int[] twoSums3(int[] nums,int target){
        Map<Integer,Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0;i<nums.length;i++){
            int n = target - nums[i];
            if(map.containsKey(n)){
                return new int[]{map.get(n),i};
            }
            map.put(nums[i],i);
        }
        throw new IllegalArgumentException("数组中没有要找的两位数");
    }
    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        int[] reslut = Question1.twoSum(nums,target);
        for(int i = 0;i<reslut.length;i++){
            System.out.println(reslut[i]);
        }
    }
}
