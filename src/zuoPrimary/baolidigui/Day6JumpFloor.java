package zuoPrimary.baolidigui;

/**
 * @author - ZwZ
 * @date - 2020/1/14 - 23:13
 * @Description:跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级
 * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 */
public class Day6JumpFloor {
    /*链接：https://www.nowcoder.com/questionTerminal/8c82a5b80378478f9484d87d1c5f12a4?answerType=1&f=discussion
    来源：牛客网
    我们把n级台阶时的跳法看成n的函数，记为f(n) 本质上还是斐波那契数列
    当n>=2时，第一次跳的时候有两种不同的选择：
        1.第一次跳1级，此时跳法的数目等于后面剩下的n-1级台阶的跳法数目，即为f(n-1);
        2.第一次跳2级，此时跳法数目等于后面剩下的n-2级台阶的跳法数目，即为f(n-2)。
    因此，n级台阶的不同跳法总数为f(n) = f(n-1) + f(n-2)*/
    public int JumpFloor(int target) {
        if(target <= 2)
            return target;
        return JumpFloor(target-1) + JumpFloor(target-2);
    }
}
