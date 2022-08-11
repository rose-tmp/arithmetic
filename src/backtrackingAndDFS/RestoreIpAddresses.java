package backtrackingAndDFS;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/3/16 - 18:53
 * @Description:93. 复原 IP 地址
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * <p>
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * 示例 4：
 * <p>
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * 示例 5：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-ip-addresses
 */
public class RestoreIpAddresses {
    List<String> fuckL = new ArrayList<>();
    /**
     * 20220722按照自己的思路写的，120/145
     * 总有一些0多的示例过不了
     * */
    public List<String> restoreIpAddresses3(String s) {
        dfs(s, new ArrayList<>(), 0);
        return fuckL;
    }

    public void dfs(String s, List<Integer> cur, int index) {
        if (cur.size() == 4 && index == s.length()) {
            String temp = cur.get(0) + "." + cur.get(1) + "." + cur.get(2) + "." + cur.get(3);
            fuckL.add(temp);
        }
        if (index >= s.length()) {
            return;
        }
        //该位置进行字符的选择
        for (int i = index + 1; i <= s.length(); i++) {
            String str = s.substring(index, i);
            if (str.length() > 3) {
                break;
            }
            if (Integer.parseInt(str) >= 0 && Integer.parseInt(str) <= 255) {
                if (str.charAt(0) == '0') {
                    if (str.length() == 1) {
                        cur.add(0);
                        dfs(s, cur, i);
                        cur.remove(cur.size() - 1);
                    } else {
                        break;
                    }
                }
                cur.add(Integer.parseInt(str));
                dfs(s, cur, i);
                cur.remove(cur.size() - 1);
            }
        }
    }


    List<String> res2 = new ArrayList<>();

    public List<String> restoreIpAddresses2(String s) {
        dfs2(new ArrayList<>(), s, 0);
        return res2;
    }
    public void dfs2(List<Integer> list, String s,int index) {
        if (list.size() == 4) {
            if (index == s.length()) {
                String temp = "";
                for (int i = 0; i < 4; i++) {
                    temp += (list.get(i) + ".");
                }
                temp = temp.substring(0, temp.length() - 1);
                res2.add(temp);
            }
            return;
        }
        if (index >= s.length()){
            return;
        }
        if (s.charAt(index) == '0') {
            list.add(0);
            dfs2(list, s, index + 1);
            list.remove(list.size() - 1);
        }
        int addr = 0;
        //当前位置的值为s[0...i]
        for (int i = index; i < s.length(); i++) {
            addr = addr * 10 + (s.charAt(i) - '0');
            if (addr <= 0 || addr > 0xFF) {
                break;
            }
            list.add(addr);
            dfs2(list, s, i + 1);
            list.remove(list.size() - 1);
        }
    }


    List<String> ans = new ArrayList<String>();
    int[] segments = new int[4];

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return ans;
    }
    /**
     * @param segId IP地址的第几个段
     * @param segStart 该段在s中的起始下标
     * */
    public void dfs(String s, int segId, int segStart) {
        if (segId == 4) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < 4; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != 4 - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }
        /**
         * 一般情况，枚举每一种可能性并递归
         * */
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
