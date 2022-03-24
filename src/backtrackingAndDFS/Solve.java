package backtrackingAndDFS;

/**
 * @author - ZwZ
 * @date - 2021/3/29 - 20:08
 * @Description:130. 被围绕的区域
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 *
 * 示例 1：
 * 输入：board = [["X","X","X","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
 * 输出：[["X","X","X","X"],["X","X","X","X"],["X","X","X","X"],["X","O","X","X"]]
 * 解释：被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * 示例 2：
 * 输入：board = [["X"]]
 * 输出：[["X"]]
 *
 * 注意：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 为 'X' 或 'O'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/surrounded-regions
 */
public class Solve {
    /**
     * 找谁跟周围一圈上的O能连起来，能连起来的O都不变
     * 其他的O变成X
     * @param board
     */
    public void solve(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i = 0;i < board.length;i++){
            if(board[i][0] == 'O'){
                dfs(board,visited,i,0);
            }
        }
        for(int i = 0;i < board.length;i++){
            if(board[i][board[0].length - 1] == 'O'){
                dfs(board,visited,i,board[0].length - 1);
            }
        }
        for(int j = 0;j < board[0].length;j++){
            if(board[0][j] == 'O'){
                dfs(board,visited,0,j);
            }
        }
        for(int j = 0;j < board[0].length;j++){
            if(board[board.length - 1][j] == 'O'){
                dfs(board,visited,board.length - 1,j);
            }
        }
        for(int i = 0;i < visited.length;i++){
            for(int j = 0;j < visited[0].length;j++){
                if(!visited[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
    /*用於控制坐標的上下左右跑 也可以不使用這種方式 而是直接調用四次dfs()
    dfs(board,visited,i,j + 1);
    dfs(board,visited,i+1,j);
    dfs(board,visited,i+1,j+1);*/
    int[] dir_X = new int[]{0,-1,0,1};
    int[] dir_Y = new int[]{-1,0,1,0};

    /**
     * 標記從board[i][j]開始所能到達並且元素值為O的位置
     * 然後將這些位置所對應的visited標記數組上的位置標記為true
     * 說明通過board一圈上某一处的O可以到达的O
     * 这些O相当于和边界上的O连通，他们是不可以修改成X的
     * 其他的O全部修改成X
     * @param board
     * @param visited
     * @param i
     * @param j
     */
    public void dfs(char[][] board,boolean[][] visited,int i,int j){
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] == 'X'){
            return;
        }
        //剪枝 說明通過邊界上其他的O已經來到過這個位置上過 接下來的子問題就不用再去計算一遍  否則就造成了重複計算
        if(visited[i][j]){
            return;
        }
        visited[i][j] = true;
        for(int k = 0;k < 4;k++){
            int X = i + dir_X[k];
            int Y = j + dir_Y[k];
            dfs(board,visited,X,Y);
        }
    }
}
