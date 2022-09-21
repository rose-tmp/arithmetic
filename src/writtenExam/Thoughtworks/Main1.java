package writtenExam.Thoughtworks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-26 15:39
 * @desc:
 */
public class Main1 {
    public static List<Integer> swapAdjacent(List<Integer> array) {
        if (array.size() < 2) {
            return array;
        }
        List<Integer> res = new ArrayList<>();
        int index = 1;
        while (index < array.size()) {
            res.add(array.get(index));
            res.add(array.get(index - 1));
            index += 2;
        }
        if (array.size() % 2 == 1) {
            res.add(array.get(array.size() - 1));
        }
        return res;
    }
}
