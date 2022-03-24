package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2021/3/30 - 14:34
 * @Description:98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 */
public class IsValidBST {
    List<Integer> list = new ArrayList();

    /**
     * 判断中序遍历结果是否是有序序列
     * 这道题如果用递归去写，递归判断当前树，左子树，右子树是不是搜索树，如果是就ture
     * 这样子的思路是不对的，具体可以看示例2
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        inOrder(root);
        if (list.size() <= 1) {
            return true;
        }
        //判断中序遍历结果是否是有序序列
        for (int i = 0, j = 1; j < list.size(); i++, j++) {
            if (list.get(i) < list.get(j)) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return judge(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean judge(TreeNode root, long low, long high) {
        if (root == null) {
            return true;
        }
        if (root.val <= low || root.val >= high) {
            return false;
        }
        return judge(root.left, root.val, high) && judge(root.right, low, root.val);
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        list.add(root.val);
        inOrder(root.right);
    }
}
