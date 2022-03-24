package string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/9/29 - 18:59
 * @Description:
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行Z字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING"行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 * 示例1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出:"LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 */
public class Convert {
    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        List<StringBuilder> list = new ArrayList<>();
        for(int i = numRows;i > 0;i--){
            list.add(new StringBuilder());
        }
        boolean down = false;//向下排列标识符
        int len = s.length();//字符串长度
        int row = 0;//当前可以加入字符的行
        int index = 0;
        while(len > 0){
            list.get(row).append(s.charAt(index++));
            //反转标识符
            if(row == 0 || row == numRows - 1)
                down = !down;
            //通过三目运算符
            row += down ? 1 : -1;
            len--;
        }
        StringBuilder ret = new StringBuilder();
        for (StringBuilder sb : list) ret.append(sb);
        return ret.toString();
    }
}
