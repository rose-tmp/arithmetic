package tree.BiTreeOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 19:37
 * @Description:非递归先序遍历二叉树
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class PreOrder {

    /**
     * @Author: ZwZ
     * @Description:先让节点的右节点先进栈再将左节点进栈 从而达到出栈顺序是先左后右
     * @Param: [root]
     * @return: void
     * @Date: 2020/2/12-20:13
     */
    public void preOrder1(TreeNode root) {
        if (root == null)
            return;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();//当前节点
            //visit(currentNode);
            System.out.println(currentNode.val);
            if (currentNode.right != null)
                stack.push(currentNode.right);
            if (currentNode.left != null)
                stack.push(currentNode.left);
        }
    }

    public List<Integer> preorder2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            while (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return res;
    }

    public static void main(String[] args) {
        PreOrder order = new PreOrder();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        order.preOrder1(root);
    }

}
