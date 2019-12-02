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
import javax.swing.JLabel;
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
            if (c instanceof JFormattedTextField){
                ((JFormattedTextField) c).setValue("");
            }
            if (c instanceof JLabel) {
                ((JLabel) c).setForeground(Color.black);
                if (((JLabel) c).getText().equals("CPF* já existente!")) {
                    ((JLabel) c).setText("CPF*");
                }
            }
        }
    }

    public static boolean checagemFuncionario(JTextField[] txts, JFormattedTextField cpf) {
        MainView view = MainPresenter.getView();
        //Check nome
        txts[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txts[0].setBorder(new LineBorder(Color.black, 1));
                view.getLblNomeFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[0].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[0].getText().toLowerCase().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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
                txts[1].setBorder(new LineBorder(Color.black, 1));
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
                cpf.setBorder(new LineBorder(Color.black, 1));
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
                txts[4].setBorder(new LineBorder(Color.black, 1));
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
                txts[10].setBorder(new LineBorder(Color.black, 1));
                view.getLblRuaFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[10].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[10].getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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
                txts[3].setBorder(new LineBorder(Color.black, 1));
                view.getLblBairroFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[3].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[3].getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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
                txts[2].setBorder(new LineBorder(Color.black, 1));
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
                txts[6].setBorder(new LineBorder(Color.black, 1));
                view.getLblCidadeFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[6].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[6].getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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
                txts[5].setBorder(new LineBorder(Color.black, 1));
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
                txts[8].setBorder(new LineBorder(Color.black, 1));
                view.getLblEstadoFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[8].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[8].getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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
                txts[11].setBorder(new LineBorder(Color.black, 1));
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
                txts[9].setBorder(new LineBorder(Color.black, 1));
                view.getLblPaisFuncionario().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (txts[9].getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!txts[9].getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
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

    public static void checagemCliente(MainView view) {

        //Check nome
        view.getTxtNomeCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNomeCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblNomeCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtNomeCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo obrigatório!");
                    }
                    if (!view.getTxtNomeCliente().getText().toLowerCase().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("O nome inserido está incorreto!");
                    }
                } catch (Exception ex) {
                    view.getTxtNomeCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtCpfCliente().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtCpfCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblCpfCliente().setForeground(Color.black);
                view.getLblCpfCliente().setText("CPF*");
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtCpfCliente().getText().contains(" ")) {
                        throw new Exception("");
                    }
                    if (FuncionarioDao.cpfExists(view.getTxtCpfCliente().getText())) {
                        throw new Exception("já existente!");
                    }
                } catch (Exception ex) {
                    view.getTxtCpfCliente().setBorder(new LineBorder(Color.red, 1));
                    if (!ex.getMessage().equals("já existente!")) {
                        view.getTxtCpfCliente().setValue("");
                    }
                    view.getLblCpfCliente().setForeground(Color.red);
                    view.getLblCpfCliente().setText("CPF* " + ex.getMessage());
                }
            }
        });

        view.getTxtEmailCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtEmailCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblEmailCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
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
                }
            }
        });

        view.getTxtTelefoneCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtTelefoneCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblTelefoneCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtTelefoneCliente().getText().equals("(  )      -    ")) {
                        throw new Exception("Obrigatório!");
                    }
                } catch (Exception ex) {
                    view.getTxtTelefoneCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblTelefoneCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtRuaCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtRuaCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblRuaCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtRuaCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtRuaCliente().getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("inválida!");
                    }
                } catch (Exception ex) {
                    view.getTxtRuaCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblRuaCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtBairroCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtBairroCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblBairroCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtBairroCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtBairroCliente().getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtBairroCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblBairroCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtNumeroCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNumeroCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblNumeroCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtNumeroCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("");
                    }
                    if (!view.getTxtNumeroCliente().getText().matches("^[0-9]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtNumeroCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblNumeroCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtNomeCidadeCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNomeCidadeCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblCidadeCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtNomeCidadeCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtNomeCidadeCliente().getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtNomeCidadeCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblCidadeCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtNomeEstadoCliente().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNomeEstadoCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblEstadoCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtNomeEstadoCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtNomeEstadoCliente().getText().matches("^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtNomeEstadoCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblEstadoCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtPaisCliente().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtPaisCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblPaisCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtPaisCliente().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtPaisCliente().getText().matches("^^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtPaisCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblPaisCliente().setForeground(Color.red);
                }
            }

        });

        view.getTxtCepCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtCepCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblCepCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtCepCliente().getText().toLowerCase().equals("     -   ")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                } catch (Exception ex) {
                    view.getTxtCepCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblCepCliente().setForeground(Color.red);
                }
            }
        });

        view.getTxtUfCliente().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtUfCliente().setBorder(new LineBorder(Color.black, 1));
                view.getLblUfCliente().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                liberaBotao(view);
                try {
                    if (view.getTxtUfCliente().getText().toLowerCase().equals("  ")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                } catch (Exception ex) {
                    view.getTxtUfCliente().setBorder(new LineBorder(Color.red, 1));
                    view.getLblUfCliente().setForeground(Color.red);
                }
            }
        });
        
        view.getTxtNomeEncomenda().addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                view.getTxtNomeEncomenda().setBorder(new LineBorder(Color.black, 1));
                view.getLblNomeEncomenda().setForeground(Color.black);
            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    if (view.getTxtNomeEncomenda().getText().toLowerCase().equals("")) {
                        throw new Exception("Campo Obrigatório!");
                    }
                    if (!view.getTxtNomeEncomenda().getText().matches("^[a-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÊÍÏÓÒÖÚÇÑ ]+$")) {
                        throw new Exception("inválido!");
                    }
                } catch (Exception ex) {
                    view.getTxtNomeEncomenda().setBorder(new LineBorder(Color.red, 1));
                    view.getLblNomeEncomenda().setForeground(Color.red);
                }
            }
        });
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

    public static void liberaBotao(MainView view) {
        boolean isValid = true;
        Component[] components = view.getPnEncomenda().getComponents();
        for (Component c : components) {
            if (c instanceof JTextField) {
                if (((JTextField) c).getText().equals("") || ((JTextField) c).getText().equals("  ")
                        || ((JTextField) c).getText().equals("(  )      -    ") || ((JTextField) c).getText().equals("  .   -   ")) {
                    isValid = false;
                    break;
                }
                LineBorder border = (LineBorder) ((JTextField) c).getBorder();
                if (border.getLineColor().getRGB() == -65536) {
                    isValid = false;
                }
            }
        }
        if (isValid) {
            view.getBtnProximoPanelCliente().setEnabled(isValid);
        } else {
            view.getBtnProximoPanelCliente().setEnabled(false);
        }

    }
}
