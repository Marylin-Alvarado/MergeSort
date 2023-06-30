/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.listas.Excepciones;

/**
 *
 * @author Marylin
 */
public class ListaVaciaException  extends Exception{

    public ListaVaciaException() {
        super("Lista vacía");
    }

    public ListaVaciaException(String mensaje) {
        super(mensaje);
    }

}
