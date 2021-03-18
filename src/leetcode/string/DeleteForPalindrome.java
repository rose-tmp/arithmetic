package leetcode.string;

/**
 * @author - ZwZ
 * @date - 2021/3/18 - 18:37
 * @Description:判断字符串中删除一个字符后可否构成一个回文串
 * 快手效率工程部门后端实习生一面第二题(二面又让写一遍这道题,TMD)
 */
public class DeleteForPalindrome {
    /*
     * str.charAt(pre) != str.charAt(rear)时，删除pre---rear之间的任何一个字符都不可能构成回文串
     * 当str.charAt(pre) == str.charAt(rear)时，有可能构成，所以pre-- rear++
     * */
    public boolean isPlindrome(String str) {
        if (str == null || str.length() <= 1) {
            return false;
        }
        int pre = 0;
        int rear = str.length() - 1;
        return judge(str, pre, rear);
    }

    public boolean judge(String str, int pre, int rear) {
        while (pre <= rear) {
            if (str.charAt(pre) == str.charAt(rear)) {
                pre++;
                rear--;
            } else {
                return judge(str, pre++, rear) || judge(str, pre, rear--);
            }
        }
        return true;
    }
}
