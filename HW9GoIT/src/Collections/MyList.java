package Collections;

public interface MyList<T> extends MyCollection<T>{
    void add(T value);
    public void add(int index,T value);
    T remove(int index);
    T get(int index);
}