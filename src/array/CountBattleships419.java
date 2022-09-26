package array;

/**
 * @author: ZwZ
 * @date: 2022-09-21 22:03
 * @desc:
 */
public class CountBattleships419 {
    public int countBattleships(char[][] board) {
        int res = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'X') {
                    board[i][j] = '.';
                    res++;
                    int index = i + 1;
                    while (index < board.length && board[index][j] == 'X') {
                        board[index][j] = '.';
                        ++index;
                    }
                    index = j + 1;
                    while (index < board[0].length && board[i][index] == 'X') {
                        board[i][index] = '.';
                        ++index;
                    }
                }
            }
        }
        return res;
    }
}
