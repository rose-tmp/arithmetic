package writtenExam.Thoughtworks;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZwZ
 * @date: 2022-08-26 16:12
 * @desc:
 */
public class Main3 {
    public static List<String> removeBackwards(List<String> array, int index, int howManyToRemove) {
        int start = 0;
        if (index - howManyToRemove >= 0) {
            start = index - howManyToRemove + 1;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            if (i >= start && i < start + howManyToRemove) {
                continue;
            }
            res.add(array.get(i));
        }
        return res;
    }
}
