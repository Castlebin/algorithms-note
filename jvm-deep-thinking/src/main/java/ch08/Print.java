package ch08;

@FunctionalInterface
public interface Print<T> {
    void print(T x);
}
