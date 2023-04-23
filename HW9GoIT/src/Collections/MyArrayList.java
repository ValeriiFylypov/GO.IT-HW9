package Collections;
import java.util.StringJoiner;

public class MyArrayList <T> implements MyList<T>{
    private static final int DEFAULT_CAPACITY = 10;//the default size of the array

    private Object[] array;//an array on the basis of which MyArrayList is organized
    private int size;//the size of MyArrayList

    public MyArrayList (){
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /*
    method for adding items to the end of MyArrayList
     */
    @Override
    public void add(T value){
        isSizeOk();/*the method will check if the size is sufficient to add the element,
         if not, it will increase the size of the array field, and then add the element
        */
        array[size++] = value;
    }

    /*
method for adding an element to MyArrayList by index, all subsequent elements with
    whose index is equal to the insertion index will be shifted 1 to the right
     */
    public void add(int index,T value){
        if(this.size < index                //throw an exception if the index is not valid
                || index < 0){
            throw new IndexOutOfBoundsException("Index " + index +
                    " out of bounds for length " + size());
        }
        isSizeOk();//check if the array size is sufficient
        Object [] newArray = new Object[array.length];
        System.arraycopy(array,0,newArray,0, index);//copy items to the index
        newArray[index] = value;//присвоюємо значення за індексом
        System.arraycopy(array,index,newArray,index+1, array.length-index-1);//copy the remaining elements
        array = newArray;
        this.size++;
    }

    /*
    a service method that checks the size of the array, if necessary, increases it by 1.5 times
     */
    private void isSizeOk(){
        if (size == array.length -1){
            System.out.println("size = " + array.length);
            Object [] newArray = new Object[(int)(array.length*1.5)];
            System.arraycopy(array,0,newArray,0, array.length);
            array = newArray;
            System.out.println("size = " + array.length);
        }
    }

    /*
    service method that checks the index for insertion/deletion in case of invalidity, throws an exception
     */
    private void isIndexOk(int index){
        if(this.size <= index
                || index < 0){
            throw new IndexOutOfBoundsException("Index " + index +
                    " out of bounds for length " + size());
        }
    }

    /*
    The method for deleting an item by index will return the item that was deleted
         */
    @Override
    public T remove (int index){
        isIndexOk(index);// в разі невалідності індекса викинеться виключення
        T toRemove = (T)array[index];
        Object [] withoutToRemove = new Object[array.length];
        System.arraycopy(array,0,withoutToRemove,0, index);
        System.arraycopy(array,index+1,withoutToRemove,index, array.length-index-1);
        array = withoutToRemove;
        this.size--;

        return toRemove;
    }

    /*
    the method that deletes
     an element by value will return true if the element existed in the email and was deleted and false if not
     */
    public boolean remove (T value){
        boolean isT = false;
        int indexT = 0;
        for (int i = 0; i < size(); i++){
            if (array[i].equals(value)){
                isT = true;
                break;
            }
            indexT++;
        }
        if(isT){
            remove(indexT);
        }

        return isT;
    }

    /*
    return the size of the collection
     */
    @Override
    public int size(){
        return size;
    }

    /*
    will return the element by index
     */
    @Override
    public T get(int index){
        isIndexOk(index);
        return (T)array[index];
    }

    /*
    clear the collection
     */
    @Override
    public void clear(){
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        int start = 0;
        StringJoiner sj = new StringJoiner(", ");
        while (start < size){
            sj.add("" + array[start++]);
        }
        return "[" + sj + "]";
    }
}

class MyArrayListTest{
    public static  void main (String [] args){
        MyArrayList <String> list = new MyArrayList<>();
        // Add items to the list
        list.add("Java");
        list.add("is the");
        list.add("best");
        list.add("programming");
        list.add("language");
        System.out.println("List after creating and adding 5 elements:\n"
                + list + ". Size = " + list.size());
        System.out.println();


        System.out.println("We take elements from the sheet: ");
        for (int i = 0; i < list.size(); i++){
            System.out.println("list.get(" + i +") = " + list.get(i));
        }
        System.out.println();


        System.out.println("Remove items from the list");
        System.out.println("you removed - " + list.remove(3));
        System.out.println("you removed - " + list.remove(list.size() - 1));
        System.out.println("you removed - " + list.remove(0));
        System.out.println("Worksheet after deleting elements:\n"
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
        System.out.println("   Clean the list");
        System.out.println("List after cleaning:\n"
                + list + ". Size = " + list.size());
        System.out.println();

    }
}