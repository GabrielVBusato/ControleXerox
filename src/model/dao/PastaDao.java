/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Pasta;

/**
 *
 * @author Gabriel
 */
public class PastaDao {

    public static ArrayList<Pasta> buscaPastasProfessor(int id) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        ArrayList<Pasta> pst = new ArrayList<>();
        String sql = "select * from pasta where idPessoa = " + "'" + id + "'";
        System.out.println(sql);
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pasta p = new Pasta();
                p.setNome(rs.getString("nome"));
                p.setIdPasta(rs.getInt("idPasta"));
                pst.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! falha: " + ex);
        }
        return pst;
    }

    public static int inserir(Pasta f) throws Exception {
        int lastkey = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO pasta(nome, idPessoa) values (?, ?)";
        //Preparando statement e retornando a ultima chave primaria inserida
        stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //Setando os valores
        stmt.setString(1, f.getNome());
        stmt.setInt(2, f.getIdPessoa());

        //Execuntando comando de inserção no banco
        stmt.executeUpdate();

        //Pegando as chaves
        ResultSet rs = stmt.getGeneratedKeys();

        //Pegando a ultima chave inserida
        if (rs.next()) {
            lastkey = rs.getInt(1);
        }

        System.out.println(lastkey);
        ConnectionFactory.closeConnection(con, stmt);

        return lastkey;
    }

}
