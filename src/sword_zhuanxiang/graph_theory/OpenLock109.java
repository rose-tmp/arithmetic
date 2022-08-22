package sword_zhuanxiang.graph_theory;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-08-17 10:35
 * @desc:剑指 Offer II 109. 开密码锁
 * 一个密码锁由 4 个环形拨轮组成，每个拨轮都有 10 个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，请给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * <p>
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，因为当拨动到 "0102" 时这个锁就会被锁定。
 * <p>
 * 示例 2:
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * <p>
 * 示例 3:
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * <p>
 * 示例 4:
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 * <p>
 * 提示：
 * <p>
 * 1 <= deadends.length <= 500
 * deadends[i].length == 4
 * target.length == 4
 * target 不在 deadends 之中
 * target 和 deadends[i] 仅由若干位数字组成
 * <p>
 * <p>
 * 注意：本题与主站 752 题相同： https://leetcode-cn.com/problems/open-the-lock/
 */
public class OpenLock109 {
    /**
     * bfs
     */
    public int openLock(String[] deadends, String target) {
        if (target.equals("0000")) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s : deadends) {
            set.add(s);
        }
        if (set.contains("0000")) {
            return -1;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add("0000");

        //避免重复搜索
        Set<String> set2 = new HashSet<>();
        set2.add("0000");

        int ans = 0;//旋转的次数
        while (!queue.isEmpty()) {
            /* 入队列的元素都是不包含在deadends中且不是target的
             * 队列中每组元素的出栈，伴随着一次旋转，所以ans++
             * 这里一定要记录size然后出队 因为这里的size个元素都来自于某个串的一次旋转
             * */
            ans++;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                //cur旋转一次可以得到的所有串
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 4; i++) {
                    list.add(getPrev(cur, i));
                    list.add(getNext(cur, i));
                }
                for (String s : list) {
                    //旋转后的该元素不是目标值且不包含在死亡组合中
                    if (!s.equals(target) && !set.contains(s) && !set2.contains(s)) {
                        queue.add(s);
                        set2.add(s);
                    } else if (s.equals(target)) {
                        return ans;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * @return 把字符串s中i位置元素向前转一下得到的新字符串
     */
    private String getPrev(String s, int i) {
        //涉及到对每一位字符的操作,转换成字符数组比起直接做字符串的拼接会变得简单优雅很多
        char[] arr = s.toCharArray();
        if (arr[i] == '0') {
            arr[i] = '9';
        } else {
            arr[i] = (char) (arr[i] - 1);
        }
        return new String(arr);
    }

    /**
     * @return 把字符串s中i位置元素向后转一下得到的新字符串
     */
    private String getNext(String s, int i) {
        char[] arr = s.toCharArray();
        if (arr[i] == '9') {
            arr[i] = '0';
        } else {
            arr[i] = (char) (arr[i] + 1);
        }
        return new String(arr);
    }
}
