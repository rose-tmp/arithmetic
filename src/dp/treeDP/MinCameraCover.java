package dp.treeDP;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2020/9/22 - 20:41
 * @Description:监控二叉树 给定一个二叉树，我们在树的节点上安装摄像头
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象
 * 现在要求给二叉树的某些节点上安装摄像头，使得所有的节点都被监控
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 示例 1：
 * 0
 * /
 * 0(摄像头)
 * / \
 * 0   0
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * 示例 2：
 * 0
 * /
 * 0(摄像头)
 * /
 * 0
 * /
 * 0(摄像头)
 * \
 * 0
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 * https://leetcode.cn/problems/binary-tree-cameras/
 */
public class MinCameraCover {
    /*public int minCameraCover(TreeNode root) {
        int[] res = dfs2(root);
        return Math.min(res[1], res[2]);
    }

    *//**
     * @return: arr[0]:本节点无覆盖时所需的最小摄像头数量
     * arr[1]:本节点有覆盖但是没有摄像头时所需的最小摄像头数量
     * arr[2]:本节点有摄像头时所需的最小摄像头数量
     *//*
    public int[] dfs2(TreeNode root) {
        if (root == null) {
            return new int[3];
        }
        int[] arr = new int[3];
        int[] leftArr = dfs2(root.left);
        int[] rightArr = dfs2(root.right);
        arr[0] = leftArr[1] + rightArr[1];
        //左或者右至少有一个有摄像头
        arr[1] = Math.min(leftArr[2], rightArr[2]);
        arr[2] = 1 + leftArr[1] + rightArr[1];
        return arr;
    }*/

    public int minCameraCover(TreeNode root) {
        int[] array = dfs(root);
        return array[1];
    }

    public int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] leftArray = dfs(root.left);
        int[] rightArray = dfs(root.right);
        int[] array = new int[3];
        array[0] = leftArray[2] + rightArray[2] + 1;
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], rightArray[0] + leftArray[1]));
        array[2] = Math.min(array[0], leftArray[1] + rightArray[1]);
        return array;
    }
}
