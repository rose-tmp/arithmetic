package leetcode.tree;

import leetcode.tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2020/10/12 - 14:54
 * @Description:530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 示例：
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 * 输出：
 * 1
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 提示：
 *     树中至少有 2 个节点。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst
 */
public class GetMinimumDifference {
    int pre = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    /*
    通过中序遍历来获取二叉搜索树值的有序序列，然后注意判断差值
    */
    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return min;
    }
    public void inOrder(TreeNode node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        //当中序遍历根节点左子树的最左节点时，为pre赋初值
        if(pre == Integer.MIN_VALUE){
            pre = node.val;
        }else{
            min = Math.min(min,node.val - pre);
            pre = node.val;
        }
        inOrder(node.right);
    }
}
