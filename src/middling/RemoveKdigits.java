package middling;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/11/15 - 15:23
 * @Description:402. 移掉K位数字
 * 给定一个以字符串表示的非负整数num，移除这个数中的任意 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-k-digits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveKdigits {
    /**
     * 贪心策略：
     * 每次都删除一个位置，都得到数值最小的那个
     * 那么最终结果当删除k个元素的时候得到的便是最小的那个
     */
    public String removeKdigits(String num, int k) {
        //删除k次  每次删除一个字符
        for (int i = 0; i < k; i++) {
            num = deleteOne(num);
        }
        return num;
    }

    /**
     * 给定一个字符串，删除一个位置使其剩余位置上拼成的数值最小
     * 在一个数中删除一个位置使得剩下的数在不改变顺序的情况下最小的话
     * 肯定是从左到右寻找并删除第一个出现arr[i] > arr[i + 1]这种情况时的i位置
     * 因为越是左边的数，占的权重越大 当首次出现arr[i] > arr[i + 1]的时候，删除i位置上的元素肯定
     * 在删除一个位置的情况下 得到的数值最小
     */
    public String deleteOne(String num) {
        if (num == null || num.length() == 0) {
            return num;
        }
        StringBuilder str = new StringBuilder(num);
        //当字符串的长度为1时，此时删除一个元素之后他的大小为0
        if (num.length() == 1) {
            str.setCharAt(0, '0');
            return str.toString();
        }
        int index = -1;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) > str.charAt(i + 1)) {
                index = i;
                break;
            }
        }
        //没有出现arr[i] > arr[i + 1]的情况  即所有元素从左到右是从小到大排列的 此时直接删除最后一个元素
        if (index == -1) {
            str.deleteCharAt(str.length() - 1);
        } else {
            str.deleteCharAt(index);
            //最高位为0
            while (str.length() != 0 && str.charAt(0) == '0') {
                str.deleteCharAt(0);
            }
            if (str.length() == 0) {
                str.append('0');
            }
        }
        return str.toString();
    }

    /**
     * 方法1中要删除k次 每次都要遍历一遍num
     * 在该方法中借助栈  从而达到一次遍历的效果
     */
    public String removeKdigits2(String num, int k) {
        if (num == null || num.length() == 0) {
            return null;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder(num);
        for (int i = 0; i < num.length(); i++) {
            /*
             * 在栈不为空且删除的元素的个数还没有到规定数目时
             * 如果栈顶元素大于当前遍历到的元素
             * 那么就要出栈 直到stack.peek() <= str.charAt(i)
             * */
            while (!stack.isEmpty() && k > 0 && stack.peek() > str.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(str.charAt(i));
        }
        /*遍历完之后如果k > 0则说明逆序的数量不够多
        此时栈中的元素从栈底到栈顶是从小到大的顺序 所以此时直接删除栈顶元素*/
        for (int i = 0; i < k; i++) {
            stack.pop();
        }
        //清空字符串
        str.delete(0, str.length());
        //借助另一个栈将stack中的元素颠倒顺序  当然一开始如果使用双端队列deque就不需要这一步
        Stack<Character> temp = new Stack<>();
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        //将元素存入最终的返回结果中
        while (!temp.isEmpty()) {
            str.append(temp.pop());
        }
        //最高位为0
        while (str.length() != 0 && str.charAt(0) == '0') {
            str.deleteCharAt(0);
        }
        if (str.length() == 0) {
            str.append('0');
        }
        return str.toString();
    }

    public static void main(String[] args) {
        RemoveKdigits kdigits = new RemoveKdigits();
        kdigits.removeKdigits2("10200", 1);
    }
}
