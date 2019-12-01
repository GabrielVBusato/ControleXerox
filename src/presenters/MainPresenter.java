/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenters;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import model.bean.Pais;
import model.dao.PaisDao;
import utils.JFrameUtils;
import views.MainView;

/**
 *
 * @author Maurício
 */
public class MainPresenter {

    private static MainPresenter instance;
    private static MainView view;

    private MainPresenter() {
        view = new MainView();
        view.getPnProfessor().setVisible(false);
        view.getPnTiragem().setVisible(false);
        view.getPnEncomenda().setVisible(false);
        view.getPnAddFuncionario().setVisible(false);
        view.getPnInfoEncomenda().setVisible(false);
        view.getPnProfessor().setName("pnProfessor");
        view.getPnTiragem().setName("pnTiragem");
        view.getPnEncomenda().setName("pnEncomenda");
        view.getPnAddFuncionario().setName("pnAddFuncionario");
        view.getPnInfoEncomenda().setName("pnInfoEncomenda");
        view.getBtnProfessor().setName("btnProfessor");
        view.getBtnTiragem().setName("btnTiragem");
        view.getBtnEncomenda().setName("btnEncomenda");
        view.getBtnAddFuncionario().setName("btnAddFuncionario");
        iniciarView();
    }

    private void iniciarView() {
        
        for (Component c : view.getPnAddFuncionario().getComponents() ){
            if (c instanceof JTextField || c instanceof JFormattedTextField){
                ((JTextField) c).setBorder(new LineBorder(Color.black, 1));
            }
        }

        JFrameUtils.checagemFuncionario(new JTextField[]{view.getTxtNome(), view.getTxtEmail(),
            view.getTxtNumero(), view.getTxtBairro(), view.getTxtTelefone(), view.getTxtCep(), view.getTxtNomeCidade(),
            view.getTxtNomeCidade(), view.getTxtNomeEstado(), view.getTxtPais(), view.getTxtRua(), view.getTxtUf()}, view.getTxtCpf());

        JFrameUtils.checagemCliente();

        //Botão professor
        view.getBtnProfessor().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.visibilidade(view, "pnProfessor", "btnProfessor");
            }
        });

        //Botão tiragem
        view.getBtnTiragem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.visibilidade(view, "pnTiragem", "btnTiragem");
            }
        });

        //Botão encomenda
        view.getBtnEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.visibilidade(view, "pnEncomenda", "btnEncomenda");
            }
        });

        //Botão add novo funcionário
        view.getBtnAddFuncionario().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.visibilidade(view, "pnAddFuncionario", "btnAddFuncionario");
            }
        });

        view.getBtnLimpar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.cleanTextField(view.getPnAddFuncionario().getComponents());
            }
        });

        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                Component[] components = view.getPnAddFuncionario().getComponents();
                for (Component c : components) {
                    if (c instanceof JTextField) {
                        if (((JTextField) c).getText().equals("") || ((JTextField) c).getText().equals("  ") ||
                                ((JTextField) c).getText().equals("(  )      -    ") || ((JTextField) c).getText().equals("  .   -   ")){
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
                    Pais pais = new Pais();
                    pais.setNome(view.getTxtPais().getText());
                    try {
                        pais.setIdPais(PaisDao.inserir(pais));
                    } catch (Exception ex) {
                        Logger.getLogger(MainPresenter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios de forma correta.");
                }
            }
        });

        view.getBtnProximoPanelCliente().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnEncomenda().setVisible(false);
                view.getPnInfoEncomenda().setVisible(true);
                view.getPnAvisaEncomendaSalva().setVisible(false);
            }
        });

        view.getBtnSalvarEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnAvisaEncomendaSalva().setVisible(true);
            }
        });
    }

    public static MainPresenter getInstance() {
        if (instance == null) {
            instance = new MainPresenter();
        }
        view.setVisible(true);
        return instance;
    }

    public static MainView getView() {
        if (!(view == null)) {
            return view;
        }
        return null;
    }
}
