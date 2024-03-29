package common;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerUtil  {
    public static List<NestedInteger> buildNestedIntegerList(Object[] arr) {
        List<NestedInteger> result = new ArrayList<>();
        for (Object a : arr) {
            result.add(new NestedIntegerImpl(a));
        }
        return result;
    }
}
