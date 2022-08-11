package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/4-18:44 - 周四
 * @Description:105. 从前序与中序遍历序列构造二叉树
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class BuildTree105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        if (preorder.length == 1 && inorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        TreeNode root = new TreeNode(preorder[0]);
        int indexR = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                indexR = i;
                break;
            }
        }
        int[] newPreLeft = new int[indexR];
        for (int i = 0; i < newPreLeft.length; i++) {
            newPreLeft[i] = preorder[i + 1];
        }
        int[] newPreRight = new int[preorder.length - indexR - 1];
        for (int i = 0, j = indexR + 1; i < newPreRight.length; i++, j++) {
            newPreRight[i] = preorder[j];
        }
        int[] newInLeft = new int[indexR];
        for (int i = 0; i < indexR; i++) {
            newInLeft[i] = inorder[i];
        }
        int[] newInRight = new int[inorder.length - indexR - 1];
        for (int i = 0, j = indexR + 1; i < newInRight.length; i++, j++) {
            newInRight[i] = inorder[j];
        }
        TreeNode leftNode = buildTree(newPreLeft,newInLeft);
        TreeNode rightNode = buildTree(newPreRight,newInRight);
        root.left = leftNode;
        root.right = rightNode;
        return root;
    }
}
