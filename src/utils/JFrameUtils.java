/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import javax.swing.JTextField;

/**
 *
 * @author Gabriel
 */
public class JFrameUtils {
    
    public static void cleanTextField(Component[] components) {
        for (Component c : components) {
            if (c instanceof JTextField) {
                ((JTextField) c).setText("");
            }
        }
    }
    
    public static boolean checagem(Component[] components){
        for (Component c: components) {
            if (c instanceof JTextField) {
                System.out.println(c);
            }
        }
        
        return true;
    }
    
}
