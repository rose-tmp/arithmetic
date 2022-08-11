package sword_zhuanxiang.tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/8-23:26 - 周一
 * @Description:剑指 Offer II 052. 展平二叉搜索树
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 *
 * 示例 1：
 *
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 *
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 *
 * 提示：
 *
 * 树中节点数的取值范围是 [1, 100]
 * 0 <= Node.val <= 1000
 *
 * 注意：本题与主站 897 题相同： https://leetcode-cn.com/problems/increasing-order-search-tree/
 */
public class IncreasingBST052 {
    TreeNode dummyNode = new TreeNode(-1);
    TreeNode preNode = dummyNode;

    public TreeNode increasingBST(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }
        inOrder(root);
        return dummyNode.right;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        preNode.right = root;
        root.left = null;
        preNode = root;
        inOrder(root.right);
    }
}
