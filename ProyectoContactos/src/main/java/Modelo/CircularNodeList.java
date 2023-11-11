/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Raul Leon
 */
public class CircularNodeList<E> {
    private E content;
    private CircularNodeList<E> sig;
    private CircularNodeList<E> prev;

    
    public CircularNodeList (E elemento){
        this.content= elemento;
        
    }
    
    public CircularNodeList(){
        
    }
    
    public E getContent() {
        return content;
    }

    public CircularNodeList<E> getSig() {
        return sig;
    }

    public CircularNodeList<E> getPrev() {
        return prev;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setSig(CircularNodeList<E> next) {
        this.sig = next;
    }

    public void setPrev(CircularNodeList<E> previous) {
        this.prev = previous;
    }
    
    
}

