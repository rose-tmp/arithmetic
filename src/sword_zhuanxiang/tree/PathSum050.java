package sword_zhuanxiang.tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/8-18:11 - 周一
 * @Description:剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，
 * 求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，
 * 但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 * <p>
 * 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/
 */
public class PathSum050 {
    /**
     * 这道题注意一点：节点值有正有负 所以在getSum()中即使sum == target 依旧向下继续遍历下去
     */
    public int pathSum(TreeNode root, int targetSum) {
        int res = 0;
        res += getSum(root, 0, targetSum);
        res += pathSum(root.left, targetSum);
        res += pathSum(root.right, targetSum);
        return res;
    }

    /**
     * @param sum 走到当前root时之前遍历到的节点值的和
     * @return root节点为根节点的树上, 有多少条和等于target的路径（这里没有要求必须从根节点出发,也没有要求必须到叶子节点结束。
     * 和要求根节点到叶子节点的路径和为目标值的那道题有区别，不过整体的方法和逻辑是一样的，
     * 只是getSum()中不再需要判断root为叶子节点并且sum == target的时候才res++，
     * 而是满足sum == target之后就res++）
     */
    public int getSum(TreeNode root, int sum, int target) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (sum + root.val == target) {
            res++;
        }
        res += getSum(root.left, sum + root.val, target);
        res += getSum(root.right, sum + root.val, target);
        return res;
    }
}
