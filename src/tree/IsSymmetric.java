package tree;

import tool.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2021/3/26 - 15:15
 * @Description:101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树[1,2,2,3,4,4,3]是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * 但是下面这个[1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 */
public class IsSymmetric {
    /**
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return judge(root, root);
    }

    /**
     * 非递归
     * 用队列去模拟递归版本的整体流程
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if (node1 == null || node2 == null) {
                return false;
            }
            if (node1.val != node2.val) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    private boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val == right.val) {
            return judge(left.left, left.right) && judge(left.right, left.left);
        }
        return false;
    }
}