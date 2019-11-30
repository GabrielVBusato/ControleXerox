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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
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
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[0].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[0].getText().toLowerCase().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("O nome inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    txts[0].setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[1].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[1].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblEmailFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(txts[1].getText());
                    if (txts[1].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!(matcher.matches())) {
                        throw new Exception("O Email inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    txts[1].setBorder(new LineBorder(Color.red, 1));
                    view.getLblEmailFuncionario().setForeground(Color.red);
                }
            }
        });

        cpf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                cpf.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblCpfFuncionario().setForeground(Color.black);
                view.getLblCpfFuncionario().setText("CPF*");
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (cpf.getText().contains(" ")) {
                        throw new Exception("");
                    }
                    if (FuncionarioDao.cpfExists(cpf.getText())) {
                        throw new Exception("já existente!");
                    }
                } catch (Exception ex) {
                    cpf.setBorder(new LineBorder(Color.red, 1));
                    if (!ex.getMessage().equals("já existente!")) {
                        cpf.setValue("");
                    }
                    view.getLblCpfFuncionario().setForeground(Color.red);
                    view.getLblCpfFuncionario().setText("CPF* " + ex.getMessage());
                }
            }
        });

        txts[4].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[4].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblTelefoneFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[4].getText().equals("(  )      -    ")) {
                        throw new Exception("~");
                    }
                } catch (Exception ex) {
                    txts[4].setBorder(new LineBorder(Color.red, 1));
                    ((JFormattedTextField) txts[4]).setValue("");
                    view.getLblTelefoneFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[10].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[10].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblRuaFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[10].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[10].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválida!");
                    }
                } catch (Exception ex) {
                    txts[10].setBorder(new LineBorder(Color.red, 1));
                    view.getLblRuaFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[3].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[3].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblBairroFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[3].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[3].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    txts[3].setBorder(new LineBorder(Color.red, 1));
                    view.getLblBairroFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[2].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[2].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblNumeroFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[2].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[2].getText().matches("^[0-9]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    txts[2].setBorder(new LineBorder(Color.red, 1));
                    view.getLblNumeroFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[6].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[6].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblCidadeFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[6].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[6].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválida!");
                    }
                } catch (Exception ex) {
                    txts[6].setBorder(new LineBorder(Color.red, 1));
                    view.getLblCidadeFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[5].addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txts[5].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblCepFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[5].getText().equals("  .   -   ")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    txts[5].setBorder(new LineBorder(Color.red, 1));
                    ((JFormattedTextField) txts[5]).setValue("");
                    view.getLblCepFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[8].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[8].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblEstadoFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[8].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[8].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    txts[8].setBorder(new LineBorder(Color.red, 1));
                    view.getLblEstadoFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[11].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[11].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblUfFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[11].getText().toLowerCase().equals("  ")) {
                        throw new Exception("");
                    }
                } catch (Exception ex) {
                    txts[11].setBorder(new LineBorder(Color.red, 1));
                    ((JFormattedTextField) txts[11]).setValue("");
                    view.getLblUfFuncionario().setForeground(Color.red);
                }
            }
        });

        txts[9].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[9].setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
                view.getLblPaisFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[9].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[9].getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    txts[9].setBorder(new LineBorder(Color.red, 1));
                    view.getLblPaisFuncionario().setForeground(Color.red);
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
