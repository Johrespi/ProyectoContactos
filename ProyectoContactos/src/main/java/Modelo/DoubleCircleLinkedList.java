/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javafx.collections.ObservableList;

/**
 *
 * @author Raul Leon
 */
public class DoubleCircleLinkedList<E> implements Iterable<E>, Serializable {

    Node<E> primero, ultimo;
    int n = 0;

    public class Node<E> implements Serializable {

        E contenido;
        Node<E> sig, anterior;

        public Node(E contenido) {
            this.contenido = contenido;
            this.sig = sig;
            this.anterior = anterior;
        }
    }

    public Node<E> getLast() {
        return ultimo;
    }

    public DoubleCircleLinkedList() {
        primero = null;
        ultimo = null;
    }

    public boolean add(E e) {
        Node<E> nuevoNodo = new Node<>(e);
        if (isEmpty()) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
            nuevoNodo.sig = nuevoNodo;
            nuevoNodo.anterior = nuevoNodo;
        } else {
            nuevoNodo.anterior = ultimo;
            nuevoNodo.sig = primero;
            ultimo.sig = nuevoNodo;
            primero.anterior = nuevoNodo;
            ultimo = nuevoNodo;
        }
        n++;
        return true;
    }

    public void add(int index, E element) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        if (index == n) {
            add(element);
        } else {
            Node<E> nuevoNodo = new Node<>(element);
            Node<E> actual = primero;
            for (int i = 0; i < index; i++) {
                actual = actual.sig;
            }
            Node<E> anterior = actual.anterior;

            nuevoNodo.anterior = anterior;
            nuevoNodo.sig = actual;
            actual.anterior = nuevoNodo;
            anterior.sig = nuevoNodo;

            if (actual == primero) {
                primero = nuevoNodo;
            }
            n++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }
        return actual.contenido;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public E remove(int index) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("La lista está vacía");
        }
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> actual = primero;
        for (int i = 0; i < index; i++) {
            actual = actual.sig;
        }
        E eliminado = actual.contenido;
        Node<E> anterior = actual.anterior;
        Node<E> siguiente = actual.sig;

        if (n == 1) {
            primero = null;
            ultimo = null;
        } else if (actual == primero) {
            primero = siguiente;
            ultimo.sig = primero;
        } else if (actual == ultimo) {
            ultimo = anterior;
            primero.anterior = ultimo;
        } else {
            anterior.sig = siguiente;
            siguiente.anterior = anterior;
        }

        n--;
        return eliminado;
    }

    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder resultado = new StringBuilder("[");
        Node<E> current = primero;
        for (int i = 0; i < n; i++) {
            resultado.append(current.contenido);
            if (i < n - 1) {
                resultado.append(", ");
            }
            current = current.sig;
        }
        resultado.append("]");
        return resultado.toString();
    }

    public void iteracionCircular(ObservableList<String> observableList) {
        if (!isEmpty()) {
            Node<E> current = ultimo;
            do {
                observableList.add(current.contenido.toString());
                current = current.sig;
            } while (current != ultimo);
        }
    }

    public void iteracionCircular2() {
        Node<E> actual = this.primero;
        actual = this.ultimo;
        for (int i = 0; i < this.size(); i++) {
            System.out.print(actual.contenido + " ");
            actual = actual.anterior;
        }
        System.out.println();

    }

    public void moveToNext() {
        if (!isEmpty()) {
            ultimo = ultimo.sig;
        }
    }

    public void moveToPrevious() {
        if (!isEmpty()) {
            ultimo = ultimo.anterior;
        }
    }


    public int indexOf(E e) {
        if (e == null) {
            return -1;
        }
        if (isEmpty()) {
            return -1;
        }
        if (e == ultimo.contenido) {
            return size() - 1;
        }

        Node<E> node = primero;
        int index = 0;
        while (node != ultimo) {
            if (node.contenido == e) {
                return index;
            }
            index++;
            node = node.sig;
        }

        return -1;
    }
    

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> actual = primero;
            private int visitados = 0;

            @Override
            public boolean hasNext() {
                return visitados < size();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                E dato = actual.contenido;
                actual = actual.sig;
                visitados++;
                return dato;
            }
        };
    }
}
