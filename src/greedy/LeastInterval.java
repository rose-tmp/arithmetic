package greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/12/5 - 18:00
 * @Description:621. 任务调度器
 * 给你一个用字符数组tasks表示的CPU需要执行的任务列表。其中每个字母表示一种不同种类的任务。
 * 任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。
 * 然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的 最短时间 。
 * <p>
 * 示例 1：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * <p>
 * 示例 2：
 * 输入：tasks = ["A","A","A","B","B","B"], n = 0
 * 输出：6
 * 解释：在这种情况下，任何大小为 6 的排列都可以满足要求，因为 n = 0
 * ["A","A","A","B","B","B"]
 * ["A","B","A","B","A","B"]
 * ["B","B","B","A","A","A"]
 * ...
 * 诸如此类
 * <p>
 * 示例 3：
 * 输入：tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
 * 输出：16
 * 解释：一种可能的解决方案是：
 * A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> (待命) -> (待命) -> A -> (待命) -> (待命) -> A
 * <p>
 * 提示：
 * 1 <= task.length <= 104
 * tasks[i] 是大写英文字母
 * n 的取值范围为 [0, 100]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/task-scheduler
 */
public class LeastInterval {
    /**
     * 贪心策略：
     * 每次执行目前剩余数目最多的 并且 未在冷却期的任务
     */
    public int leastInterval(char[] tasks, int n) {
        if (n <= 0) {
            return tasks.length;
        }
        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        //记录每个任务对应数量 key:任务 value:未执行的该任务的数量
        Map<Character, Integer> taskMap = new HashMap<>();
        //记录每个任务的冷却期  即到第几次执行任务时，才可以执行某个任务
        Map<Character, Integer> coolMap = new HashMap<>();
        //初始化taskMap和coolMap
        for (char ch : tasks) {
            taskMap.put(ch, taskMap.getOrDefault(ch, 0) + 1);
            if (!coolMap.containsKey(ch)) {
                //起初每个任务都可以在第一次执行任务的时候被执行
                coolMap.put(ch, 1);
            }
        }
        //初始化coolMap
        //当前所要执行的是第index次任务
        int index = 1;
        //有多少任务已经被执行
        int count = 0;
        while (count < tasks.length) {
            int maxNum = 0;
            char task = ' ';
            //寻找需要执行的task
            for (Map.Entry<Character, Integer> entry : taskMap.entrySet()) {
                Character temp = entry.getKey();
                //选择剩余数目最多 并且 未在冷却期的任务
                if (maxNum < entry.getValue() && index >= coolMap.get(temp)) {
                    task = temp;
                    maxNum = entry.getValue();
                }
            }
            //有任务满足条件 不需要原地待命
            if (task != ' ') {
                coolMap.put(task, index + n + 1);
                taskMap.put(task, taskMap.get(task) - 1);
                count++;
            }
            index++;
        }
        return index - 1;
    }

    public static void main(String[] args) {
        LeastInterval interval = new LeastInterval();
        char[] arr = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        interval.leastInterval(arr, 2);
    }
}
