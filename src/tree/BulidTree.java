package tree;

import tool.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author - ZwZ
 * @date - 2020/9/25 - 22:13
 * @Description:
 */
public class BulidTree {
    int postOrderIndex;
    int rootVal;
    int[] postorder;
    //int rootInorderIndex;
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        postOrderIndex = postorder.length - 1;
        return helper(0, inorder.length - 1);
    }

    /**
     * 创建存储中序遍历数组中<val,index>关系的map
     * @param inleft
     * @param inRight
     * @return
     */
    public TreeNode helper(int inleft, int inRight) {
        if (inleft > inRight) {
            return null;
        }
        rootVal = postorder[postOrderIndex];
        int rootInorderIndex = map.get(rootVal);//根节点在中序遍历数组中的下标
        TreeNode root = new TreeNode(rootVal); //此次遍历的根节点
        postOrderIndex--;
        root.right = helper(rootInorderIndex + 1, inRight);
        root.left = helper( inleft, rootInorderIndex - 1);
        return root;
    }
    public void inOrder(TreeNode root) {
        if (root == null)
            return;
        inOrder(root.left);
        //visit(root);
        System.out.println(root.val);
        inOrder(root.right);
    }
    public void postOrder(TreeNode root) {
        if (root == null)
            return;
        postOrder(root.left);
        postOrder(root.right);
        //visit(root);
        System.out.println(root.val);
    }
    public static void main(String[] args) {
        BulidTree sum = new BulidTree();
        int[] inOrder = {9, 3, 15, 20, 7};
        int[] postOrder = {9, 15, 7, 20, 3};
        TreeNode root = sum.buildTree(inOrder, postOrder);
        sum.inOrder(root);
        System.out.println("--------");
        sum.postOrder(root);
    }
}
