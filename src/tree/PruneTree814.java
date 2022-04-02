package tree;

import tool.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2022/4/2 11:33 下午
 * @Description:剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 *
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 *
 *
 * 示例 2:
 *
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 解释:
 *
 *
 * 示例 3:
 *
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 解释:
 *
 *
 *  
 *
 * 提示:
 *
 * 二叉树的节点个数的范围是 [1,200]
 * 二叉树节点的值只会是 0 或 1
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pOCWxh
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PruneTree814 {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null || root.val == 0 && fuck(root.left) && fuck(root.right)) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (fuck(node.left)) {
                node.left = null;
            }
            if (fuck(node.right)) {
                node.right = null;
            }
        }
        return root;
    }

    //判断以当前节点为跟节点的子树是否可以删除
    public boolean fuck(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.val == 1) {
            return false;
        } else {
            return fuck(root.left) && fuck(root.right);
        }
    }
}
