package greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author - ZwZ
 * @date - 2020/11/30 - 9:02
 * @Description:767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * <p>
 * 示例1:
 * 输入: S = "aab"
 * 输出: "aba"
 * <p>
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * <p>
 * S 只包含小写字母并且长度在[1, 500]区间内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reorganize-string
 */
public class ReorganizeString {
    public String reorganizeString(String S) {
        int[] count = new int[26];
        int maxCount = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            count[S.charAt(i) - 'a']++;
            maxCount = Math.max(maxCount, count[S.charAt(i) - 'a']);
        }
        /*
        * 当n为偶数时，有n/2个偶数下标 n/2个奇数下标 此时当某个字符出现的次数大于n/2则一定会出现相邻
        * 当n为奇数时，有(n + 1)/2个偶数下标，所以每个字母出现的次数不能超过(n + 1)/2
        * */
        if (maxCount > (S.length() + 1) / 2) {
            return "";
        }
        //定义指定排序规则的优先队列
        PriorityQueue<Character> queue = new PriorityQueue(new Comparator() {
            @Override
            public int compare(Object ch1, Object ch2) {
                return count[(Character) ch2 - 'a'] - count[(Character) ch1 - 'a'];
            }
        });
        //将元素按照出现的次数加入优先队列中
        for (char i = 'a'; i <= 'z'; i++) {
            if (count[i - 'a'] > 0) {
                queue.offer((i));
            }
        }
        while (queue.size() > 1) {
            char ch1 = queue.poll();
            char ch2 = queue.poll();
            sb.append(ch1);
            sb.append(ch2);
            count[ch1 - 'a'] = count[ch1 - 'a'] > 0 ? count[ch1 - 'a'] - 1 : 0;
            count[ch2 - 'a'] = count[ch2 - 'a'] > 0 ? count[ch2 - 'a'] - 1 : 0;
            if (count[ch1 - 'a'] > 0) {
                queue.offer((ch1));
            }
            if (count[ch2 - 'a'] > 0) {
                queue.offer(ch2);
            }
        }
        /*
        * 因为在while循环中每次拿出来两个字符 所以当n为奇数时， 肯定在优先队列中会剩一个字符
        * 而这个字符可以拿出来直接加在返回结果的最后而不会出现最终的结果后两个字符重复的情况
        * 因为如果该字符在整个字符串中至少出现两次，那么最后一次从优先队列中选出两个字符时，这个字符肯定
        * 是被优先拿出来的，也就是ch1 会先被插在了某两个字符中间，而不会造成最终结果两个字符重复的情况
        * */
        if(queue.size() > 0){
            sb.append(queue.poll());
        }
        return sb.toString();
    }
}
