/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.utilidades;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.enums.Ordenacion;
import modelo.enums.TipoDato;

/**
 *
 * @author Marylin
 */
public class utilidades {
     public static JComboBox cargarComboOrdenacion(JComboBox combo){
        combo.removeAllItems();
        for(Ordenacion Metodo: Ordenacion.values()) {
            combo.addItem(Metodo);
        }
        return combo;
    }
    public static JComboBox cargarComboTipoDeDato(JComboBox combo){
        combo.removeAllItems();
        for(TipoDato Metodo: TipoDato.values()) {
            combo.addItem(Metodo);
        }
        return combo;
    }
    public static void permitirSoloNumTxt(JTextField txt){
        txt.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c =e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
        }
    });
    }
        public static void limitarTamanioTxt(JTextField txt,Integer tamanio){
            txt.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                if (txt.getText().length()>=tamanio) {
                    e.consume();
                    JOptionPane.showMessageDialog(txt, "Solo se permiten "+tamanio+" caracteres");
                }
        }
    });
    }
    
}
