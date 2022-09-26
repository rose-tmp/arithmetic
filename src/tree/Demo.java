package tree;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-09-22 22:39
 * @desc:
 */
public class Demo {
    /**
     * @return 以root为根节点的树的路径和
     */
    public int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        int val = 0;
        int leftV = getSum(root.left);
        if (leftV != 0) {
            val += root.val * 10 + leftV;
        }
        int rightV = getSum(root.right);
        if (rightV != 0) {
            val += root.val * 10 + rightV;
        }
        if (rightV == 0 && leftV == 0) {
            val += root.val;
        }
        return val;
    }

    public int getSum2(TreeNode root) {
        dfs(new ArrayList<>(), root);
        int sum = 0;
        for (int i = 0; i < res.size(); i++) {
            sum += res.get(i);
        }
        return sum;
    }

    List<Integer> res = new ArrayList<>();

    public void dfs(List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            int sum = root.val;
            for (int i = list.size() - 1; i >= 0; i--) {
                sum += list.get(i) * 10;
            }
            res.add(sum);
            return;
        }
        list.add(root.val);
        dfs(list, root.left);
        dfs(list, root.right);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println(demo.getSum2(root));
        ;
    }
}
