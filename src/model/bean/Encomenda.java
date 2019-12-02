/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Encomenda {
    private int idEncomenda;
    private ArrayList<Tiragem> tiragens = new ArrayList<>();
    private int idPessoa;

    public int getIdEncomenda() {
        return idEncomenda;
    }

    public void setIdEncomenda(int idEncomenda) {
        this.idEncomenda = idEncomenda;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public ArrayList<Tiragem> getTiragens() {
        return tiragens;
    }

    public void setTiragens(Tiragem t) {
        this.tiragens.add(t);
    }
    
    
    
}
