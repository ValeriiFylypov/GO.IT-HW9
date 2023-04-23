package Collections;

import java.util.Objects;
import java.util.StringJoiner;

public class MyHashMap <K, V> implements MyMap<K, V>{

    private  Node<K,V> [] array;//Array in which the lists of map items will be stored
    private int size;//A variable that stores the size of the map
    private static final float LOAD_FACTOR = 0.75f;
    private int limit;

    private int length;

    public MyHashMap(){
        length = 16;
        array = new Node[length];
        limit = (int)(length * 8 * LOAD_FACTOR);
    }

    /*
A nested class for storing map element values and organizing their storage as a single-linked list
     */
    static class Node<K,V>{
        private final int hash;
        private final K key;
        private V value;
        private Node<K,V> next;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            hash = Objects.hashCode(key);
        }
    }

    private void resize(){
        if(size < limit){
            return;
        }
        length = length*2;
        limit = (int) (length * 8 * LOAD_FACTOR);
        Node <K,V> []arrayCopy = array;
        array = new Node[length];
        for (Node<K,V> node : arrayCopy){
            Node<K, V> forCopy = node;
            while (forCopy != null){
                Node<K, V> add = new Node<>(forCopy.key,forCopy.value);
                addNode(add);
                forCopy = forCopy.next;
            }

        }
    }

    /*
   A service method used to calculate the index of the array to which the element will be placed
     */
    private int calcIndex(K key){
        return Objects.hashCode(key) & length-1;
    }

    /*
A service method used to add a map item without checking for duplicate keys
     */
    private void addNode(Node<K,V> node){
        int index = calcIndex(node.key);
        if (array[index] == null){
            array[index] = node;
        }else {
            Node<K, V> current = array[index];
            while (current.next != null){
                current = current.next;
            }
            current.next = node;
        }

    }

    /*
    A service method used to search for a map item by key, if there is no
    with the corresponding key is not in the map, null will be returned
     */
    private Node <K,V> getNode(K key){
        int index = calcIndex(key);
        if (array[index] != null){
            Node <K,V> current = array[index];
            while (true){
                if(current.hash == Objects.hashCode(key) && key.equals(current.key)){
                    return current;
                }
                if (current.next == null) break;
                current = current.next;
            }
        }
        return null;
    }

    /*
    Method for adding items to the map
     */
    @Override
    public void put(K key, V value){
        resize();
        Node <K, V> newElement = new Node<>(key, value);

     /*
     node to check if there is an element with the same key in the map, refers to the map element,
        if it exists and null, if not
         */
        Node <K, V> checkNode = getNode(key);
        if(checkNode != null){//if an element with an identical key is already contained, overwrite the value
            checkNode.value = value;
            return;
        }
        addNode(newElement);
        size++;
    }

    /*
Method for removing items from the map
     */
    @Override
    public void remove(K key){
        int index = calcIndex(key);
        Node<K, V> beforeRemove = array[index];
        if(beforeRemove == null) {//check whether the array element is empty, which must contain the corresponding key
            return;
        }
        if (beforeRemove.key.equals(key)){//if the required item is the first in the list
            array[index] = beforeRemove.next;
            size--;
            return;
        }
        while (beforeRemove.next != null){//search for the element preceding the element with the corresponding key if the previous conditions are not met
            if (beforeRemove.next.key.equals(key)){
                beforeRemove.next = beforeRemove.next.next;
                size--;
                return;
            }
            beforeRemove = beforeRemove.next;
        }

    }

    /*
Method for cleaning the map
     */
    @Override
    public void clear(){
        length = 16;
        array = new Node[length];
        limit = (int)(length * 8 * LOAD_FACTOR);
        size = 0;
    }

    /*
    Method that returns the size of the map
     */
    @Override
    public int size(){
        return size;
    }

    /*
    Method for getting a value by key
     */
    @Override
    public V get(K key){
        Node <K, V> desired  = getNode(key);
        if (desired != null){
            return desired.value;
        }
        return null;
    }

    public String toString(){
        StringJoiner sj = new StringJoiner(", ");
        int index = 0;
        for (Node<K,V> node : array){
            sj.add("\n" + index++ + ")\n" );
            if (node == null) {
                continue;
            }
            Node <K, V> current = node;
            sj.add(current.key +" - " + current.value);
            while (current.next != null){
                current = current.next;
                sj.add(current.key +" - " + current.value);
            }
        }
        return "[" + sj + "]";
    }
}

class MyHashMapTest{
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        for (int i = 0; i < 220; i++){
            map.put(i, "a"+i);
        }

        map.put(96, "b");
        map.put(97, "b");
        map.put(98, "b");

        map.remove(97);
        System.out.println(map);
        System.out.println("map.size() = " + map.size());


    }
}