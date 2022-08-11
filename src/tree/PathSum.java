package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2020/9/26 - 17:59
 * @Description:
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明:叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 */
public class PathSum {
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(new ArrayList<>(), root, targetSum);
        return res;
    }

    //从根节点为root的树中寻找路径和为target的路径
    public void dfs(List<Integer> list, TreeNode root, int target) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        if (root.left == null && root.right == null) {
            if (target == sum) {
                res.add(new ArrayList<>(list));
            }
        }
        dfs(list, root.left, target);
        dfs(list, root.right, target);
        list.remove(list.size() - 1);
    }

    /*List<Integer> list = new ArrayList();
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        preOrder(root,sum);
        return res;
    }
    public void preOrder(TreeNode root, int sum){
        if(root == null){
            return ;
        }
        list.add(root.val);//加入队列
        sum -= root.val;
        //跟节点到叶子节点的总和等于给定目标和
        if(root.left == null && root.right == null && sum == 0){
            List<Integer> listCopy = new ArrayList();
            for(int i = 0; i < list.size();i++){
                listCopy.add(list.get(i));
            }
            res.add(listCopy);
        }
        preOrder(root.left,sum);
        preOrder(root.right,sum);
        list.remove(list.size() - 1);
    }*/
}
