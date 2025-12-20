package util;

import java.util.Objects;

public class Tuple<T, U, V> {
    private T first;
    private U second;
    private V third;

    public Tuple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getFirst() {
        return first;
    }
    
    public U getSecond() {
        return second;
    }

    public V getThird() {
        return third;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) obj;
        return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second) && Objects.equals(third, tuple.third);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

    
}
