/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.modeloTabla;

import controlador.listas.Excepciones.ListaVaciaException;
import controlador.listas.Excepciones.PosicionNoEncontradaException;
import controlador.listas.ListasEnlazadas;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Marylin
 */
public class ModeloTabla<E> extends AbstractTableModel {

    ListasEnlazadas lista = new ListasEnlazadas();

    public ListasEnlazadas getLista() {
        return lista;
    }

    public void setLista(ListasEnlazadas lista) {
        this.lista = lista;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return lista.getTamanio();
    }

    @Override
    public String getColumnName(int i) {

        switch (i) {
            case 0:
                return "Objetos Aleatorios";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int i, int i1) {
        try {
            E e = (E) lista.obtener(i);
            switch (i1) {
                case 0:
                    return (e != null) ? e : "NO DEFINIDO";
                default:
                    return null;
            }
        } catch (ListaVaciaException ex) {
            System.out.println(ex);
        } catch (PosicionNoEncontradaException ex) {
            System.out.println(ex);
        }
        return null;
    }

}
