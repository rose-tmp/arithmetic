package zuoPrimary.dongtaiguihua;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 18:05
 * @Description:将暴力递归中MinPath问题修改成DP版本
 */
public class DpMinPath {
    //1，写出暴力递归版本
    public int walkBaoli(int[][] matrix,int i,int j){
        //递归终止条件
        if(i == matrix.length - 1 && j == matrix[0].length - 1)
            return matrix[i][j];
        //走到了最后一行
        if(i == matrix.length - 1)
            return matrix[i][j] + walkBaoli(matrix,i,j+1);
        //走到了最后一列
        if(j == matrix[0].length - 1)
            return matrix[i][j] + walkBaoli(matrix,i+1,j);
        int down = walkBaoli(matrix,i + 1,j);//向下走所需要的最小代价
        int right = walkBaoli(matrix,i,j+1);//向右走所需要的最短路径
        return matrix[i][j] + Math.min(right,down);
    }
    //2.分析是否存在大量重复计算过程，以及是否满足无后效性  两者都满足，那么可以使用动态规划优化
    /** 
    * @Author: ZwZ
    * @Description: walkBaoli(matrix,0,0)依赖于walkBaoli(matrix,0,1)和walkBaoli(matrix,1,0)
     * 而(1,0)和(0,1)都依赖(1,1)，并且满足无后效性 
    * @Param: [matrix, i, j] 
    * @return: int 
    * @Date: 2020/3/29-18:11
    */
    public int walkDp(int[][] matrix,int i,int j){
        //动态规划表
        int[][] dp = new int[matrix.length][matrix[0].length];//每一个位置上存储此位置到达右下角的最小代价
        //根据暴力递归中的递归终止条件为dp表赋值(i == matrix.length - 1 && j == matrix[0].length - 1即右下角时，此时的最小代价也就是matrix[matrix.length - 1][matrix[0].length - 1])
        dp[matrix.length - 1][matrix[0].length - 1] = matrix[matrix.length - 1][matrix[0].length - 1];
        /*用dp[matrix.length - 1][matrix[0].length - 1]推出来dp的最后一行每个位置所对应的值
        参考暴力递归中if(i == matrix.length - 1)里面的递推式*/
        for (int k = matrix[0].length - 2; k >= 0; k--) {
            dp[matrix.length - 1][k] = matrix[matrix.length - 1][k] + dp[matrix.length - 1][k+1];
        }
        /*用dp[matrix.length - 1][matrix[0].length - 1]推出来dp的最后一列每个位置所对应的值
        参考暴力递归中if(j == matrix[0].length - 1)里面的递推式*/
        for (int k = matrix.length - 2; k >= 0 ; k--) {
            dp[k][matrix[0].length - 1] = matrix[k][matrix[0].length - 1] + dp[k+1][matrix[0].length - 1];
        }
        //为dp表所有位置赋值 外层for控制行，内层for控制列
        for (int k = matrix.length - 2; k >= 0; k--) {
            for (int l = matrix[0].length - 2; l >= 0 ; l--) {
                dp[k][l] = matrix[k][l] + Math.min(dp[k+1][l],dp[k][l+1]);
            }
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        DpMinPath minPath = new DpMinPath();
        int[][] arr = {{1,2,3,0,2},{6,7,0,3,7},{2,0,6,5,8}};
        System.out.println(minPath.walkDp(arr,0,0));
    }
}
