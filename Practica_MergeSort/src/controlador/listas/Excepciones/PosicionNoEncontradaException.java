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
public class PosicionNoEncontradaException extends Exception{

    public PosicionNoEncontradaException(String mensaje) {
        super(mensaje);
    }

    public PosicionNoEncontradaException() {
        super("Posición fuera de límites");
    }

}
