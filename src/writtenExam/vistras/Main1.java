package writtenExam.vistras;

/**
 * @author: ZwZ
 * @date: 2022-09-15 14:49
 * @desc:
 */
public class Main1 {
    /**
     * 思路：二分查找
     *
     * @param totalNum 总页码数
     * @return 出错的页码
     */
    public int getErrorPageNum(int totalNum) {
        int left = 0;
        int right = totalNum;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isGoodPage(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean isGoodPage(int page_number) {
        return true;
    }
}
