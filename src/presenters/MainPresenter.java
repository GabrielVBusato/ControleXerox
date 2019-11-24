/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenters;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
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
        view.getPnProfessor().setName("pnProfessor");
        view.getPnTiragem().setName("pnTiragem");
        view.getPnEncomenda().setName("pnEncomenda");
        view.getPnAddFuncionario().setName("pnAddFuncionario");
        view.getBtnProfessor().setName("btnProfessor");
        view.getBtnTiragem().setName("btnTiragem");
        view.getBtnEncomenda().setName("btnEncomenda");
        view.getBtnAddFuncionario().setName("btnAddFuncionario");
        iniciarView();
    }
    
    private void iniciarView() {

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
                JFrameUtils.checagem(view.getPnAddFuncionario().getComponents());
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
}
