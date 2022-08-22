package interview.byteDance_huyu;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/8/8-22:21 - 周一
 * @Description:字节跳动二面 求一个叶子节点到另一个叶子节点的最长路径的长度
 * 例如：
 *            x
 *          /   \
 *         x     x
 *      /    \
 *     x      x
 *    / \    / \
 *   x   x  x   x
 * 最长路径是5
 * 这道题和https://leetcode.cn/problems/jC7MId/挺像，但是没有它难
 */
public class Main2 {
    public static void main(String[] args) {
        Main2 main2 = new Main2();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(1);
        root.left.left.left = new TreeNode(1);
        //root.left.left.left.left = new TreeNode(1);
        root.left.right = new TreeNode(1);
        root.left.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(1);
        System.out.println(main2.getPath2(root));
    }

    /**
     * 面试的时候的思路:
     * 1. 以某个节点root为根节点的树，必须经过root时，其最长路径长度即为(左子树高度+右子树高度+1)
     * 2. 对每个节点都计算一遍步骤1，即以它为根节点的树，必须先经过它时，最长路径的长度
     * <p>
     * 该方法的复杂度是非常高的，因为涉及到了大量的重复计算（同一个树被在多个地方递归计算了高度）
     * 面试的时候面试官提醒了我有复杂度为O(N)的算法，可以考虑一下后序遍历
     * 但是面试的时候慌，没想出来
     */
    int res = 0;

    /**
     * 得到以root为跟节点的树的最长路径长度
     */
    public int getPath1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //经过root的路径的最长路径长度
        res = Math.max(res, getSum(root));
        //得到以root.left为根节点的树的最长路径长度
        res = Math.max(res, getPath1(root.left));
        res = Math.max(res, getPath1(root.right));

        return res;
    }

    /**
     * 使用后序遍历降低时间复杂度
     * 在getPath1中是从上到下，在getPath2中自下到上
     * 在计算高度的过程中更新结果
     * @return 以root为根节点，经过root的最长路径的长度
     */
    int res2 = 0;
    public int getPath2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        getHigh2(root);
        return res2;
    }

    /**
     * @return 以root为根节点的树的高度
     */
    private int getHigh2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int lMax = getHigh2(root.left);
        int rMax = getHigh2(root.right);
        //在计算出高度向上返回的过程中，更新最终结果
        res2 = Math.max(res2,1 + lMax+rMax);
        return 1 + Math.max(lMax, rMax);
    }

    /**
     * 计算经过root的路径的最长路径长度
     */
    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int lHigh = getHigh(root.left);
        int rHigh = getHigh(root.right);
        return 1 + lHigh + rHigh;
    }

    /**
     * 计算二叉树高度
     */
    private int getHigh(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int lHigh = getHigh(root.left);
        int rHigh = getHigh(root.right);
        return 1 + Math.max(lHigh, rHigh);
    }
}
