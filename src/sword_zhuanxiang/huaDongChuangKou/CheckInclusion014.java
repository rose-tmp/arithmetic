package sword_zhuanxiang.huaDongChuangKou;

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
    /**
     * 思路：创建两个数组分别表示s1,s2中26个字母对应出现的次数
     * 然后将一个长度为s1长度的滑动窗口一直从s2的头滑到尾
     * 在滑动的过程中去更新上述两个数组中字母的出现次数
     * <p>
     * 如果出现两个数组中每个位置上的数字都相同的情况，则说明找到了一个s2中的子字符串是满足要求的 就返回true
     * <p>
     * 时间复杂度:o((m+n)*26)
     */
    public boolean checkInclusion1(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] arr1 = new int[26];//arr1[i]表示字符(i + 'a')出现的次数
        int[] arr2 = new int[26];//arr2[i]表示字符(i + 'a')出现的次数

        for (int i = 0; i < s1.length(); i++) {
            arr1[s1.charAt(i) - 'a']++;
            arr2[s2.charAt(i) - 'a']++;
        }
        /*先看一下s2中前s1.length个字符是否满足要求
         * 如果满足要求就说明已经找到了就不再需要向下找了
         * */
        if (isEquals(arr1, arr2)) {
            return true;
        }
        //长度为s1.length的滑动窗口开始在s2上向后滑动,每次滑动一个位置 i为滑动窗口右边界
        for (int i = s1.length(); i < s2.length(); i++) {
            /*
             * 这里的滑动窗口不太直观，是通过arr2来表示的，有点抽象
             * arr2中存储了长度为s1.length的滑动窗口的
             * */
            //删除滑动窗口中现在记录的第一个位置的元素(即将这个元素位置出现的次数减1)
            arr2[s2.charAt(i - s1.length()) - 'a']--;
            //将滑动窗口向右滑动一个位置，(即将这个元素出现的次数加1)
            arr2[s2.charAt(i) - 'a']++;

            //判断对于新的字符串是否是s1的变位词
            if (isEquals(arr1, arr2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkInclusion1中每次滑动窗口更新之后都会对长度为26的两个数组进行equals比较
     * 但是滑动窗口向右滑动一个位置之后arr2....详见力扣题解的优化(没看懂f)
     */
    public boolean checkInclusion2(String s1, String s2) {

        return false;
    }

    /**
     * 判断两个数组中每个位置对应位置上的数字大小是否相同
     * (这两个数组的长度都是26,每个位置上代表一个字符出现的次数)
     */
    private boolean isEquals(int[] arr1, int[] arr2) {
        for (int i = 0; i < 26; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /*
     * 长度为n1的滑动窗口
     * 每次从s2中截取长度为n1的字符串，然后拿该子串和s1作比较看是否含有相同数量的字符
     *
     * 时间复杂度:o((m+n)*26)
     * */
    public boolean checkInclusion3(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        boolean flag = false;
        for (int i = 0; i < n2; i++) {
            if (i + n1 - 1 < n2) {
                //将截取的字串和s1作比较
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
    private boolean check(String s1, String s2) {
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
