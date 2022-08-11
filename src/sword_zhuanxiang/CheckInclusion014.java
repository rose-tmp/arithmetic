package sword_zhuanxiang;

/**
 * @author - ZwZ
 * @date - 2022/7/30-16:39 - 周六
 * @Description:剑指 Offer II 014. 字符串中的变位词
 * 给定两个字符串s1和s2，写一个函数来判断 s2 是否包含 s1的某个变位词。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/MPnaiL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CheckInclusion014 {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        boolean flag = false;
        for (int i = 0; i < n2; i++) {
            if (i + n1 - 1 < n2) {
                flag |= check(s1, s2.substring(i, i + n1));
            } else {
                break;
            }
        }
        return flag;
    }

    /**
     * 如果s2中出现的每个字符的个数都和s1中相同，则返回true;否则返回false
     */
    public boolean check(String s1, String s2) {
        int[] flag1 = new int[26];
        int[] flag2 = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            flag1[s1.charAt(i) - 'a']++;
            flag2[s2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (flag1[i] != flag2[i]) {
                return false;
            }
        }
        return true;
    }
}
