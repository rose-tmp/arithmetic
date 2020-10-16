package leetcode.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/15 - 19:43
 * @Description:剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 */
public class Permutation {
    List<String> res = new LinkedList<>();
    char[] c;
    /**
    这道题并不是经典的回溯算法，其实应该归属于递归类型的题目范围内
    因为这道题中，之前字母的选择会影响之后的字母的选择，第一个位置选了a,
    那么第二个位置就不能再选，以此类推。所以这个问题并没有39. 组合总和  22. 括号生成
    等其他的经典回溯一样更加的有套路化，写出来的答案也有点不像回溯算法的模板
    但是本质是一样的，在回溯中也是在完成某一个位置上元素的选择之后，
    然后递归进行下一个位置的元素的选择。而这里的是，在完成了某一个位置上的元素选择之后，
    递归进行剩下全部位置的元素选择，所以更像是给纯递归类型的问题
    */
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c)); // 添加排列方案
            return;
        }
        /*
        * set集合放在了dfs函数内部
        * 这样可以避免在同一层(递归树的同一层，也就是形成的字符串的同一个位置)出现相同的元素
        * 例如"aab"就会出现这种情况，导致最终结果会出现两次baa,
        * set集合放在dfs内部时，因为dfs()内部的for控制了递归树的每一层，
        * 所以，对于同一层的多次调用dfs()而言，使用的是同一个set对象，并不是表面上看的
        * 用的是每次进入dfs都new出来那个新的
        *
        * 这个就是此处使用set剪枝的代码妙处
        * */
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i]))
                continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x); // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1); // 递归下一个元素之后的位置
            swap(i, x); // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }

    public static void main(String[] args) {
        Permutation permutation = new Permutation();
        permutation.permutation("aab");
    }
}
