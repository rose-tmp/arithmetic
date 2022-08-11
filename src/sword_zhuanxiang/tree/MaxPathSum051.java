package sword_zhuanxiang.tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/8-23:07 - 周一
 * @Description:剑指 Offer II 051. 节点之和最大的路径
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。
 * 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围是 [1, 3 * 104]
 * -1000 <= Node.val <= 1000
 * <p>
 * 注意：本题与主站 124 题相同： https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 *
 * 这道题和字节跳动-互娱二面时的题很像，但是那个没有这个难
 */
public class MaxPathSum051 {
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxN(root);
        return res;
    }

    /**
     * 从左子树或者右子树来到root,所能取得的最大值
     */
    public int maxN(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(0, maxN(root.left));
        int rightMax = Math.max(0, maxN(root.right));
        int maxPath = Math.max(leftMax, rightMax) + root.val;
        //在此过程中更新res
        res = Math.max(res, leftMax + rightMax + root.val);
        return maxPath;
    }
}
