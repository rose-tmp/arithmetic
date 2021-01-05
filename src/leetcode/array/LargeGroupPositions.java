package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/1/5 - 16:23
 * @Description:830. 较大分组的位置
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组
 * 例如，在字符串 s = "abbxxxxzyy"中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6]
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果
 * <p>
 * 示例1：
 * 输入：s = "abbxxxxzzy"
 * 输出：[[3,6]]
 * 解释："xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 * <p>
 * 示例 2：
 * 输入：s = "abc"
 * 输出：[]
 * 解释："a","b" 和 "c" 均不是符合要求的较大分组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/positions-of-large-groups
 */
public class LargeGroupPositions {
    /**
     * 双指针暴力解法
     * 时间复杂度O(N^2)
     * */
    public List<List<Integer>> largeGroupPositions1(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s == null || s.length() < 3) {
            return res;
        }
        StringBuilder sb = new StringBuilder(s);
        for (int start = 0; start < sb.length() - 2; ) {
            int len = 0;
            int end;
            for (end = start; end < sb.length(); end++) {
                if (sb.charAt(end) == sb.charAt(start)) {
                    len++;
                } else {
                    break;
                }
            }
            if (len >= 3) {
                List<Integer> list = new ArrayList<>();
                list.add(start);
                list.add(end - 1);
                res.add(list);
            }
            start = end;
        }
        return res;
    }
    /**
     * 一次遍历
     * 时间复杂度O(N)
     * */
    public List<List<Integer>> largeGroupPositions2(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s == null || s.length() < 3) {
            return res;
        }
        StringBuilder sb = new StringBuilder(s);
        int num = 1;
        for (int i = 0; i < sb.length(); i++) {
            if (i == sb.length() - 1 || sb.charAt(i) != sb.charAt(i + 1)) {
                if (num >= 3) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i - num + 1);
                    list.add(i);
                    res.add(list);
                }
                num = 1;
            } else {
                num++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LargeGroupPositions largeGroupPositions = new LargeGroupPositions();
        String s = "abcdddeeeeaabbbcd";
        largeGroupPositions.largeGroupPositions2(s);
    }
}
