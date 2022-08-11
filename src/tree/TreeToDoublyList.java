package tree;

/**
 * @author - ZwZ
 * @date - 2022/7/19-20:09 - 周二
 * @Description:剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。
 * 要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class TreeToDoublyList {
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        dummyNode.right.left = pre;
        pre.right = dummyNode.right;
        return dummyNode.right;
    }
    Node dummyNode = new Node(-1);
    Node pre = dummyNode;
    public void inOrder(Node root) {
        if(root == null) {
            return;
        }
        inOrder(root.left);
        pre.right = root;
        root.left = pre;
        pre = root;
        inOrder(root.right);
    }
}
