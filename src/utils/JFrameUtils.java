/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import presenters.MainPresenter;
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

    public static boolean checagemFuncionario(JTextField[] txts, JFormattedTextField cpf) {
        MainView view = MainPresenter.getView();
        //Check nome
        txts[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[0].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblNomeFuncionario().setForeground(Color.black);
                view.getLblNomeFuncionario().setText("Nome");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[0].getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!txts[0].getText().toLowerCase().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("O nome inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    txts[0].setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeFuncionario().setForeground(Color.red);
                    view.getLblNomeFuncionario().setText("Nome " + "(" + ex.getMessage() + ")");
                }
            }
        });

        txts[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[1].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(txts[1].getText());
                    if (!(matcher.matches() && !txts[1].getText().equals(""))) {
                        throw new Exception("O Email inserido está incorreto!");
                    }
                } catch (Exception ex) {

                    txts[1].setBorder(new LineBorder(Color.red, 1));
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        cpf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cpf.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (cpf.getText().contains(" ")) {

                        throw new Exception("CPF inserido é invalido!");
                    }
                } catch (Exception ex) {
                    cpf.setBorder(new LineBorder(Color.red, 1));
                    cpf.setValue("");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        return true;
    }

    public static boolean checagemCliente() {
        MainView view = MainPresenter.getView();
        
        //Check nome
        view.getTxtNomeCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNomeCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblNomeFuncionario().setForeground(Color.black);
                view.getLblNomeFuncionario().setText("Nome");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtNomeCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!view.getTxtNomeCliente().getText().toLowerCase().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("O nome inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    view.getTxtNomeCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeFuncionario().setForeground(Color.red);
                    view.getLblNomeFuncionario().setText("Nome " + "(" + ex.getMessage() + ")");
                }
            }
        });

        txts[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[0].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblNomeFuncionario().setForeground(Color.black);
                view.getLblNomeFuncionario().setText("Nome");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[0].getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!txts[0].getText().toLowerCase().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("O nome inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    txts[0].setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeFuncionario().setForeground(Color.red);
                    view.getLblNomeFuncionario().setText("Nome " + "(" + ex.getMessage() + ")");
                }
            }
        });

        cpf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cpf.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (cpf.getText().contains(" ")) {

                        throw new Exception("CPF inserido é invalido!");
                    }
                } catch (Exception ex) {
                    cpf.setBorder(new LineBorder(Color.red, 1));
                    cpf.setValue("");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        return true;
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
