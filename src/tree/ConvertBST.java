package tree;

import zuoPrimary.shu.TreeNode;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/9/21 - 8:41
 * @Description:给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
 */
public class ConvertBST {
    /*
     * 反向中序遍历
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null)
            return null;
        TreeNode node = root;//当前节点指针
        Stack<TreeNode> stack = new Stack<>();
        int sum = 0;
        //node != null用于一开始node指向根节点时
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                TreeNode currentNode = stack.pop();
                sum += currentNode.val;
                currentNode.val = sum;
                node = currentNode.left;//向左子树走
            }
        }
        return root;
    }

    /**
     * 反向中序遍历
     */
    int sum = 0;

    public TreeNode convertBST2(TreeNode root) {
        int res = 0;
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
