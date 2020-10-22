package leetcode.greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/10/22 - 9:46
 * @Description:763. 划分字母区间
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
 * <p>
 * 示例 1：
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 */
public class GartitionLabels {
    /*
     * 贪心策略
     * 每一个字符都记录它所能到达的最远的位置
     * 直到遍历到此位置时，此位置及之前的位置上的字符构成一个片段
     * */
    public List<Integer> partitionLabels1(String S) {
        if (S == null)
            return null;
        if (S.length() < 1)
            return new ArrayList<>(S.length());
        List<Integer> list = new ArrayList<>();
        int maxIndex = 0;//记录最远距离
        int start = 0;//记录当前的maxIndex由前边的哪个字符产生
        for (int i = 0; i < S.length(); i++) {
            for (int j = 0; j < S.length(); j++) {
                //找到字符串中与当前下标包含同意字符
                if (S.charAt(j) == S.charAt(i)) {
                    maxIndex = Math.max(maxIndex, j);
                }
            }
            //此时 maxIndex前边的字符构成一个片段
            if (i == maxIndex) {
                list.add(maxIndex - start + 1);
                maxIndex = i;
                start = i + 1;
            }
        }
        return list;
    }

    /*
     * 对方法1进行优化：利用空间换时间
     * */
    public List<Integer> partitionLabels(String S) {
        if (S == null)
            return null;
        if (S.length() < 1)
            return new ArrayList<>(S.length());
        List<Integer> list = new ArrayList<>();
        int[] lastIndex = new int[26];
        //得到每个位置上的字符最后一次出现时的下标
        for (int i = 0; i < S.length(); i++) {
            lastIndex[S.charAt(i) - 'a'] = i;
        }
        int maxIndex = 0;//记录最远距离
        int start = 0;//记录当前的maxIndex由前边的哪个字符产生
        for (int i = 0; i < S.length(); i++) {
            int temp = lastIndex[S.charAt(i) - 'a'];
            if(maxIndex < temp)
                maxIndex = temp;
            //此时 maxIndex前边的字符构成一个片段
            if (i == maxIndex) {
                list.add(maxIndex - start + 1);
                maxIndex = i;
                start = i + 1;
            }
        }
        return list;
    }
}
