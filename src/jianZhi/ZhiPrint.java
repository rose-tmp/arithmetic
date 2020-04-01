package jianZhi;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author - ZwZ
 * @date - 2020/3/1 - 21:40
 * @Description:之字型打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印
 */
public class ZhiPrint {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(pRoot == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        int flag = 1;//行数
        int count;
        while(!queue.isEmpty()){
            count = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                //奇数行
                if((flag & 1) == 0){
                    if(temp.left != null)
                        queue.offer(temp.left);
                    if(temp.right != null)
                        queue.offer(temp.right);
                }
                //偶数行
                if((flag & 1) == 1){
                    if(temp.right != null)
                        queue.offer(temp.right);
                    if(temp.left != null)
                        queue.offer(temp.left);
                }
            }
            flag++;
            result.add(list);
        }
        return result;
    }
}
