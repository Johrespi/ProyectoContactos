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

    private int capacidad = 100;
    private E[] elements = null;
    private int n = 0;

    public ArrayList() {
        this.elements = (E[]) (new Object[capacidad]);
        this.n = 0;
    }

    public void addCapacity() {
        E[] tmp = (E[]) new Object[capacidad * 2];
        for (int i = 0; i < capacidad; i++) {
            tmp[i] = elements[i];
        }
        elements = tmp;
        capacidad = capacidad * 2;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void clear() {
        n = 0;
    }

    @Override
    public void add(int index, E element) {
        if (element == null) {
            throw new NullPointerException("El elemento enviado es null.");
        } else if (index < 0 || index > this.n) {
            throw new IndexOutOfBoundsException();
        } else if (isEmpty()) {
            elements[n++] = element;
        } else if (capacidad == n) {
            addCapacity();
        }
        n++;
        for (int i = n; i > index; i--) {
            elements[i] = elements[i - 1];

        }
        elements[index] = element;
    }

    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[n++] = element;
            return true;
        } else if (capacidad == n) {
            addCapacity();
        }
        for (int i = n - 1; i >= 0; i--) {
            elements[i + 1] = elements[i];
        }
        elements[0] = element;
        n++;
        return true;
    }

    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (isEmpty()) {
            elements[n++] = element;
            return true;
        } else if (capacidad == n) {
            addCapacity();
        }
        int index = n;
        elements[index] = element;
        n++;
        return true;
    }

    @Override
    public E remove(int index) {
        E elementToRemove = null;
        if (this.isEmpty() || index >= this.n || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            elementToRemove = elements[index];
            for (int i = index; i < this.n - 1; i++) {
                elements[i] = elements[i + 1];
            }
            this.n--;
        }
        return elementToRemove;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(this.n - 1);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= this.n) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= this.n) {
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
            for (int i = 0; i < n - 1; i++) {
                result += elements[i].toString() + ", ";
            }
            result += elements[n - 1].toString() + "]";
        }
        return result;
    }

    public boolean reverse() {
        if (this.isEmpty()) {
            return false;
        }

        ArrayList<E> listaRevertida = new ArrayList<E>();

        for (int i = n - 1; i > -1; i--) {
            listaRevertida.addLast(this.elements[i]);
        }

        for (int i = 0; i < n; i++) {
            this.elements[i] = listaRevertida.get(i);
        }

        return true;
    }
    
    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < n; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(E element, Comparator<E> cmp) {
        for (int i = 0; i < n; i++) {
            if (cmp.compare(element, elements[i]) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < n; i++) {
            if (o == null ? elements[i] == null : o.equals(elements[i])) {
                return i;
            }
        }
        return -999999999; 
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
                return currentIndex < n;
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
