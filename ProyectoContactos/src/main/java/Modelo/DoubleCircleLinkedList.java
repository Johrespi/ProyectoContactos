/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Raul Leon
 */
public class DoubleCircleLinkedList<E> implements List<E> {

    private CircularNodeList<E> last;
    private int size;

    public int getSize() {
        return size;
    }

    @Override
    public CircularNodeList<E> getByIndex(int index) {
        int contador = 0;
        for (CircularNodeList<E> nodo = last.getSig(); nodo != null; nodo = nodo.getSig()) {
            if (contador == index) {
                return nodo;
            }
            contador++;
        }
        throw new IndexOutOfBoundsException("Indice fuera de rango");
    }

    @Override
    public boolean add(E element, int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addFirst(E element) {
        if (!isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addLast(E element) {
        if (element == null) {
            return false;
        } else if (this.isEmpty()) {
            CircularNodeList nuevo = new CircularNodeList(element);
            last = nuevo;
            nuevo.setSig(nuevo);
            nuevo.setPrev(nuevo);
            size++;
            return true;
        } else if (!this.isEmpty()) {
            CircularNodeList nuevo = new CircularNodeList(element);
            last.getSig().setPrev(nuevo);
            nuevo.setSig(last.getSig());
            last.setSig(nuevo);
            nuevo.setPrev(last);
            last = nuevo;
            size++;
            return true;
        }
        return false;
    }
    
    public CircularNodeList<E> getPrev(CircularNodeList<E> nodo) {
        if (this.isEmpty() || nodo == null) {
            return null;
        }
        CircularNodeList<E> p;
        p = this.last;
        do {
            if (p.getSig() == nodo) {
                return p;

            }
            p = p.getSig();

        } while (p != this.last);
        return null;

    }

    public boolean isEmpty() {
        return this.last == null;
    }

    public CircularNodeList<E> getSig(CircularNodeList<E> nodo) {
        if (this.isEmpty() || nodo == null) {
            return null;
        }
        CircularNodeList<E> p;
        p = this.last;
        do {
            if (p.getPrev() == nodo) {
                return p;
            }
            p = p.getSig();

        } while (p != this.last);
        return null;

    }

    @Override
    public boolean delete(E contentComp) {
        //Si el contador sobrepasa el size de la lista, significa que ya iteró por todos los elementos y no encontro el que 
        //quiere eliminar
        int contador = 0;
        CircularNodeList<E> nodoViajero = last.getSig();
        while (nodoViajero.getContent() != contentComp && contador < size) {
            nodoViajero = nodoViajero.getSig();
            contador++;
        }

        if (contador < size) {
            CircularNodeList<E> nodoEliminar = nodoViajero;

            CircularNodeList<E> nodoAnterior = nodoViajero.getPrev();
            CircularNodeList<E> nodoPosterior = nodoViajero.getSig();

            nodoAnterior.setSig(nodoPosterior);
            nodoPosterior.setPrev(nodoAnterior);

            if (nodoEliminar.equals(last)) {
                last = nodoAnterior;
            }
            size--;
            return true;
        }
        return false;
    }

    public boolean deleteByIndex(int index) {
        int contador = 0;

        for (CircularNodeList<E> nodoViajero = last.getSig(); contador != index; nodoViajero = nodoViajero.getSig()) {
            System.out.println("Contador acumulandose:");
            System.out.println(contador);
            if (contador +1== index) {
                
                CircularNodeList<E> nodoEliminar = nodoViajero;

                CircularNodeList<E> nodoAnterior = nodoViajero.getPrev();
                CircularNodeList<E> nodoPosterior = nodoViajero.getSig();

                nodoAnterior.setSig(nodoPosterior);
                nodoPosterior.setPrev(nodoAnterior);
                
                //El nodo a eliminar no tendrá nada apuntandolo por lo que se lo llevará el garbage collector;
                if (nodoEliminar.equals(last)) {
                    last = nodoAnterior;
                }
                size--;
                return true;
            }
            contador++;
        }

        return false;

    }

}