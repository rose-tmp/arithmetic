package leetcode.backtrackingAndDFS;

import java.util.HashSet;
import java.util.Set;

/**
 * @author - ZwZ
 * @date - 2020/10/17 - 9:42
 * @Description:52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 */
public class TotalNQueens {
    /**
     * 如果是在n*n位置上抽出n个位置，然后一个一个的去试是否满足条件的话，计算量已经超过亿级
     * 所以这种原始的暴力解法是不现实的
     * 这里使用回溯法 总体时间复杂度是O(N!)
     * */
    public int totalNQueens(int n) {
        if(n <= 0) return 0;
        if(n == 1) return 1;
        Set<Integer> cols = new HashSet();//存储哪一个列上存在元素
        Set<Integer> diagonal1 = new HashSet();//通过行与列的差值，记录捺对角线上是否存在元素
        Set<Integer> diagonal2 = new HashSet();//通过行和列的和，记录撇对角线上是否存在元素
        return backTrack(cols,diagonal1,diagonal2,0,n);
    }
    /**
     * row代表当前处在哪一行
     */
    public int backTrack(Set<Integer> cols,Set<Integer> diagonal1,Set<Integer> diagonal2,int row,int n){
        //这里是row == n而不是n - 1当其是n - 1时，最后一行还没有判断是否满足条件，直接就返回了，这是不对的
        if(row == n){
            return 1;
        }else{
            int count = 0;
            //i控制列
            for(int i = 0;i < n;i++){
                if(cols.contains(i) || diagonal1.contains(row - i) || diagonal2.contains(row + i)) continue;
                cols.add(i);
                diagonal1.add(row - i);
                diagonal2.add(row + i);
                count += backTrack(cols,diagonal1,diagonal2,row + 1,n);
                cols.remove(i);
                diagonal1.remove(row - i);
                diagonal2.remove(row + i);
            }
            return count;
        }
    }
}
