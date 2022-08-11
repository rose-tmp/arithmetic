package tree;

import tool.TreeNode;

/**
 * @author - ZwZ
 * @date - 2022/7/20-20:17 - 周三
 * @Description:687. 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 *
 * 两个节点之间的路径长度 由它们之间的边数表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-univalue-path
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *  思路：
 * https://leetcode.cn/problems/longest-univalue-path/solution/guan-yu-di-gui-si-lu-de-chao-xiang-xi-ge-ren-jian-/
 */
public class LongestUnivaluePath {
    int ans = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        //左右节点所能贡献的最值
        int maxSon = 0;
        if (root.left != null && root.left.val == root.val) {
            maxSon = left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            maxSon = Math.max(maxSon, right + 1);
        }
        if (root.left != null && root.right != null && root.left.val == root.val && root.val == root.right.val) {
            ans = Math.max(ans, left + right + 2);
        }
        ans = Math.max(ans, maxSon);
        return maxSon;
    }
}
