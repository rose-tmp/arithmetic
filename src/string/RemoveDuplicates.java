package string;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2021/3/16 - 18:59
 * @Description:1047. 删除字符串中的所有相邻重复项
 * <p>
 * 给出由小写字母组成的字符串S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicates {
    public String removeDuplicates(String S) {
        if (S == null || S.length() == 0) return null;
        Stack<Character> stack = new Stack();
        for (int i = 0; i < S.length(); i++) {
            if (stack.isEmpty() || stack.peek() != S.charAt(i)) {
                stack.push(S.charAt(i));
            } else {
                stack.pop();
            }
        }
        StringBuilder res = new StringBuilder();
        for (char c : stack) {
            res.append(c);
        }
        return res.toString();
    }
}
