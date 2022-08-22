package sword_zhuanxiang.tree;

import tool.TreeNode;

/**
 * @author: ZwZ
 * @date: 2022-08-20 22:39
 * @desc:剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。
 * 请剪除该二叉树中所有节点的值为 0 的子树。
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 *
 * 示例 1:
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 *
 * 示例 2:
 *
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 解释:
 *
 * 示例 3:
 *
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 解释:
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,200]
 * 二叉树节点的值只会是 0 或 1
 *
 *
 * 注意：本题与主站 814 题相同：https://leetcode-cn.com/problems/binary-tree-pruning/
 */
public class PruneTree47 {
    /**
     *
     * */
    public TreeNode pruneTree1(TreeNode root) {
        if (isZero(root)) {
            return null;
        }
        if(isZero(root.left)) {
            root.left = null;
        }
        if (isZero(root.right)) {
            root.right = null;
        }
        pruneTree1(root.left);
        pruneTree1(root.right);
        return root;
    }
    /**
     * 以root为根节点的树上是否全部为0
     */
    public boolean isZero(TreeNode root) {
        if(root == null) {
            return true;
        }
        if (root.val == 1) {
            return false;
        }
        return isZero(root.left) && isZero(root.right);
    }
    /**
     * @return 以root为根节点的二叉树去除了它上面所有节点的值为 0 的子树后的根节点
     * 值为0的节点:
     * 1. 该节点是叶子节点
     * 2. 该节点不是叶子节点，且其左右子树上不全是值为0的节点
     * 3. 该节点不是叶子节点，且其左右子树上全是值为0的节点
     * */
    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        //root为叶子节点 并且它的值为0
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        //将root的左子树上所有节点的值为0的树删除
        TreeNode l = pruneTree2(root.left);
        //将root的右子树上所有节点值为0的树删除
        TreeNode r = pruneTree2(root.right);
        //如果当前节点值为0 左右子树也都删除了 则同样返回null
        if (root.val == 0 && l == null && r == null) {
            return null;
        }
        root.left = l;
        root.right = r;
        return root;
    }
}
