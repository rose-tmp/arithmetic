package sword2;

/**
 * @author: ZwZ
 * @date: 2022-09-22 23:45
 * @desc:
 */
public class VerifyPostorder33 {
    public boolean verifyPostorder(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    /**
     * @return postorder[l, r]构成的后序遍历序列是否是一个...
     */
    public boolean verify(int[] postorder, int l, int r) {
        if (l >= r) {
            return true;
        }
        int index = l;
        while (postorder[index] < postorder[r]) {
            index++;
        }
        int index2 = index;
        while (index2 < r) {
            if (postorder[index2] < postorder[r]) {
                return false;
            }
        }
        return verify(postorder, l, index - 1) && verify(postorder, index + 1, r);
    }
}