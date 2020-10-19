package leetcode.easy;

/**
 * @author - ZwZ
 * @date - 2020/10/19 - 9:17
 * @Description:844. 比较含退格的字符串
 * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
 * 注意：如果对空文本输入退格字符，文本继续为空。
 * 示例 1：
 * 输入：S = "ab#c", T = "ad#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “ac”。
 * <p>
 * 示例 2：
 * 输入：S = "ab##", T = "c#d#"
 * 输出：true
 * 解释：S 和 T 都会变成 “”。
 * <p>
 * 示例 3：
 * 输入：S = "a##c", T = "#a#c"
 * 输出：true
 * 解释：S 和 T 都会变成 “c”。
 * <p>
 * 示例 4：
 * 输入：S = "a#c", T = "b"
 * 输出：false
 * 解释：S 会变成 “c”，但 T 仍然是 “b”。
 * <p>
 * 提示：
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S 和 T 只含有小写字母以及字符 '#'。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/backspace-string-compare
 */
public class BackspaceCompare {
    /**
     * 将两个字符串还原，然后比较
     */
    public boolean backspaceCompare1(String S, String T) {
        StringBuilder sbS = new StringBuilder();//存储还原后的字符串
        StringBuilder sbT = new StringBuilder();
        //遍历给定字符串
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) != '#') {
                sbS.append(S.charAt(i));
            } else {
                //遇到#则开始删除
                if (sbS.length() > 0) {
                    sbS.deleteCharAt(sbS.length() - 1);
                }
            }
        }
        for (int i = 0; i < T.length(); i++) {
            if (T.charAt(i) != '#') {
                sbT.append(T.charAt(i));
            } else {
                if (sbT.length() > 0) {
                    sbT.deleteCharAt(sbT.length() - 1);
                }
            }
        }
        int lenS = sbS.length();
        int lenT = sbT.length();
        if (lenS != lenT) {
            return false;
        } else {
            for (int i = 0; i < lenS; i++) {
                if (sbS.charAt(i) != sbT.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * 一个元素会不会留下来取决于其后面的#的个数
     * 使用指针从后向前遍历数组且再遍历的同时记录#的个数即可
     */
    public boolean backspaceCompare(String S, String T) {
        //指针
        int pointS = S.length() - 1;
        int pointT = T.length() - 1;
        //记录#的数量
        int countS = 0;
        int countT = 0;
        while (pointS >= 0 || pointT >= 0) {
            while (pointS >= 0) {
                if (S.charAt(pointS) == '#') {
                    countS++;
                    pointS--;
                } else if (countS > 0) {
                    pointS--;
                    countS--;
                } else {
                    break;
                }
            }
            while (pointT >= 0) {
                if (T.charAt(pointT) == '#') {
                    countT++;
                    pointT--;
                } else if (countT > 0) {
                    pointT--;
                    countT--;
                } else {
                    break;
                }
            }
            if (pointS >= 0 && pointT >= 0) {
                if (S.charAt(pointS) != T.charAt(pointT)) {
                    return false;
                }
            } else {
                //没有全为负
                if (pointS >= 0 || pointT >= 0) {
                    return false;
                }
            }
            pointT--;
            pointS--;
        }
        return true;
    }
}
