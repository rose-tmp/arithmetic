//美团真题
package writtenExam.meituan;
import java.util.*;
/**
 * @author - ZwZ
 * @date - 2022/8/5-15:00 - 周五
 * @Description:小美是美团的前端工程师，为了防止系统被恶意攻击，小美必须要在用户输入用户名之前做一个合法性检查，一个合法的用户名必须满足以下几个要求：
 *
 * 用户名的首字符必须是大写或者小写字母。
 * 用户名只能包含大小写字母，数字。
 * 用户名需要包含至少一个字母和一个数字。
 * 如果用户名合法，请输出 "Accept"，反之输出 "Wrong"。
 * 格式：
 *
 *
 * 输入：
 * - 输入第一行包含一个正整数 T，表示需要检验的用户名数量。
 * - 接下来有 T 行，每行一个字符串 s，表示输入的用户名。
 * 输出：
 * - 对于每一个输入的用户名 s，请输出一行，即按题目要求输出一个字符串。
 * 示例：
 *
 *
 * 输入：
 *      5
 *      Ooook
 *      Hhhh666
 *      ABCD
 *      Meituan
 *      6666
 * 输出：
 *      Wrong
 *      Accept
 *      Wrong
 *      Wrong
 *      Wrong
 * 提示：
 *
 * 1 <= T <= 100
 * s 的长度不超过 20
 * 请注意，本题需要自行编写「标准输入」和「标准输出」逻辑，以及自行 import/include 需要的 library。了解书写规则
 *
 *
 * 作者：美团
 * 链接：https://leetcode.cn/leetbook/read/meituan/ohsjgd/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = Integer.parseInt(sc.nextLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            list.add(sc.nextLine());
        }
        for (int i = 0; i < list.size(); i++) {
            if (isValid(list.get(i))) {
                System.out.println("Accept");
            } else {
                System.out.println("Wrong");
            }
        }
    }

    public static boolean isValid(String username) {
        //首字母不是大写字母
        if (!((username.charAt(0) >= 'A' && username.charAt(0) <= 'Z') || (username.charAt(0) >= 'a' && username.charAt(0) <= 'z'))) {
            return false;
        }
        boolean flag = false;

        for (int i = 1; i < username.length(); i++) {
            boolean isChar = false, isNum = false;
            //是字母
            if (username.charAt(i) >= 'A' && username.charAt(i) <= 'Z' ||
                    username.charAt(i) >= 'a' && username.charAt(i) <= 'z') {
                isChar = true;
            }
            //是数字
            if (username.charAt(i) >= '0' && username.charAt(i) <= '9') {
                isNum = true;
            }
            //既不是字母也不是数字
            if (!isChar && !isNum) {
                return false;
            }
            if (isNum) {
                flag = true;
            }
        }
        return flag;
    }
}
