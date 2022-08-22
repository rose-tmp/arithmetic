package sword_zhuanxiang.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2022/8/7-16:52 - 周日
 * @Description:剑指 Offer II 037. 小行星碰撞
 * 给定一个整数数组 asteroids，表示在同一行的小行星。
 * 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向
 * （正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。
 * 找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
 * 如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 示例 1：
 *
 * 输入：asteroids = [5,10,-5]
 * 输出：[5,10]
 * 解释：10 和 -5 碰撞后只剩下 10 。 5 和 10 永远不会发生碰撞。
 * 示例 2：
 *
 * 输入：asteroids = [8,-8]
 * 输出：[]
 * 解释：8 和 -8 碰撞后，两者都发生爆炸。
 * 示例 3：
 *
 * 输入：asteroids = [10,2,-5]
 * 输出：[10]
 * 解释：2 和 -5 发生碰撞后剩下 -5 。10 和 -5 发生碰撞后剩下 10 。
 * 示例 4：
 *
 * 输入：asteroids = [-2,-1,1,2]
 * 输出：[-2,-1,1,2]
 * 解释：-2 和 -1 向左移动，而 1 和 2 向右移动。
 * 由于移动方向相同的行星不会发生碰撞，所以最终没有行星发生碰撞。
 *
 * 提示：
 *
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 * 注意：本题与主站 735 题相同： https://leetcode-cn.com/problems/asteroid-collision/
 */
public class AsteroidCollision037 {
    /**
     * asteroids[i] > 0 --> 入栈
     * asteroids[i] < 0 --> 出栈一个元素top:
     * 1. top > (-asteroids[i]): i++ ,即当前正数top把asteroids[i]吃掉
     * 2. top < (-asteroids[i]): 继续出栈，即当前负数把top吃掉:
     *   2.1 栈为空: 将asteroids[i]加入最终结果集中,即该负数左边已经没有正数可以吃
     * 3. 所有元素遍历一遍后，把栈中元素全部加入到结果集中 -> 即所有留下来的正数加入结果集（负数在遍历的过程中已经加入了）
     * */
    public int[] asteroidCollision(int[] asteroids) {
        if (asteroids.length == 0) {
            return new int[]{};
        }
        if (asteroids.length == 1) {
            return new int[]{asteroids[0]};
        }
        List<Integer> res = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int ptr = 0;
        while (ptr < asteroids.length) {
            int cur = asteroids[ptr];
            if (cur > 0) {
                stack.push(cur);
                ptr++;
            } else {
                //该负数左边一个正数也没有则直接加入结果集后继续向后遍历
                if (stack.isEmpty()) {
                    res.add(cur);
                    ptr++;
                    continue;
                }
                int top = stack.pop();
                while (top < (-cur) && !stack.isEmpty()) {
                    top = stack.pop();
                }
                if (top > (-cur)) {
                    stack.push(top);
                    ptr++;
                } else if (stack.isEmpty() && top < (-cur)) {
                    res.add(cur);
                    ptr++;
                } else if (top == (-cur)) {
                    ptr++;
                }

            }
        }
        res.addAll(stack);
        int[] ans = new int[res.size()];
        int index = 0;
        for (int i : res) {
            ans[index] = i;
            index++;
        }
        return ans;
    }
}
