package zuoPrimary.dongtaiguihua;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 22:20
 * @Description:使用动态规划对暴力递归的IsAim进行优化
 * 在暴力递归的IsAim中，只要是参数i和sum确定之后，返回值一定是确定的，和之前做了什么决策来到了这里没有任何关系
 */
public class DpIsAim {
    /** 
    * @Author: ZwZ
    * @Description:arr,aim都是确定的，i和sum则是dp矩阵的行和列
     * sum列的范围是：0----数组中所有数的和
    * @Param: [arr, i, sum, aim] 
    * @return: boolean 
    * @Date: 2020/3/29-22:25
    */
    public boolean dpIsAim(int[] arr, int i,int sum,int aim){
        int SUM = 0;
        for (int j = 0; j < arr.length; j++) {
            SUM += arr[j];
        }
        if(aim < SUM)
            return false;
        boolean[][] dp = new boolean[arr.length+1][SUM+1];//行为i 列为0---SUM
        //根据暴力递归中递归的终止条件 为dp表赋值 即最后枚举到最后一行的时候，只有sum == aim的时候才为true
        for (int j = 0; j <= SUM; j++) {
            if(j == aim)
                dp[arr.length][j] = true;
            else
                dp[arr.length][j] = false;
        }
        for (int j = dp.length - 2; j >= 0; j--) {
            for (int k = 0; k < dp[0].length; k++) {
                dp[j][k] = k+ arr[j] <= SUM ? (dp[j+1][k] || dp[j+1][k+arr[j]]) : dp[j+1][k];
            }
        }
        return dp[i][sum];
    }

    public static void main(String[] args) {
        DpIsAim isAim = new DpIsAim();
        int[] arr = {2,5,7};
        System.out.println(isAim.dpIsAim(arr,0,0,15));
    }
}
