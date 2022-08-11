package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/7/19-19:38 - 周二
 * @Description:剑指 Offer II 051. 节点之和最大的路径
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 * <p>
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jC7MId
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxPathSum {
    public int maxPathSum(TreeNode root) {
        maxN(root);
        return res;
    }

    int res = Integer.MIN_VALUE;

    public int maxN(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMaxN = Math.max(maxN(root.left), 0);
        int rightMaxN = Math.max(maxN(root.right), 0);

        //当前节点的最大路径
        int maxPath = leftMaxN + rightMaxN + root.val;
        res = Math.max(res, maxPath);

        return Math.max(leftMaxN, rightMaxN) + root.val;
    }
}
