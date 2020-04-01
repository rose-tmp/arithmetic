package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/3/29 - 11:54
 * @Description:矩阵中每一个数都是正数代表走这个点需要花费的代价，要求从左上角走到右下角，
 * 每一步只能向右或者向下，沿途经过的数字要累加起来代表总代价
 * 返回最小的总代价
 *
 * 这个是一个枚举的过程，即枚举出来了所有的情况，然后在这些情况中找到自己想要的答案
 */
public class MinPath {
    /** 
    * @Author: ZwZ
    * @Description： 
    * @Param: [matrix, i, j] 
    * @return: int 返回从(i,j)出发到矩阵的右下角所需要的最小的总代价
    * @Date: 2020/3/29-11:56
    */
    public int walk(int[][] matrix,int i,int j){
        //递归终止条件
        if(i == matrix.length - 1 && j == matrix[0].length - 1)
            return matrix[i][j];
        //走到了最后一行
        if(i == matrix.length - 1)
            return matrix[i][j] + walk(matrix,i,j+1);
        //走到了最后一列
        if(j == matrix[0].length - 1)
            return matrix[i][j] + walk(matrix,i+1,j);
        int down = walk(matrix,i + 1,j);//向下走所需要的最小代价
        int right = walk(matrix,i,j+1);//向右走所需要的最短路径
        return matrix[i][j] + Math.min(right,down);
    }
}
