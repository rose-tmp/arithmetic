package writtenExam.T4399;

import java.util.Scanner;

/**
 * @author: ZwZ
 * @date: 2022-08-18 20:15
 * @desc:
 */
public class Main2 {

    public static int maxSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            n = Math.max(dp[i - 2] + nums[i], nums[i]);
            dp[i] = Math.max(n, dp[i - 1]);
        }
        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(",");
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        System.out.println(maxSum(nums));
    }
}
