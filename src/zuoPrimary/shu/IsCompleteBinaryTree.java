package zuoPrimary.shu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/3/25 - 22:03
 * @Description:是否为完全二叉树
 */
public class IsCompleteBinaryTree {
    /** 
    * @Author: ZwZ
    * @Description:层次遍历二叉树，如果某节点：
     * 1.存在右子树但是不存在左子树，直接返回false
     * 2.存在左子树但是不存在右子树，则层次遍历接下来的所有节点都必须是叶子结点(没有左右子树)
     * 3.存在左子树和右子树，则继续层次遍历
     * 4.不存在左子树和右子树，继续层次遍历 
    * @Param: [node] 
    * @return: boolean 
    * @Date: 2020/3/25-22:15
    */
    public boolean isCompleteBinaryTree(TreeNode node){
        if(node == null || (node.left == null && node.right == null))
            return true;
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(node);
        while(!queue.isEmpty()){
            TreeNode curNode = queue.poll();//得到但不删除队列头部节点
            if(curNode.left == null && curNode.right != null){
                return false;
            }
            if(curNode.left != null)
                queue.offer(curNode.left);
            if(curNode.right != null)
                queue.offer(curNode.right);
            if(curNode.left != null && curNode.right == null){
                while(!queue.isEmpty()){
                    TreeNode temp = queue.poll();
                    if(temp.left != null || temp.right != null)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsCompleteBinaryTree completeBinaryTree = new IsCompleteBinaryTree();
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(10);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(12);
        root.right = new TreeNode(5) ;
        root.right.left = new TreeNode(18);
        root.right.right = new TreeNode(9);
        System.out.println(completeBinaryTree.isCompleteBinaryTree(root));
    }
}
