package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/4/10-21:01 - 周日
 * @Description:剑指 Offer II 052. 展平二叉搜索树
 * 给你一棵二叉搜索树，请按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * <p>
 * 示例 1：
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * <p>
 * 示例 2：
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 * 提示：
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 * <p>
 * 注意：本题与主站 897题相同：https://leetcode-cn.com/problems/increasing-order-search-tree/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/NYBBNL
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IncreasingBST897 {
    /**
     * 1.中序遍历并在过程中将每一个节点记录到list中
     * 2.遍历list将节点拿出按照要求组成一个新二叉树
     */
    public TreeNode increasingBST1(TreeNode root) {

        return null;
    }

    /**
     * 在中序遍历过程中进行新二叉树的构造
     */
    TreeNode dummyNode = new TreeNode(-1);
    TreeNode preNode = dummyNode;

    public TreeNode increasingBST2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        fuck(root);
        return dummyNode.right;
    }

    public void fuck(TreeNode root) {
        if (root == null) {
            return;
        }
        fuck(root.left);

        preNode.right = root;
        root.left = null;
        preNode = root;

        fuck(root.right);
    }
}
