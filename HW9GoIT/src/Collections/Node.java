package Collections;

public class Node <T>{
    private final T element;
    private Node<T> prev;
    private Node<T> next;
    public Node(T element){
        this.element = element;
    }

    public T getElement(){
        return element;
    }

    public Node<T> getPrev(){
        return prev;
    }
    public Node<T> getNext(){
        return next;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}