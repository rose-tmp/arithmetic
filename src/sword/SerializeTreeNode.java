package sword;

import java.util.Stack;

/**
 * @author - ZwZ
 * @date - 2020/3/3 - 10:35
 * @Description:序列化二叉树
 * 请实现两个函数，分别用来序列化和反序列化二叉树
 * 二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串
 * 从而使得内存中建立起来的二叉树可以持久保存
 * 序列化可以基于先序、中序、后序、层序的二叉树遍历方式来进行修改，
 * 序列化的结果是一个字符串，序列化时通过某种符号表示空节点（#），
 * 以 ！ 表示一个结点值的结束（value!）
 * 二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。
 */
public class SerializeTreeNode {
    //序列化 先序
    String Serialize(TreeNode root) {
        String str = "";
        if (root == null)
            return null;
        Stack<TreeNode> stack = new Stack();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            str = str + node.val + ",";
            if (node.right != null)
                stack.push(node.right);
            else
                str = str + "#" + ",";
            if (node.left != null)
                stack.push(node.left);
            else
                str = str + "#" + ",";
        }
        return str + "!";

    }
    int index = -1;
    //反序列化
    TreeNode Deserialize(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.deleteCharAt(sb.length() - 1);
        str = sb.toString();
        String[] s = str.split(",");//将序列化之后的序列用，分隔符转化为数组
        index++;//索引每次加一
        int len = s.length;
        if (index > len) {
            return null;
        }
        TreeNode treeNode = null;
        if (!s[index].equals("#")) {//不是叶子节点 继续走 是叶子节点出递归
            treeNode = new TreeNode(Integer.parseInt(s[index]));
            treeNode.left = Deserialize(str);
            treeNode.right = Deserialize(str);
        }
        return treeNode;
    }
    public static void main(String[] args) {
        SerializeTreeNode serialize = new SerializeTreeNode();
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);
        String str = "5,4,#,3,#,2";
        System.out.println(serialize.Deserialize(str));
    }
}
