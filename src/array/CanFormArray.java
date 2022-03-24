package array;

/**
 * @author - ZwZ
 * @date - 2020/11/1 - 10:33
 * @Description:5554. 能否连接形成数组(第213场leetcode周赛第一题)
 * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也互不相同。
 * 请你以任意顺序连接 pieces 中的数组以形成 arr 。但是，不允许对每个数组 pieces[i] 中的整数重新排序。
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：arr = [85], pieces = [[85]]
 * 输出：true
 *
 * 示例 2：
 * 输入：arr = [15,88], pieces = [[88],[15]]
 * 输出：true
 * 解释：依次连接 [15] 和 [88]
 *
 * 示例 3：
 * 输入：arr = [49,18,16], pieces = [[16,18,49]]
 * 输出：false
 * 解释：即便数字相符，也不能重新排列 pieces[0]
 *
 * 示例 4：
 * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * 输出：true
 * 解释：依次连接 [91]、[4,64] 和 [78]
 *
 * 示例 5：
 * 输入：arr = [1,3,5,7], pieces = [[2,4,6,8]]
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-array-formation-through-concatenation
 */
public class CanFormArray {
    public boolean canFormArray(int[] arr, int[][] pieces) {
        //遍历arr
        for (int i = 0; i < arr.length;) {
            boolean flag = false;
            for (int[] piece : pieces) {
                if(arr[i] == piece[0]){
                    for (int j = 0; j < piece.length; j++) {
                        /*二维数组中的 元素都不相同
                        * 通过arr[i] == piece[0]进入此for之后
                        * 如果存在不相等则一定不可在匹配成功
                        * */
                        if(arr[i] != piece[j]){
                            return false;
                        }
                        i++;
                    }
                    //这一串字符匹配成功，去匹配arr中下一个字符
                    flag = true;
                    break;
                }
            }
            //此字符匹配了二维数组中所有的字符，都没有匹配成功
            if(!flag){
                return false;
            }
        }
        return true;
    }
}
