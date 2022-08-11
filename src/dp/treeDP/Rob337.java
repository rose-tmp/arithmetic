package dp.treeDP;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/4/1 7:55 下午
 * @Description:337. 打家劫舍 III
 * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
 * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
 * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
 * <p>
 * <p>
 * 示例 1:
 * 输入: root = [3,2,3,null,3,null,1]
 * 输出: 7
 * 解释:小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7
 * <p>
 * 示例 2:
 * 输入: root = [3,4,5,1,3,null,1]
 * 输出: 9
 * 解释:小偷一晚能够盗取的最高金额 4 + 5 = 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Rob337 {
    public int rob(TreeNode root) {
        int[] res = fuck(root);
        return Math.max(res[0], res[1]);
    }

    /**
     * @return 二维数组 arr[0]:当前节点不偷，以该节点为根节点所构成的树所能获得到的最大收益; arr[1]:当前节点偷，...
     */
    public int[] fuck(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] res = new int[2];
        int[] left = fuck(root.left);
        int[] right = fuck(root.right);
        res[0] = Math.max(left[1], left[0]) + Math.max(right[1], right[0]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}
