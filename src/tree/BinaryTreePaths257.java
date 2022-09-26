package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-22 23:01
 * @desc:
 */
public class BinaryTreePaths257 {

    public List<String> binaryTreePaths(TreeNode root) {
        dfs("", root);
        return res;
    }

    public List<String> res = new ArrayList<>();

    public void dfs(String cur, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            cur = cur + root.val;
            res.add(cur);
            return;
        }
        cur = cur + root.val;
        cur += "->";
        dfs(cur, root.left);
        dfs(cur, root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        BinaryTreePaths257 binaryTreePaths257 = new BinaryTreePaths257();
        System.out.println(binaryTreePaths257.binaryTreePaths(root));
        ;
    }
}
