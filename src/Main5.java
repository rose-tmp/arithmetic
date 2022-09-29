/**
 * @author: ZwZ
 * @date: 2022-09-29 12:12
 * @desc:
 */
public class Main5 {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        while (l < r) {
            int cur = Math.min(height[l], height[r]) * (r - l);
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
            max = Math.max(max, cur);
        }
        return max;
    }
}
