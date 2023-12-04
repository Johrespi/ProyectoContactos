package Modelo;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class DoubleCircleLinkedLists<E> implements Iterable<E>, Serializable {
    private Node<E> last;
    private int effective;
    private static final long serialVersionUID = 58743299253201266L;
    
    public void setLast(int index){
        if(index>size()||index<0||isEmpty()){
            throw new NoSuchElementException();
        }
        Node<E> nodo=last.getNext();
        int cont=0;
        do{
            if(index==cont){
                last=nodo;
                break;
            }
            nodo=nodo.getNext();
            cont++;
        }while(nodo!=last.getNext());
    }
    
    public void clear(){
        last=null;
        effective=0;
    }
        
    public void addAll(PriorityQueue<E> cola){
        while(!cola.isEmpty())
            this.addFirst(cola.poll());
    }
    
    public boolean addFirst(E element) {
        Node<E> nodo=new Node<>(element);
        if(element==null){
            return false;
        }else if(size()==0){
            last=nodo;
            last.setNext(last);
            last.setPrevious(last);
        }else{
            nodo.setNext(last.getNext());
            last.getNext().setPrevious(nodo);
            last.setNext(nodo);
            last.getNext().setPrevious(last);
        }
        effective++;
        return true;
    }


    public boolean addLast(E element) {
        Node<E> nodo=new Node<>(element);
        if(element==null){
            return false;
        }else if(size()==0){
            last=nodo;
            last.setNext(last);
            last.setPrevious(last);
        }else{
            nodo.setNext(last.getNext());
            last.getNext().setPrevious(nodo);
            last.setNext(nodo);
            nodo.setPrevious(last);
            last=nodo;
        }
        effective++;
        return true;
    }

    public boolean removeFirst() {
        if(isEmpty()){
            return false;
        }
        last.getNext().getNext().setPrevious(last);
        last.getNext().setPrevious(null);
        last.setNext(last.getNext().getNext());
        effective--;
        return true;
    }

    public boolean removeLast() {
        if(isEmpty()){
            return false;
        }
        int buf=0;
        for(Node<E> n=last.getNext();n!=last;n=n.getNext()){
            if(buf==size()-2){
                n.setNext(last.getNext());
                last.getNext().setPrevious(n);
                last.setNext(null);
                last.setPrevious(null);
                last=n;
            }
            buf++;
        }
        effective--;
        return true;
    }

    public E getFirst() {
        return last.getNext().getContent();
    }

    public E getLast() {
        return last.getContent();
    }

    public boolean insert(int index, E element) {
        if(index>=size()||index<0){
            return false;
        }else if(index==0){
            addFirst(element);
        }else if(index==size()-1){
            addLast(element);
        }else{
            Node<E> nodo = new Node<>(element);
            int buf=0;
            for(Node<E>n=last.getNext();n!=last;n=n.getNext()){
                if(buf==index-1){
                    nodo.setNext(n.getNext());
                    n.getNext().setPrevious(nodo);
                    n.setNext(nodo);
                    nodo.setPrevious(n);
                }
                buf++;
            }
            effective++;
        }
        return true;
    }

    public boolean contains(E element) {
        if(element==null||isEmpty()){
            return false;
        }
        Node<E> nodo=last.getNext();
        do{
            if(element==nodo.getContent()){
                return true;
            }
            nodo=nodo.getNext();
        }while(nodo!=last.getNext());
        return false;
    }

    public E get(int index) {
        E value=null;
        if(index>size()||index<0||isEmpty()){
            return value;
        }
        Node<E> nodo=last.getNext();
        int cont=0;
        do{
            if(index==cont){
                value=nodo.getContent();
                break;
            }
            nodo=nodo.getNext();
            cont++;
        }while(nodo!=last.getNext());
        return value;
    }

    public int indexOf(E element) {
        int value=-1;
        if(isEmpty()|| element==null){
            return value;
        }
        Node<E> nodo=last.getNext();
        int cont=0;
        do{
            if(element==nodo.getContent()){
                value=cont;
                break;
            }
            nodo=nodo.getNext();
            cont++;
        }while(nodo!=last.getNext());
        return value;
    }

    public boolean isEmpty() {
        return effective==0;
    }
    
    public E remove(int index) {
        E value=null;
        if(index>=size()||index<0||isEmpty()){
            return value;
        }else if(index==0){
            value=last.getNext().getContent();
            removeFirst();
            return value;
        }else if(index==size()-1){
            value=last.getContent();
            removeLast();
            return value;
        }else{
            int cont=0;
            Node<E> n=last.getNext();
            do{
                if(cont==index-1){
                    n.getNext().getNext().setPrevious(n);
                    n.getNext().setPrevious(null);
                    n.setNext(n.getNext().getNext());
                    effective--;
                    return value;
                }
                n=n.getNext();
                cont++;
            }while(n!=last.getNext());
        }
        return null;
    }

    public boolean remove(E element, Comparator<E> cmp) {
        boolean value=false;
        Node<E> nodo=last.getNext();
        do{
            if(cmp.compare(element, nodo.getContent())==0){
                value=true;
                nodo.getPrevious().setNext(nodo.getNext());
                nodo.getNext().setPrevious(nodo.getPrevious());
                nodo.setNext(null);
                nodo.setPrevious(null);
                effective--;
                return value;
            }
            nodo=nodo.getNext();
        }while(nodo!=last.getNext());
        return value;
    }

    
    public E set(int index, E element) {
        E value = null;
        if(index >= size()||index<0||element == null){
            return value;
        }
        Node<E> nodo = last.getNext();
        int cont=0;
        do{
            if(index == cont){
                value = nodo.getContent();
                nodo.setContent(element);
                return value;
            }
            cont++;
            nodo=nodo.getNext();
        }while(nodo!=last.getNext());
        return value;
    }

    
    public int size() {
        return effective;
    }
    
    public ListIterator<E> listIterator() {
        return new ListIterator<E>() {
            Node<E> nodo = last != null ? last.getNext() : null;
            Node<E> lastReturned = null;

            @Override
            public boolean hasNext() {
                return size() > 0 && nodo != null;
            }

             @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = nodo.getContent();
                lastReturned = nodo; 
                nodo = nodo.getNext();
                return value;
            }

            @Override
            public boolean hasPrevious() {
                return size() > 0 && nodo != null;
            }

            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                if (lastReturned == null) {
                    lastReturned = last; 
                } else {
                    lastReturned = lastReturned.getPrevious();
                }
                nodo = lastReturned;
                return nodo.getContent();
            }

            @Override
            public int nextIndex() {
                // Implementa si es necesario
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public int previousIndex() {
                // Implementa si es necesario
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void remove() {
                // Implementa si es necesario
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void set(E e) {
                // Implementa si es necesario
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public void add(E e) {
                // Implementa si es necesario
                throw new UnsupportedOperationException("Not supported yet.");
            }
            /*    
            @Override
            public String toString() {
                return "{" + "nodo=" + nodo + ", lastReturned=" + lastReturned + '}';
            }*/
            
            
        };
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> nodoN = isEmpty() ? null : last.getPrevious();
            Node<E> currentNode = null;

            @Override
            public boolean hasNext() {
                if (nodoN == null) {
                    return false;
                }
                return currentNode == null || currentNode.getNext() != last.getNext();
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (currentNode == null) {
                    currentNode = last.getNext();
                } else {
                    currentNode = currentNode.getNext();
                }
                return currentNode.getContent();
            }
        };
    }

        
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("[");
        switch(size()){
            case 0:
                s.append("]");
                return s.toString();
            case 1:
                s.append(last.getNext().getContent().toString()).append("]");
                return s.toString();
            default: 
                Node<E> nodo = last.getNext();
                do{
                    if(nodo == last){
                        s.append(nodo.getContent().toString()).append("]");
                    }else{
                        s.append(nodo.getContent().toString()).append(",");
                    }
                    nodo = nodo.getNext();
                }while(nodo != last.getNext());
                return s.toString();
        }
    }
    
    class Node<E> implements Serializable {
        private E content;
        private Node<E> previous;
        private Node<E> next;
        private static final long serialVersionUID = 58743201266L;

        public Node(E content) {
            this.content = content;
            this.next = null;
            this.previous = null;
        }

        public E getContent() {
            return content;
        }

        public void setContent(E content) {
            this.content = content;
        }

        public Node<E> getPrevious() {
            return previous;
        }

        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" + "content=" + content + '}';
        }                    
    }
}
