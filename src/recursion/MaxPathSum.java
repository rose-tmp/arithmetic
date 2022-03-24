//递归
package recursion;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/3/3 10:53 下午
 * @Description:124. 二叉树中的最大路径和
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * <p>
 * 路径和 是路径中各节点值的总和。
 * <p>
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 * <p>
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 思路：
 * 这道题最重要的其实是理解单独写的getMaxOneNode函数的意思。
 * 整体来讲：遍历每个节点，计算一遍有它参与的时候，其子树中所能产生的最大路径和，
 * 在遍历树中所有节点的过程中也就水到渠成的把最大值记录了下来
 */
public class MaxPathSum {
    public int sumMax = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        getMaxOneNode(root);
        return sumMax;
    }

    //得到某个节点作为根节点，且必须经过该节点所产生的路径的最大路径和
    public int getMaxOneNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = Math.max(getMaxOneNode(root.left), 0);
        int rightSum = Math.max(getMaxOneNode(root.right), 0);
        //更新最大值
        sumMax = Math.max(sumMax, leftSum + rightSum + root.val);

        return Math.max(leftSum, rightSum) + root.val;
    }
}
