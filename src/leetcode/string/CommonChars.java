package leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/14 - 13:28
 * @Description:1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 * 提示：
 *     1 <= A.length <= 100
 *     1 <= A[i].length <= 100
 *     A[i][j] 是小写字母
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 */
public class CommonChars {
    public List<String> commonChars(String[] A) {
        if (A == null || A.length == 0) {
            return null;
        }
        List<String> result = new ArrayList<>();
        int[] minNum = new int[26];
        Arrays.fill(minNum, Integer.MAX_VALUE);//为存储最小数量的数组赋初值
        int[] num = new int[26];
        for (int i = 0; i < A.length; i++) {
            //删除上个字符串留在num数组中的数据
            for (int index = 0; index < num.length; index++) {
                num[index] = 0;
            }
            //统计某个字符串中某字符
            for (int j = 0; j < A[i].length(); j++) {
                num[A[i].charAt(j) - 'a']++;
            }
            //更新最小数组
            for (int k = 0; k < 26; k++) {
                minNum[k] = Math.min(minNum[k], num[k]);
            }
        }
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minNum[i]; j++) {
                result.add(String.valueOf((char) ('a' + i)));
            }
        }
        return result;
    }
}
