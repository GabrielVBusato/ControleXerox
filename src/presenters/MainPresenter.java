/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presenters;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.dao.PaisDao;
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
        iniciarView();
    }

    private void iniciarView() {

        //Botão professor
        view.getBtnProfessor().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnProfessor().setVisible(true);
                view.getPnTiragem().setVisible(false);
                view.getPnEncomenda().setVisible(false);
                view.getPnAddFuncionario().setVisible(false);
                view.getBtnProfessor().setEnabled(false);
                view.getBtnTiragem().setEnabled(true);
                view.getBtnEncomenda().setEnabled(true);
                view.getBtnAddFuncionario().setEnabled(true);
            }
        });

        //Botão tiragem
        view.getBtnTiragem().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnProfessor().setVisible(false);
                view.getPnTiragem().setVisible(true);
                view.getPnEncomenda().setVisible(false);
                view.getPnAddFuncionario().setVisible(false);
                view.getBtnProfessor().setEnabled(true);
                view.getBtnTiragem().setEnabled(false);
                view.getBtnEncomenda().setEnabled(true);
                view.getBtnAddFuncionario().setEnabled(true);
            }
        });

        //Botão encomenda
        view.getBtnEncomenda().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnProfessor().setVisible(false);
                view.getPnTiragem().setVisible(false);
                view.getPnEncomenda().setVisible(true);
                view.getPnAddFuncionario().setVisible(false);
                view.getBtnProfessor().setEnabled(true);
                view.getBtnTiragem().setEnabled(true);
                view.getBtnEncomenda().setEnabled(false);
                view.getBtnAddFuncionario().setEnabled(true);
            }
        });

        //Botão add novo funcionário
        view.getBtnAddFuncionario().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                view.getPnProfessor().setVisible(false);
                view.getPnTiragem().setVisible(false);
                view.getPnEncomenda().setVisible(false);
                view.getPnAddFuncionario().setVisible(true);
                view.getBtnProfessor().setEnabled(true);
                view.getBtnTiragem().setEnabled(true);
                view.getBtnEncomenda().setEnabled(true);
                view.getBtnAddFuncionario().setEnabled(false);
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
