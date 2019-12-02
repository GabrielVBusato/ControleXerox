/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Venda {
    private int idVenda;
    private Date dataVenda;
    private double valorTotal;
    private int idPessoa;
    private ArrayList<Tiragem> tiragens = new ArrayList<>();

    public int getIdVenda() {
        return idVenda;
    }

    public ArrayList<Tiragem> getTiragens() {
        return tiragens;
    }

    public void setTiragens(Tiragem t) {
        tiragens.add(t);
    }
    

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }
    
    
}
