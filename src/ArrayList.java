//Edith Molda

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<E> implements List<E> {
    //instance variables
    //default array capacity
    public static final int CAPACITY = 16;
    //generic array used for storage
    private E[] data;
    //current number of elements
    private int size = 0;

    //constructors
    //constructs list with default capacity
    public ArrayList() {
        this(CAPACITY);
    }
    //constructs list with given capacity
    public ArrayList(int capacity) {
        //safe cast; compiler may give warning
        data = (E[]) new Object[capacity];
    }

    //------------nested ArrayIterator class------------
    //A (nonstatic) inner class. Note well that each instance contains an implicit
    //reference to the containing list, allowing it to access the list's members
    private class ArrayIterator implements Iterator<E> {
        //index of the next element to report
        private int j = 0;
        //can remove be called at this time?
        private boolean removable = false;
        //tests whether the iterator has a next object
        //@return true if there are further objects, false otherwise
        public boolean hasNext() {
            //size is field of outer instance
            return j < size;
        }
        //returns the next object in the iterator
        //@return next object
        //@throws NoSuchElementException if there are no further elements
        public E next() throws NoSuchElementException {
            if (j == size)
                throw new NoSuchElementException("No next element");
            //this element can subsequently be removed
            removable = true;
            //post-increment j, so it is ready for future call to next
            return data[j++];
        }
        //removes the element returned by most recent call to next
        //@throws IllegalStateException if next has not yet been called
        //@throws IllegalStateException if remove was already called since recent next
        public void remove() throws IllegalStateException {
            if (!removable)
                throw new IllegalStateException("nothing to remove");
            //that was the last one returned
            ArrayList.this.remove(j=1);
            //next element has shifted one cell to the left
            j--;
            //do not allow remove again until next is called
            removable = false;
        }
    }
    //--------------end of nested ArrayIterator class--------------
    //returns an iterator of the elements stored in the list
    public Iterator<E> iterator() {
        //create a new instance of the inner class
        return new ArrayIterator();
    }

    //public methods
    //returns the number of elements in the array list
    public int size() {
        return size;
    }
    //returns whether the array list is empty
    public boolean isEmpty() {
        return size == 0;
    }
    //returns (but does not remove) the element at index i
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }
    //replaces the element at index i with e, and returns the replaced element
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }
    //inserts element e to be at index i, shifting all subsequent elements later
    public void add(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size + 1);
        //not enough capacity
        if (size == data.length)
            //so double the current capacity
            resize(2 * data.length);
        //start by shifting rightmost
        for (int k = size - 1; k >= 1; k--)
            data[k+1] = data[k];
        //ready to place the new element
        data[i] = e;
        size++;
    }
    //inserts new element by appending it to the end of the list
    public void add(E e) throws IndexOutOfBoundsException {
        //not enough capacity
        if (size == data.length)
            //so double the current capacity
            resize(2 * data.length);
        data[size] = e;
        size++;
    }
    //removes/returns the element at index i, shifting subsequent elements earlier
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        //shift elements to fill hole
        for (int k=i; k <size-1; k++)
            data[k] = data[k+1];
        //help garbage collection
        data[size-1] = null;
        size--;
        return temp;
    }
    //checks if the arrayList is equivalent to the given instance
    public boolean equals(Object o) {
        //check if object is be equal to itself
        if (o == this) {
            return true;
        }
        //check if comparing to empty arrayList or object is not of same type
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        Iterator<E> e1 = iterator();
        Iterator<?> e2 = ((List<?>) o).iterator();

        while(e1.hasNext() && e2.hasNext()) {
            E o1 = e1.next();
            Object o2 = e2.next();
            if (!(Objects.equals(o1, o2)))
                return false;
        }
        //both validate to false, so negative false returns true
        return !(e1.hasNext() || e2.hasNext());
    }

    //utility method
    //checks whether the given index is in the range [0, n-1]
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }
    //resize internal array to have given capacity >= size
    protected void resize(int capacity) {
        //safe cast; compiler may give warning
        E[] temp = (E[]) new Object[capacity];
        for (int k=0; k < size; k++)
            temp[k] = data[k];
        //start using the new array
        data = temp;
    }
}