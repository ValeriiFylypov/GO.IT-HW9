package Collections;

public class MyStack <T> implements MyQueueInterface<T>{
    private int size;
    private MyLinkedList<T> list;

    public MyStack(){
        list = new MyLinkedList<>();
    }

    public void push(T element){
        list.add(element);
        size = list.size();
    }

    public T remove(int index){
        T element =  list.remove(index);
        size = list.size();
        return element;
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

    public T pop(){
        T element = list.remove(list.size()-1);
        size = list.size();
        return element;
    }

    @Override
    public String toString(){
        return list.toString();
    }
}

class StackTest{
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        //  Adding items to the stack
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        System.out.println("queue after creating and adding 5 elements:\n"
                + stack + ". Size = " + stack.size());
        System.out.println();

        //take elements from stack
        System.out.println("We take the first element from stack: ");
        System.out.println("stack.peek() = " + stack.peek());
        System.out.println();

        //remove items from the stack using the pop() method
        System.out.println("Remove items from the stack using the po method pop()");
        System.out.println("you removed - " + stack.pop());
        System.out.println("you removed - " + stack.pop());
        System.out.println("stack after deleting items:\n"
                + stack + ". Size = " + stack.size());
        System.out.println();

        //remove items from the stack using the remove method
        System.out.println("Remove items from the stack using the remove methode()");
        System.out.println("you removed - " + stack.remove(1));
        System.out.println("stack after deleting items:\n"
                + stack + ". Size = " + stack.size());
        System.out.println();

        //clean up the stack
        stack.clear();
        System.out.println("Clearing the stack");
        System.out.println("stack after cleaning:\n"
                + stack + ". Size = " + stack.size());
        System.out.println();

    }
}