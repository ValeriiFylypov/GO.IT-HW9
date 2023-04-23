package Collections;

public class MyQueue <T> implements MyQueueInterface<T>{
    private int size;
    private MyLinkedList <T> list;

    public MyQueue(){
        list = new MyLinkedList<>();
    }


    public void add(T value){
        list.add(value);
        size = list.size();
    }

    @Override
    public void clear() {
        list.clear();
        size = list.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        return list.get(0);
    }

    public T poll(){
        T element = list.remove(0);
        size = list.size();
        return element;
    }

    @Override
    public String toString(){
        return list.toString();
    }
}

class MyQueueTest{
    public static void main(String[] args) {
        MyQueue <Integer> queue = new MyQueue<>();
        // Adding items to the queue
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        System.out.println("queue after creating and adding 5 elements:\n"
                + queue + ". Size = " + queue.size());
        System.out.println();

        //take items from the queue
        System.out.println("Take the first element from the queue: ");
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println();

        //remove items from the queue
        System.out.println("Remove items from the queue");
        System.out.println("you removed - " + queue.poll());
        System.out.println("you removed - " + queue.poll());
        System.out.println("you removed - " + queue.poll());
        System.out.println("List after deleting elements:\n"
                + queue + ". Size = " + queue.size());
        System.out.println();


        //clear the queue
        queue.clear();
        System.out.println("Clearing the queue");
        System.out.println("List after cleaning:\n"
                + queue + ". Size = " + queue.size());
        System.out.println();

    }
}