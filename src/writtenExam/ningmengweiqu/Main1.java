package writtenExam.ningmengweiqu;

import java.util.*;

/**
 * @author: ZwZ
 * @date: 2022-09-04 10:51
 * @desc:
 */
public class Main1 {
    public ArrayList<Integer> findNext1(ArrayList<Integer> nums) {
        // write code here
        ArrayList<Integer> list = new ArrayList<>();
        if (nums.size() == 0) {
            return list;
        }
        for (int i = 0; i < nums.size(); i++) {
            int j = 0;
            for (j = i + 1; j < nums.size(); j++) {
                if (nums.get(j) > nums.get(i)) {
                    break;
                }
            }
            if (j < nums.size()) {
                list.add(j);
            } else {
                list.add(-1);
            }
        }
        return list;
    }

    public ArrayList<Integer> findNext(ArrayList<Integer> nums) {
        int[] arr = new int[nums.size()];
        for (int i = 0; i < nums.size(); i++) {
            arr[i] = nums.get(i);
        }
        int[] rArr = nextGreaterElements(arr);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < rArr.length; i++) {
            res.add(rArr[i]);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<Integer>();
        for (int i = 0; i < n - 1; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                ret[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return ret;
    }
}
