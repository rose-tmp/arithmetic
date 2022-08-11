package interview.byteDance_huyu;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/8-22:20 - 周一
 * @Description:字节提前批 一面
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 *  
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109 
 * -1000 <= targetSum <= 1000 
 *  
 * <p>
 * 注意：本题与主站 437 题相同：https://leetcode-cn.com/problems/path-sum-iii/
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/6eUYwP
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Main1 {
    int res = 0;

    public int pathSum(TreeNode root, int targetSum) {
        fuck(root, targetSum);
        return res;
    }
    /**
     * 求以root为根节点的树上有多少满足条件的情况
     * */
    public void fuck(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        dfs(root, root.val, targetSum);
        fuck(root.left, targetSum);
        fuck(root.right, targetSum);
    }

    /**
     * 记录选中root节点，以此节点为root当前子树下满足条件的情况
     */
    public void dfs(TreeNode root, int sum, int target) {
        if (sum == target) {
            res++;
        }
        if (root.left != null) {
            dfs(root.left, sum + root.left.val, target);
        }
        if (root.right != null) {
            dfs(root.right, sum + root.right.val, target);
        }
    }
}
