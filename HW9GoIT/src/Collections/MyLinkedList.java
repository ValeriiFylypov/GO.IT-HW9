package Collections;

import java.util.StringJoiner;

public class MyLinkedList<T> implements MyList<T>{
    private int size;//the size of the MyLinkedList
    Node <T> first;//node to save the initial element of MyLinkedList
    Node <T> last;//node for saving the end element MyLinkedList

    /*
  method for clearing MyLinkedList
     */
    @Override
    public void clear(){
        first = last = null;
        size = 0;
    }

    /*
method that returns a MyLinkedList dimension

     */
    @Override
    public int size(){
        return size;
    }

    /*
method that adds items to the end of MyLinkedList
     */
    @Override
    public void add(T element){
        Node <T> newNode = new Node<>(element);
        if(isEmpty()){
            first = newNode;
        }else {
            newNode.setPrev(last);
            last.setNext(newNode);
        }
        last = newNode;
        size++;
    }

    /*
method that adds items to the beginning of MyLinkedList
     */
    public void addFirst(T value){
        Node <T> newNode = new Node<>(value);
        if (isEmpty()){
            last = newNode;
        }else {
            newNode.setNext(first);
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    /*
a method that adds items to MyLinkedList by index, all subsequent ones are shifted to the right by 1
     */
    public void add(int index, T value) {
        if (index == size){//if the index is equal to the length, add to the end of MyLinkedList
            add(value);
            return;
        }
        throwException(index);//if the index is not valid, an exception will be thrown
        Node<T> newNode = new Node<>(value);
        Node<T> current = getNode(index);
        if(index == 0){//if the index is 0, add to the beginning of MyLinkedList
            addFirst(value);
            size--;//reduce the side because the addFirst method will increase it by 1 and the same will happen at the end of this method
        }else {// if the first 2 conditions did not work, add by index
            newNode.setPrev(current.getPrev());
            newNode.setNext(current);
            current.getPrev().setNext(newNode);
            current.setPrev(newNode);
        }

        size++;
    }

    /*
a method that removes an item from MyLinkedList by index will return the item
     */
    @Override
    public T remove(int index){
        throwException(index);//if the index is invalid, the checkout will be thrown
        Node<T> current = getNode(index);//take the current node by index
        T value = current.getElement();//save the value that will be returned from the method
        if(size == 1){//clear MyLinkedList, provided that it is a single element
            clear();
            return value;
        }

        if (current == first){//if this is the first element
            first = first.getNext();
            first.setPrev(null);
            size--;
            return value;
        }

        if(current == last){//if the last
            last = last.getPrev();
            last.setNext(null);
        } else {//an item in the middle
            current.getNext().setPrev(current.getPrev());
            current.getPrev().setNext(current.getNext());

        }

        size--;

        return value;
    }

    /*
method that returns an item from MyLinkedList by index
     */
    @Override
    public T get(int index){
        throwException(index);//if the index is invalid, the checkout will be thrown
        return getNode(index).getElement();//the index will find the node and return its element
    }

    /*
service method, checks if MyLinkedList is empty
     */
    private boolean isEmpty(){
        return size == 0;
    }


    /*
service method, checks the index for validity
     */
    private boolean isIndexOk(int index){
        return index >= 0 && index < size;
    }

    /*
service method, throws an exception if the index is invalid

     */
    private void throwException(int index){
        if(!isIndexOk(index)){
            throw new IndexOutOfBoundsException("Index " + index +
                    " out of bounds for length " + size());
        }
    }

    /*
    service method, returns a node by index
     */
    private Node<T> getNode(int index){
        Node <T> current = first;
        int start = 0;
        while (start < index){
            start++;
            current = current.getNext();
        }
        return current;
    }

    @Override
    public String toString() {
        if(isEmpty()) return "[]";
        Node<T> current = first;
        StringJoiner sj = new StringJoiner(", ");
        while (current != null){
            sj.add("" + current.getElement());
            current = current.getNext();

        }
        return "[" + sj + "]";
    }
}

class MyLinkedListTest{
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        //Add items to the list
        list.add("Java");
        list.add("is the");
        list.add("best");
        list.add("programming");
        list.add("language");
        System.out.println("Sheet after creating and adding 5 elements:\n"
                + list + ". Size = " + list.size());
        System.out.println();


        System.out.println("Take elements from the sheet: ");
        for (int i = 0; i < list.size(); i++){
            System.out.println("list.get(" + i +") = " + list.get(i));
        }
        System.out.println();


        System.out.println("Remove items from the list");
        System.out.println("you removed - " + list.remove(3));
        System.out.println("you removed - " + list.remove(list.size() - 1));
        System.out.println("you removed - " + list.remove(0));
        System.out.println("Sheet after deleting elements:\n"
                + list + ". Size = " + list.size());
        System.out.println();

        //trying to delete an element by an invalid index
        System.out.println("Trying to delete an element by invalid index - 2 with length 2:");
        try {
            list.remove(2);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        System.out.println();

        //clean the list
        list.clear();
        System.out.println("Clean the list");
        System.out.println("List after cleaning:\n"
                + list + ". Size = " + list.size());
        System.out.println();

    }
}