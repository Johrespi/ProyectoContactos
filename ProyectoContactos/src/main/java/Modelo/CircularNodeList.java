/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Raul Leon
 * @param <E>
 */
public class CircularNodeList<E> implements Serializable{
    private E contenido;
    private CircularNodeList<E> sig;
    private CircularNodeList<E> anterior;

    
    public CircularNodeList (E elemento){
        this.contenido= elemento;
        
    }
    
    public CircularNodeList(){
        
    }
    
    public E getContent() {
        return contenido;
    }

    public CircularNodeList<E> getSig() {
        return sig;
    }

    public CircularNodeList<E> getPrev() {
        return anterior;
    }

    public void setContent(E content) {
        this.contenido = content;
    }

    public void setSig(CircularNodeList<E> next) {
        this.sig = next;
    }

    public void setPrev(CircularNodeList<E> previous) {
        this.anterior = previous;
    }
    
    
}

