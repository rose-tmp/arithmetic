package leetcode;

import leetcode.pool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/9/24 - 17:54
 * @Description:
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 *
 * 假定 BST 有如下定义：
 *
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 *
 *    1
 *     \
 *      2
 *     /
 *    2
 * 返回[2].
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 */
public class FindMode {
    List<Integer> List = new ArrayList<>();
    int pre = 0;//中序遍历时当前节点的上一个节点
    int count = 0;//表示当前节点的数量
    int maxCount = 0;//最大的重复数量

    public int[] findMode(TreeNode root) {
        inOrderTraversal(root);
        int[] res = new int[List.size()];
        //把集合list转化为数组
        for (int i = 0; i < List.size(); i++) {
            res[i] = List.get(i);
        }
        return res;
    }

    /**
     * 中序遍历中，当前节点如果和上一个节点的值不相同时，则此时拿当前节点之前出现的节点的个数与max进行比较
     * @param root
     */
    public void inOrderTraversal(TreeNode root) {
        if (root == null)
            return;
        inOrderTraversal(root.left);
        //对当前节点的一些逻辑操作
        int rootVal = root.val;
        if (rootVal == pre) {
            count++;
        } else {
            pre = rootVal;
            count = 1;
        }
        if (count == maxCount) {
            List.add(rootVal);
        } else if (count > maxCount) {
            List.clear();
            List.add(rootVal);
            maxCount = count;
        }
        inOrderTraversal(root.right);
    }

    public static void main(String[] args) {
        FindMode mode = new FindMode();
        TreeNode root = new TreeNode(0);
        int[] arr = mode.findMode(root);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
