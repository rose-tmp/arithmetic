package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2021/3/28 - 20:14
 * @Description:
 */
public class BSTIterator {
    List<TreeNode> list;

    int index = 0;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        list.add(new TreeNode(Integer.MIN_VALUE));
        inOrder(root);
    }

    public int next() {
        return list.get(++index).val;
    }

    public boolean hasNext() {
        if (index != list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node);
                node = node.right;
            }
        }
    }
}
