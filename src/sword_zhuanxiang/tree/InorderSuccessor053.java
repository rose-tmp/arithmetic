package sword_zhuanxiang.tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/9-10:59 - 周二
 * @Description:剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 * <p>
 * 注意：本题与主站 285 题相同：
 * https://leetcode-cn.com/problems/inorder-successor-in-bst/
 */
public class InorderSuccessor053 {
    TreeNode res = null;
    TreeNode pre = new TreeNode(-1);
    /*
    * 和052的整体逻辑是一样的,都是在中序遍历的过程中维护指向上一个节点的方式去完成题目
    * */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        inOrder(root, p);
        return res;
    }

    public void inOrder(TreeNode root, TreeNode p) {
        if (root == null) {
            return;
        }
        inOrder(root.left, p);
        if (pre.val == p.val) {
            res = root;
        }
        pre = root;
        inOrder(root.right, p);
    }
}
