package backtrackingAndDFS;

/**
 * @author - ZwZ
 * @date - 2020/10/30 - 10:33
 * @Description:79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻
 * 或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * <p>
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 */
public class Exist {
    public boolean exist(char[][] board, String word) {
        if (board == null || word.length() == 0 || (board.length + board[0].length) < word.length())
            return false;
        boolean[][] sign = new boolean[board.length][board[0].length];
        //为标记数组sign赋初值 Java默认初始化boolean类型数组的每一个位置为false
        /*for (int i = 0; i < sign.length; i++) {
            for (int j = 0; j < sign[0].length; j++) {
                sign[i][j] = false;
            }
        }*/
        //双重for遍历二维数组为了找到第一个字母在二维数组出现的位置
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (backTrack(board, sign, word, 0, row, col)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 回溯函数  用于判断[row,col]位置的四个方向是否含有word.charAt(index + 1)这个字符
     * sign:用于标记每个位置是否被访问过  如果之前匹配上了二维数组中的某字符，后面就不可再拿他来匹配
     * index:记录当前所遍历到的word的下标
     */
    public boolean backTrack(char[][] board, boolean[][] sign, String word,
                             int index, int row, int col) {
        boolean res = false;
        if (index == word.length())
            return true;
        //越界 直接返回false
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
            return false;
        //此位置可以匹配
        if (board[row][col] == word.charAt(index) && sign[row][col] == false) {
            sign[row][col] = true;
            res = backTrack(board, sign, word, index + 1, row, col + 1) ||
                    backTrack(board, sign, word, index + 1, row, col - 1) ||
                    backTrack(board, sign, word, index + 1, row - 1, col) ||
                    backTrack(board, sign, word, index + 1, row + 1, col);
        }
        sign[row][col] = false;//将本次进入backTrack时所改变的sign复原
        return res;
    }

    public static void main(String[] args) {
        Exist exist = new Exist();
        char[][] board = {{'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}, {'a', 'a', 'a', 'a'}};
        exist.exist(board, "aaaaaaaaaaaaa");
    }
}
