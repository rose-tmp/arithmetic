package interview;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2021/3/19 - 14:44
 * @Description:返回一颗二叉树站在右边可以看得到的节点
 * (瓜子后端实习一面算法题)
 * 和leetcode 102. 二叉树的层序遍历及其相似
 */
public class SeeTreeNode {
    public List<TreeNode> getList(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            //遍历队列中含有的每一个节点 并将最后一个节点(即最右边可看到的节点加入res)
            for (int i = 0; i < size; i++) {
                //队列的头 即当前遍历到的节点
                TreeNode cur = queue.poll();
                //最右节点
                if (i == size - 1) {
                    res.add(cur);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
}
