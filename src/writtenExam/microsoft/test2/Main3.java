package writtenExam.microsoft.test2;
import java.util.ArrayList;
import java.util.List;
public class Main3 {
        public int solution(int[] A, int X, int Y) {
            // write your code in Java 8 (Java SE 8)
            int res = Integer.MAX_VALUE;
            for (int start = 0; start < Y; start++) {
                List<Integer> list = new ArrayList<>();
                for (int i = start; i < A.length; i += Y) {
                    list.add(A[i]);
                }
                if (list.size() < X) {
                    continue;
                }
                int[] subArray = list.stream().mapToInt(i -> i).toArray();
                int localMinCost = minSum(subArray, X);
                res = Math.min(res, localMinCost);
            }
            return res;
        }
        /**
         * 长度为len的子数组的最小和
         * nums.length > len
         * @param nums
         * @param len
         */
        private int minSum(int[] nums, int len) {
            int curSum = 0;
            for (int i = 0; i < len; i++) {
                curSum += nums[i];
            }
            int minSum = curSum;
            for (int i = len; i < nums.length; i++) {
                curSum += nums[i] - nums[i - len];
                minSum = Math.min(minSum, curSum);
            }
            return minSum;
        }
}
