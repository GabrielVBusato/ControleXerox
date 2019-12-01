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
import model.bean.Cidade;
import model.bean.Endereco;
import model.bean.Estado;
import model.bean.Funcionario;
import model.bean.Pais;
import model.bean.Pessoa;
import model.dao.CidadeDao;
import model.dao.EnderecoDao;
import model.dao.EstadoDao;
import model.dao.FuncionarioDao;
import model.dao.PaisDao;
import model.dao.PessoaDao;
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
        view.getPnAvisaEncomendaSalva().setVisible(false);
        view.getPnAddTiragemEncomenda().setVisible(false);
        view.getPnMaisTiragem().setVisible(false);
        view.getPnProfessor().setName("pnProfessor");
        view.getPnTiragem().setName("pnTiragem");
        view.getPnEncomenda().setName("pnEncomenda");
        view.getPnAddFuncionario().setName("pnAddFuncionario");
        view.getPnInfoEncomenda().setName("pnInfoEncomenda");
        view.getPnAddTiragemEncomenda().setName("pnAddTiragemEncomenda");
        view.getPnAvisaEncomendaSalva().setName("pnAvisaEncomendaSalva");
        view.getPnMaisTiragem().setName("pnMaisTiragem");
        view.getBtnProfessor().setName("btnProfessor");
        view.getBtnTiragem().setName("btnTiragem");
        view.getBtnEncomenda().setName("btnEncomenda");
        view.getBtnAddFuncionario().setName("btnAddFuncionario");
        iniciarView();
    }

    private void iniciarView() {

        for (Component c : view.getPnAddFuncionario().getComponents()) {
            if (c instanceof JTextField || c instanceof JFormattedTextField) {
                ((JTextField) c).setBorder(new LineBorder(Color.black, 1));
            }
        }

        for (Component c : view.getPnEncomenda().getComponents()) {
            if (c instanceof JTextField || c instanceof JFormattedTextField) {
                ((JTextField) c).setBorder(new LineBorder(Color.black, 1));
            }
        }

        view.getBtnProximoPanelCliente().setEnabled(false);

        JFrameUtils.checagemFuncionario(new JTextField[]{view.getTxtNome(), view.getTxtEmail(),
            view.getTxtNumero(), view.getTxtBairro(), view.getTxtTelefone(), view.getTxtCep(), view.getTxtNomeCidade(),
            view.getTxtNomeCidade(), view.getTxtNomeEstado(), view.getTxtPais(), view.getTxtRua(), view.getTxtUf()}, view.getTxtCpf());

        //Checagem de campos de cliente
        JFrameUtils.checagemCliente(view);

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

        //Botão limpar cliente
        view.getBtnLimparCliente().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.cleanTextField(view.getPnEncomenda().getComponents());
            }
        });

        //Botão cancelar cliente
        view.getBtnCancelarCliente().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrameUtils.cleanTextField(view.getPnEncomenda().getComponents());
                view.getPnEncomenda().setVisible(false);
                view.getBtnEncomenda().setEnabled(true);
            }
        });
   
        //Botão salva encomenda e cliente
        view.getBtnSalvarEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnAvisaEncomendaSalva().setVisible(true);
                view.getBtnCancelarInfoEncomenda().setVisible(false);
                view.getBtnSalvarEncomenda().setVisible(false);
            }
        });
        
        //Botão próximo para tiragens
        view.getBtnProximoTiragens().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnAddTiragemEncomenda().setVisible(true);
                view.getLblTituloInfoEncomenda().setVisible(false);
                view.getPnAvisaEncomendaSalva().setVisible(false);
                view.getTxtNomeEncomenda().setVisible(false);
                view.getLblNomeEncomenda().setVisible(false);
            }
        });
        
        //Botão adiciona tiragem a encomenda
        view.getBtnSalvarTiragemEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnAddTiragemEncomenda().setVisible(false);
                view.getPnMaisTiragem().setVisible(true);
            }
        });
        
        //Botão adiciona mais tiragem a encomenda
        view.getBtnAddMaisTiragemEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnMaisTiragem().setVisible(false);
                view.getPnAddTiragemEncomenda().setVisible(true);
            }
        });
        
        //Botão cancela mais tiragem
        view.getBtnCancelarMaisTiragem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnMaisTiragem().setVisible(false);
                view.getBtnEncomenda().setEnabled(true);
            }
        });

        view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = true;
                Component[] components = view.getPnAddFuncionario().getComponents();
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
                    //Declaração de variáveis
                    Pais pais = new Pais();
                    Estado estado = new Estado();
                    Cidade cidade = new Cidade();
                    Endereco endereco = new Endereco();
                    Pessoa pessoa = new Pessoa();
                    Funcionario funcionario = new Funcionario();

                    //Setando atributos do form
                    pais.setNome(view.getTxtPais().getText());

                    estado.setNome(view.getTxtNomeEstado().getText());
                    estado.setUf(view.getTxtUf().getText());

                    cidade.setCep(view.getTxtCep().getText());
                    cidade.setNome(view.getTxtNomeCidade().getText());

                    endereco.setBairro(view.getTxtBairro().getText());
                    endereco.setNumero(view.getTxtNumero().getText());
                    endereco.setRua(view.getTxtRua().getText());

                    pessoa.setCpf(view.getTxtCpf().getText());
                    pessoa.setEmail(view.getTxtEmail().getText());
                    pessoa.setNome(view.getTxtNome().getText());
                    pessoa.setTelefone(view.getTxtTelefone().getText());

                    //Set id
                    try {
                        pais.setIdPais(PaisDao.inserir(pais));

                        estado.setIdPais(pais.getIdPais());
                        estado.setIdEstado(EstadoDao.inserir(estado));

                        cidade.setIdEstado(estado.getIdEstado());
                        cidade.setIdCidade(CidadeDao.inserir(cidade));

                        endereco.setIdCidade(cidade.getIdCidade());
                        endereco.setIdEndereco(EnderecoDao.inserir(endereco));

                        pessoa.setIdEndereco(endereco.getIdEndereco());
                        pessoa.setIdPessoa(PessoaDao.inserir(pessoa));

                        funcionario.setIdPessoa(pessoa.getIdPessoa());
                        FuncionarioDao.inserir(funcionario);

                        JFrameUtils.cleanTextField(view.getPnEncomenda().getComponents());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro " + ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios de forma correta.");
                }
            }
        });
        
       


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
