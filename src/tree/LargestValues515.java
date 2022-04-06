package tree;

import tool.TreeNode;

import java.util.*;

/**
 * @author - ZwZ
 * @date - 2022/4/6-16:34 - 周三
 * @Description:剑指 Offer II 044. 二叉树每层的最大值
 * 515. 在每个树行中找最大值
 * 给定一棵二叉树的根节点root，请找出该二叉树中每一层的最大值。
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 * 1
 * /  \
 * 3    2
 * / \    \
 * 5  3     9
 * 示例2：
 * <p>
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 * 1
 * / \
 * 2   3
 * 示例3：
 * <p>
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 * <p>
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 * 1
 * \
 * 2
 * 示例5：
 * <p>
 * 输入: root = []
 * 输出: []
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hPov7L
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestValues515 {
    /**
     * 层次遍历过程中做节点的记录
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            int len = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node.val > max) {
                    max = node.val;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}
