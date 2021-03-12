package leetcode.dp;

/**
 * @author - ZwZ
 * @date - 2021/3/6 - 11:23
 * @Description:
 */
public class Demo {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        int left = 0;
        int ans = 1;
        for (int right = 1; right < s.length(); right++) {
            for (int i = left; i < right; i++) {
                if (s.charAt(i) == s.charAt(right)) {
                    //剔除重复元素
                    left = i + 1;
                    break;
                }
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        Demo demo = new Demo();
        demo.lengthOfLongestSubstring(str);
    }
}
