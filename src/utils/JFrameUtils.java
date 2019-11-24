/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import views.MainView;

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

    public static void visibilidade(MainView view, String painel, String botao) {

        Component[] paineis = view.getPnPrincipal().getComponents();
        Component[] menu = view.getPnMenu().getComponents();

        for (Component p : paineis) {
            p.setVisible(false);
        }
        for (Component m : menu) {
            m.setEnabled(true);
        }

        for (Component p : paineis) {
            if ((p.getName().equals(painel))) {
                p.setVisible(true);
            }
        }
        for (Component m : menu) {
            if ((m.getName().equals(botao))) {
                m.setEnabled(false);
            }
        }
    }
}
