package common;

import java.util.ArrayList;
import java.util.List;

public class NestedIntegerImpl implements NestedInteger {
    private Integer num;
    private List<NestedInteger> nested;

    public NestedIntegerImpl(Object data) {
        Class<?> clazz = data.getClass();
        if (data instanceof Integer) {
            num = (int) data;
        } else if (clazz.isArray()) {
            nested = new ArrayList<>();
            for (Object d : (int[]) data) {
                nested.add(new NestedIntegerImpl(d));
            }
        } else {
            throw new IllegalArgumentException("数据类型不合法");
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

    @Override
    public String toString() {
        if (this.isInteger()) {
            return this.getInteger().toString();
        }
        StringBuilder sb = new StringBuilder("[");
        for (NestedInteger ni : this.getList()) {
            sb.append(ni.toString() + ", ");
        }
        if (sb.charAt(sb.length() - 1) == ' ') {
            sb = new StringBuilder(sb.substring(0, sb.length() - 2));
        }
        sb.append("]");
        return sb.toString();
    }

}
