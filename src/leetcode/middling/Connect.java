package leetcode.middling;

import leetcode.tool.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/10/15 - 7:38
 * @Description:116. 填充每个节点的下一个右侧节点指针
 * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * <p>
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
 */
public class Connect {
    /**
     * 在层次遍历上加以改进
     */
    public Node connect1(Node root) {
        if (root == null)
            return null;
        Queue<Node> queue = new LinkedList();//队列
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();//本层节点个数
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                //边界设置成size - 1而非size是当node为本层的最右边的节点时，就直接不给其next赋值，默认的就是NullNull
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }
    /**
     * 不使用层次遍历，不创建队列，从而将空间复杂度降为O(1)
     * */
    public Node connect2(Node root) {
        if (root == null)
            return null;
        //cur我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            Node dummy = new Node(0);
            Node pre = dummy;
            //因为是完美二叉树，如果有左子节点就一定有右子节点
            while (cur != null && cur.left != null) {
                pre.next = cur.left;
                pre = pre.next;
                pre.next = cur.right;
                pre = pre.next;
                cur = cur.next;
            }
            //进入下一层
            cur = dummy.next;
        }
        return root;
    }
    /**
     * connect2的不同形式实现  在这里没有创建哑节点
     * connect2使用哑节点找到下一层的最左节点
     * */
    public Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        // 从根节点开始
        Node leftmost = root;
        while (leftmost.left != null) {
            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node node = leftmost;
            while (node != null) {
                node.left.next = node.right;
                if (node.next != null) {
                    node.right.next = node.next.left;
                }
                // 指针向后移动
                node = node.next;
            }
            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }
}
