package backtrackingAndDFS;

import tool.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author - ZwZ
 * @date - 2022/3/15 4:17 下午
 * @Description:95. 不同的二叉搜索树 II  [96. 不同的二叉搜索树 NumsTree 是一个dp问题]
 * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：[[1]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    //得到[pre,rear]范围内的所有二叉搜索树
    public List<TreeNode> dfs(int pre, int rear) {
        List<TreeNode> res = new ArrayList<>();
        if (pre > rear) {
            //这里add(null)为了进入上层的for后给root.left或root.right赋值为null 而不是直接因为list.size()==0不进入上层递归的for
            res.add(null);
            return res;
        }
        for (int i = pre; i <= rear; i++) {
            //左子树
            List<TreeNode> left = dfs(pre, i - 1);
            //右子树
            List<TreeNode> right = dfs(i + 1, rear);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
