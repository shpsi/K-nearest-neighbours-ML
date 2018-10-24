package fr.enssat.caronnantel.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

    public static <T> T mostCommonItem(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue()) max = e;
        }

        return max.getKey();
    }
}
