package Collections;

import java.util.Arrays;
import java.util.Collection;

public class ArrayList<T> {
    private static final int INITIAL_SIZE = 10;
    private static final float EXPANSION_COEFFICIENT = 1.5f;
    private Object[] data;
    private int size = 0;

    public ArrayList() {
        data = new Object[INITIAL_SIZE];
    }

    public ArrayList(int size) {
        data = new Object[size];
        this.size = size;
    }

    public ArrayList(Collection<T> collection) {
        data = new Object[collection.size()];
        for (T element : collection) {
            add(element);
        }
    }

    public int size() {
        return size;
    }

    public void add(T item) {
        if (size == data.length) {
            data = extensionArray(size, data);
        }
        size++;
        data[size] = item;
    }


    public T get(int index) {
        return (T) data[index];
    }

    public void remove(int index) {
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        data[--size] = null;
    }

    public void addAll(Collection<T> collection) {
        if (!collection.isEmpty()) {
            for (T element : collection) {
                this.add(element);
            }
        }
    }

    private Object[] extensionArray(int size, Object[] data) {
        return Arrays.copyOf(data, (int) Math.ceil(size * EXPANSION_COEFFICIENT));
    }
}