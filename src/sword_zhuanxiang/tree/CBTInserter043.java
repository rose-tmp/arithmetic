package sword_zhuanxiang.tree;

import tool.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2022/8/8-14:52 - 周一
 * @Description:剑指 Offer II 043. 往完全二叉树添加节点
 * 完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，
 * 并且所有的节点都尽可能地集中在左侧。
 * 设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：
 * <p>
 * CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。
 * 使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
 * CBTInserter.get_root() 将返回树的根节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
 * 输出：[null,1,[1,2]]
 * 示例 2：
 * <p>
 * 输入：inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
 * 输出：[null,3,4,[1,2,3,4,5,6,7,8]]
 * <p>
 * 提示：
 * <p>
 * 最初给定的树是完全二叉树，且包含 1 到 1000 个节点。
 * 每个测试用例最多调用 CBTInserter.insert  操作 10000 次。
 * 给定节点或插入节点的每个值都在 0 到 5000 之间。
 * <p>
 * 注意：本题与主站 919 题相同： https://leetcode-cn.com/problems/complete-binary-tree-inserter/
 */
public class CBTInserter043 {
    TreeNode root;

    public CBTInserter043(TreeNode root) {
        this.root = root;
    }

    /**
     * 层次遍历的变形题目:
     * 新加入的节点的父亲为层次遍历过程中第一个左节点不为空或者右节点不为空的节点
     */
    public int insert(int v) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left == null) {
                TreeNode node = new TreeNode(v);
                cur.left = node;
                queue.add(node);
                return cur.val;
            } else if (cur.right == null) {
                TreeNode node = new TreeNode(v);
                cur.right = node;
                queue.add(node);
                return cur.val;
            } else {//左右孩子都不为空，此时将该节点的左右孩子加入队列后将其出队列
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return 0;
    }

    public TreeNode get_root() {
        return root;
    }
}
