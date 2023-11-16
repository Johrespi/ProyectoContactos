/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author johann
 * @param <E>
 */
public class ArrayList<E> implements List<E>, Serializable {

    private int capacity = 100;
    private E[] elements = null;
    private int effectiveSize = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacity]);
        this.effectiveSize = 0;
    }

    public void addCapacity() {
        E[] tmp = (E[]) new Object[capacity * 2];
        for (int i = 0; i < capacity; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacity = capacity * 2;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize == 0;
    }

    @Override
    public void clear() {
        effectiveSize = 0;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException("Element provided is null.");
        } else if (index < 0 || index > this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        effectiveSize++;
        for (int i = effectiveSize; i > index; i--) {
            elements[i] = elements[i - 1];

        }
        elements[index] = element;
    }

    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        for (int i = effectiveSize - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        effectiveSize++;
        return true;
    }

    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[effectiveSize++] = element;
            return true;
        } else if (capacity == effectiveSize) {
            addCapacity();
        }
        int index = effectiveSize;
        elements[index] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public E remove(int index) {
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.effectiveSize || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.effectiveSize - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.effectiveSize--;
        }
        return elementToRemove;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(this.effectiveSize - 1);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.effectiveSize) {
            throw new IndexOutOfBoundsException();
        }
        E oldElement = elements[index];
        elements[index] = element;
        return oldElement;
    }

    @Override
    public String toString() {
        String result = "[";

        if (isEmpty()) {
            return result + "]";
        } else {
            for (int i = 0; i < effectiveSize - 1; i++) {
                result += elements[i].toString() + ", ";
            }
            result += elements[effectiveSize - 1].toString() + "]";
        }
        return result;
    }

    public boolean reverse() {
        if (this.isEmpty()) {
            return false;
        }

        ArrayList<E> aux = new ArrayList<E>();

        for (int i = effectiveSize - 1; i > -1; i--) {
            aux.addLast(this.elements[i]);
        }

        for (int i = 0; i < effectiveSize; i++) {
            this.elements[i] = aux.get(i);
        }

        return true;
    }

    public boolean addAll(List<E> l) {
        if (l == null) {
            return false;
        }
        int total_a_tener = effectiveSize + l.size();
        int añadir_capacity = total_a_tener / 100;
        if (total_a_tener > capacity) {
            for (int i = 0; i < (añadir_capacity + 1); i++) {
                addCapacity();
            }
        }
        for (int u = 0; u < l.size(); u++) {
            elements[effectiveSize] = l.get(u);
            effectiveSize++;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < effectiveSize; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(E element, Comparator<E> cmp) {
        for (int i = 0; i < effectiveSize; i++) {
            if (cmp.compare(element, elements[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < effectiveSize; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }
        return -999999999; // Elemento no existe
    }

    @Override
    public Iterator<E> iterator() {
        if (isEmpty()) {
            return null;
        }
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < effectiveSize;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];

            }

        };
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
