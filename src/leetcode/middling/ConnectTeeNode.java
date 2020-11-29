package leetcode.middling;

import leetcode.tool.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/9/28 - 8:44
 * @Description: 给定一个二叉树
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有next 指针都被设置为 NULL。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii
 */


public class ConnectTeeNode {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        //队列
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node temp = null;
            for (int i = 0; i < size; i++) {
                Node queueHead = queue.poll();//删除头部
                if (queueHead.left != null) {
                    queue.offer(queueHead.left);
                }
                if (queueHead.right != null) {
                    queue.offer(queueHead.right);
                }
                if (i != 0) {
                    temp.next = queueHead;
                }
                temp = queueHead;
            }
        }
        return root;
    }
}
