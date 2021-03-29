package leetcode.backtrackingAndDFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/10/13 - 18:41
 * @Description:17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 */
public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        backTrack(res,map,digits,0,new StringBuffer());
        return res;
    }
    //回溯
    public void backTrack(List<String> res,Map<Character,String> map,
                          String digits,int index,StringBuffer resSingle){
        if(digits.length() == index) {
            res.add(resSingle.toString());
        }else{
            char digit = digits.charAt(index);
            String letters = map.get(digit);
            int lettersCount = letters.length();
            for(int i = 0;i < lettersCount;i++){
                resSingle.append(letters.charAt(i));
                backTrack(res,map,digits,index + 1,resSingle);
                resSingle.deleteCharAt(index);
            }
        }
    }
}
