//Edith Molda

import java.util.Iterator;

//a simplified version of the java.util.List interface
public interface List<E> extends Iterable{
    //returns the number of elements in this list
    int size();
    //returns whether the list is empty
    boolean isEmpty();
    //returns (but does not remove) the element at index i
    E get(int i) throws IndexOutOfBoundsException;
    //replaces the element at index i with e, and returns the replaced element
    E set(int i, E e) throws IndexOutOfBoundsException;
    //inserts element e to be at index i, shifting all subsequent elements later
    void add(int i, E e) throws IndexOutOfBoundsException;
    //removes/returns the element at index i, shifting subsequent elements earlier
    E remove(int i) throws IndexOutOfBoundsException;
    //returns an iterator of the elements stored in the list
    Iterator<E> iterator();
}