package writtenExam.Thoughtworks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-26 15:59
 * @desc:
 */
public class Main2 {
    public static List<Integer> sum_two(List<Integer> input_array) {
        // Write your code here

        if (input_array == null) {
            return new ArrayList<>();
        }
        if (input_array.size() < 3) {
            return new ArrayList<>(input_array);
        }
        return back(input_array);
    }

    public static List<Integer> back(List<Integer> input) {
        if (input.size() < 3) {
            return input;
        }
        List<Integer> res = new ArrayList<>();
        int i = 0, j = input.size() - 1;
        while (i <= j) {
            if (i == j) {
                res.add(input.get(i));
                break;
            }
            res.add(input.get(i) + input.get(j));
            i++;
            j--;
        }
        if (res.size() < 3) {
            return res;
        }
        return back(res);
    }
}
