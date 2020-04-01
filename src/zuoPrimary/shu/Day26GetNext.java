package zuoPrimary.shu;

import java.util.ArrayList;

/**
 * @author - ZwZ
 * @date - 2020/2/12 - 12:59
 * @Description:二叉树的下一个结点 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 *
 * 此题找后继节点，找前驱结点和其思路相同
 */
class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;//父节点

    TreeLinkNode(int val) {
        this.val = val;
    }
}

public class Day26GetNext {
    ArrayList<TreeLinkNode> list = new ArrayList();//存储中序遍历结果

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:找到根节点，中序遍历整个二叉树 然后在遍历序列中寻找指定节点的后序节点 
     * @Param: [pNode] 
     * @return: zuoPrimary.shu.TreeLinkNode 
     * @Date: 2020/3/25-20:41
     */
    public TreeLinkNode GetNext1(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        TreeLinkNode root = pNode;
        //寻找父节点
        while (root != null) {
            if (root.next != null)
                root = root.next;
            else
                break;
        }
        inOrder(root);
        int i;
        for (i = 0; i < list.size(); i++) {
            if (list.get(i) == pNode)
                break;
        }
        return i == list.size() - 1 ? null : list.get(i + 1);
    }

    /**
     *  
     *
     * @Author: ZwZ
     * @Description:若该节点有右子树，则其后续节点是右子树上的最左节点 如果该节点没有右子树，则其属于哪一个树的最子树的最右节点，则这颗树的根节点就是它的后续节点 
     * @Param: [node] 
     * @return: zuoPrimary.shu.TreeLinkNode 
     * @Date: 2020/3/25-20:44
     */
    public TreeLinkNode getNext2(TreeLinkNode node) {
        //没有右子树
        if (node.right == null) {
            TreeLinkNode parent = node.next;
            //node指向的节点不是父节点的左孩子 此时两个指针继续向上寻找 parent==null是对边界的考察：给定的node是中序遍历最末尾节点时的情况，此时由于他没有后续节点，所以parent会一直向上跑到null 为了避免因为parent == null时依旧进入while而导致空指针异常，所以加入了parent!= null作为条件
            while (parent.left != node && parent != null) {
                node = parent;
                parent = parent.next;
            }
            return parent;
        } else {
            node = node.right;
            //寻找右子树中的最左节点
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
    }

    //中序遍历
    private void inOrder(TreeLinkNode root) {
        if (root != null) {
            inOrder(root.left);
            list.add(root);
            inOrder(root.right);
        }
    }
}
