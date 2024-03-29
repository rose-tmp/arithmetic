package string;

/**
 * @author - ZwZ
 * @date - 2020/10/21 - 8:39
 * @Description:925. 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * <p>
 * 示例 1：
 * 输入：name = "alex", typed = "aaleex"
 * 输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * <p>
 * 示例 2：
 * 输入：name = "saeed", typed = "ssaaedd"
 * 输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 * <p>
 * 示例 3：
 * 输入：name = "leelee", typed = "lleeelee"
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：name = "laiden", typed = "laiden"
 * 输出：true
 * 解释：长按名字中的字符并不是必要的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/long-pressed-name
 */
public class IsLongPressedName {
    public boolean isLongPressedName(String name, String typed) {
        if (name == null || typed == null) {
            return false;
        }
        int i = 0;//作为name的指针
        int j = 0;//作为typed的指针
        /*
        输入的字符串也就是typed有可能没有全部涵盖name里边的字符
        所以用j当作循环条件
        如："pyplrz"     "ppyypllr"
        */
        while (j < typed.length()) {
            if (name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (name.charAt(i) != typed.charAt(j) && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            }else{
                return false;
            }
        }
        return i == name.length();
    }
}
