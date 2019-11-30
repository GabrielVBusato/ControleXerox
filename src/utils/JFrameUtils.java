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
import model.dao.FuncionarioDao;
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
                    view.getLblNomeFuncionario().setText("Nome " + "(" + ex.getMessage() + " )");
                }
            }
        });

        txts[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[1].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblEmailFuncionario().setForeground(Color.black);
                view.getLblEmailFuncionario().setText("Email");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(txts[1].getText());
                    if (txts[1].getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!(matcher.matches())) {
                        throw new Exception("O Email inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    txts[1].setBorder(new LineBorder(Color.red, 1));
                    view.getLblEmailFuncionario().setForeground(Color.red);
                    view.getLblEmailFuncionario().setText("Email " + "( " + ex.getMessage() + " )");
                }
            }
        });

        cpf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cpf.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblCpfFuncionario().setForeground(Color.black);
                view.getLblCpfFuncionario().setText("CPF");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (cpf.getText().contains(" ")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (FuncionarioDao.cpfExists(cpf.getText())) {
                        throw new Exception("Cpf existente!");
                    }
                } catch (Exception ex) {
                    cpf.setBorder(new LineBorder(Color.red, 1));
                    if (!ex.getMessage().equals("Cpf existente!")) {
                        cpf.setValue("");
                    }
                    view.getLblCpfFuncionario().setForeground(Color.red);
                    view.getLblCpfFuncionario().setText("CPF " + "( " + ex.getMessage() + " )");
                }
            }
        });

        txts[4].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[4].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblTelefoneFuncionario().setForeground(Color.black);
                view.getLblTelefoneFuncionario().setText("Telefone");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[4].getText().toLowerCase().equals("")) {
                        throw new Exception("Obrigatório!");
                    }
                    if (!txts[4].getText().matches("^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")) {
                        throw new Exception("inválido!");
                    }
                }catch (Exception ex){
                    txts[4].setBorder(new LineBorder(Color.red, 1));
                    view.getLblTelefoneFuncionario().setForeground(Color.red);
                    view.getLblTelefoneFuncionario().setText("Telefone "+ ex.getMessage() );
                }
            }
        });
        
        txts[10].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[10].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblRuaFuncionario().setForeground(Color.black);
                view.getLblRuaFuncionario().setText("Rua");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[10].getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!txts[10].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválida!");
                    }
                }catch (Exception ex){
                    txts[10].setBorder(new LineBorder(Color.red, 1));
                    view.getLblRuaFuncionario().setForeground(Color.red);
                    view.getLblRuaFuncionario().setText("Rua "+ ex.getMessage() );
                }
            }
        });
        
        txts[10].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[3].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblRuaFuncionario().setForeground(Color.black);
                view.getLblRuaFuncionario().setText("Bairro");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[3].getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!txts[3].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                }catch (Exception ex){
                    txts[3].setBorder(new LineBorder(Color.red, 1));
                    view.getLblBairroFuncionario().setForeground(Color.red);
                    view.getLblBairroFuncionario().setText("Bairro "+ ex.getMessage() );
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
                    view.getLblNomeCliente().setForeground(Color.red);
                    view.getLblNomeCliente().setText("Nome " + "(" + ex.getMessage() + ")");
                }
            }
        });

        

        view.getTxtCpfCliente().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtCpfCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblCpfCliente().setForeground(Color.black);
                view.getLblCpfCliente().setText("CPF");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtCpfCliente().getText().contains(" ")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (FuncionarioDao.cpfExists(view.getTxtCpfCliente().getText())) {
                        throw new Exception("Cpf existente!");
                    }
                } catch (Exception ex) {
                    view.getTxtCpfCliente().setBorder(new LineBorder(Color.red, 1));
                    if (!ex.getMessage().equals("Cpf existente!")) {
                        view.getTxtCpfCliente().setValue("");
                    }
                    view.getLblCpfCliente().setForeground(Color.red);
                    view.getLblCpfCliente().setText("CPF " + "( " + ex.getMessage() + " )");
                }
            }
        });
        
        view.getTxtEmailCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtEmailCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblEmailCliente().setForeground(Color.black);
                view.getLblEmailCliente().setText("Email");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(view.getTxtEmailCliente().getText());
                    if (view.getTxtEmailCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!(matcher.matches())) {
                        throw new Exception("O Email inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    view.getTxtEmailCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblEmailCliente().setForeground(Color.red);
                    view.getLblEmailCliente().setText("Email " + "( " + ex.getMessage() + " )");
                }
            }
        });
        
        view.getTxtTelefoneCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtTelefoneCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblTelefoneCliente().setForeground(Color.black);
                view.getLblTelefoneCliente().setText("Telefone");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtTelefoneCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Obrigatório!");
                    }
                    if (!view.getTxtTelefoneCliente().getText().matches("^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$")) {
                        throw new Exception("inválido!");
                    }
                }catch (Exception ex){
                    view.getTxtTelefoneCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblTelefoneCliente().setForeground(Color.red);
                    view.getLblTelefoneCliente().setText("Telefone "+ ex.getMessage() );
                }
            }
        });
        
        view.getTxtRuaCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtRuaCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblRuaFuncionario().setForeground(Color.black);
                view.getLblRuaFuncionario().setText("Rua");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtRuaCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtRuaCliente().getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválida!");
                    }
                }catch (Exception ex){
                    view.getTxtRuaCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblRuaCliente().setForeground(Color.red);
                    view.getLblRuaCliente().setText("Rua "+ ex.getMessage() );
                }
            }
        });
        
        view.getTxtBairroCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtBairroCliente().setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblRuaFuncionario().setForeground(Color.black);
                view.getLblRuaFuncionario().setText("Bairro");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtBairroCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtBairroCliente().getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                }catch (Exception ex){
                    view.getTxtBairroCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblBairroCliente().setForeground(Color.red);
                    view.getLblBairroCliente().setText("Bairro "+ ex.getMessage() );
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
