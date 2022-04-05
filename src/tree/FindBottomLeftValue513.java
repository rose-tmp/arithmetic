package tree;

import tool.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2022/4/5 10:42 下午
 * @Description:513. 找树左下角的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的最底层最左边节点的值
 * 假设二叉树中至少有一个节点。
 * <p>
 * <p>
 * 示例 1:
 * 输入: root = [2,1,3]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [1,104]
 * -2^31<= Node.val <= 2^31- 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/LwUNpT
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindBottomLeftValue513 {
    /**
     * 先序遍历过程中记录节点值
     */
    int maxHigh = Integer.MIN_VALUE;
    TreeNode node = null;
    public int findBottomLeftValue2(TreeNode root) {
        fuck(root,0);
        return node.val;
    }

    public void fuck(TreeNode root, int high) {
        if (root == null) {
            return;
        }
        if (high > maxHigh) {
            maxHigh = high;
            node = root;
        }
        fuck(root.left, high + 1);
        fuck(root.right, high + 1);
    }

    /**
     * 思路： 层次遍历的过程中记录每一层的最左侧节点
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root.val;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            TreeNode pre = null;
            for (int i = 0; i < len; i++) {
                //记录每层最左侧节点
                if (i == 0) {
                    pre = queue.peek();
                }
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                // 遍历到了最后一层
                if (queue.isEmpty()) {
                    res = pre.val;
                }
            }
        }
        return res;
    }
}
