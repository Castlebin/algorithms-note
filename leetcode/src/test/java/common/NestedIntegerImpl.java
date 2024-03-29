package common;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {
    private Integer num;
    private List<NestedInteger> nested;

    public NestedIntegerImpl(Object data) {
        Class<?> clazz = data.getClass();
        if (clazz.isPrimitive()) {
            num = (int) data;
        } else if (clazz.isArray()) {
            nested = new ArrayList<>();
            for (Object d : (Object[]) data) {
                nested.add(new NestedIntegerImpl(d));
            }
        } else {
            throw new IllegalArgumentException("数据必须是整数或者数组类型！");
        }
    }

    @Override
    public boolean isInteger() {
        return num != null;
    }

    @Override
    public Integer getInteger() {
        return num;
    }

    @Override
    public List<NestedInteger> getList() {
        return nested;
    }
}
