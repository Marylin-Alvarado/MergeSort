/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.listas;

import javax.xml.bind.annotation.XmlAnyElement;

/**
 *
 * @author Marylin
 */
public class NodoLista <E>{
    
    private E info;
    private NodoLista <E> siguiente;

    public NodoLista() {
        this.info = null;
        this.siguiente = null;
    }
    
    public NodoLista(E info, NodoLista<E> siguiente) {
        this.info = info;
        this.siguiente = siguiente;
    }
    @XmlAnyElement(lax = true)
    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }
    
    public NodoLista<E> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<E> siguiente) {
        this.siguiente = siguiente;
    }
    
}
