package leetcode.backtrackingAndDFS;


import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 20
 * 20/10/13 - 21:46
 * @Description:22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 */
public class GenerateParenthesis {
    //方法1 : 递归  这个过程其实就是回溯，只是没有剪枝
    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList();
        char[] current = new char[2 * n];
        generateAll(current, 0, result);
        return result;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (current.length == pos) {
            if (valid(current)) {
                result.add(new String(current));
            }
        }
        /*
        某一个位置要么填'(' 要么填')'
        如果在此位置填了'(' 而最终的序列不满足要求的话，会退回到下面标注的第一行代码处继续向下执行
        而第二行代码，把这个位置上的字符从'('修改成了')'，然后继续执行第三行代码 即递归
        如此一个过程就可以把所有的序列都给遍历一遍去判断他们是否满足要求
        */
        else {
            current[pos] = '(';
            generateAll(current, pos + 1, result); //第一行
            current[pos] = ')'; //第二行
            generateAll(current, pos + 1, result);
        }
    }

    //判断某序列是否满足条件
    public boolean valid(char[] current) {
        int balance = 0;
        for (int i = 0; i < current.length; i++) {
            if (current[i] == '(') {
                balance++;
            } else {
                balance--;
            }
            /*
            正常情况从左到右遍历都是某个右括号对应的左括号先出现，然后右括号再出现将其抵消
            当出现balance < 0的情况时，则证明某一对括号的右括号先出现
            所以这种情况下对应的不是我们最终想要的答案
            */
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    /*
    把字符串数组修改成StringBuffer
    public List<String> generateParenthesis12(int n) {
        List<String> result = new ArrayList();
        StringBuffer sb = new StringBuffer();
        generateAll(sb, n, result);
        return result;
    }

    public void generateAll(StringBuffer sb, int n, List<String> result) {
        if (sb.length() == n) {
            if (valid(sb)) {
                result.add(sb.toString());
            }
        }
        else {
            sb.append('(');
            generateAll(sb, n, result);
            sb.deleteCharAt(sb.length() - 1);

            sb.append(')');
            generateAll(sb, n, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    public boolean valid(StringBuffer sb) {
        int balance = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }*/

    /*
    * 方法2 剪枝后的回溯
    * 方法1中把所有的情况都穷举了出来然后判断哪一种情况满足要求
    * 而方法2中我们只对还有可能合法的序列进行接下来的拼接
    * */
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        generateAll2(sb, 0, 0, n, result);
        return result;
    }

    public void generateAll2(StringBuffer sb, int open, int close, int n, List<String> result) {
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        if (open < n) {
            sb.append('(');
            generateAll2(sb, open + 1, close, n, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            generateAll2(sb, open, close + 1, n, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
