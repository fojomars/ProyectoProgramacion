/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import javax.swing.JOptionPane;

/**
 *
 * @author christian and fred
 */
public class pedirDatos {
        public static int enteiro(String msg) {
        return Integer.parseInt(JOptionPane.showInputDialog(msg));
    }

    public static float decimal(String msg) {
        return Float.parseFloat(JOptionPane.showInputDialog(msg));
    }

    public static String pedirString(String msg) {
        return JOptionPane.showInputDialog(msg);
    }
}
