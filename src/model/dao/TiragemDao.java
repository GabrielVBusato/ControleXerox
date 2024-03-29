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
import java.sql.Statement;
import model.bean.Tiragem;

/**
 *
 * @author Gabriel
 */
public class TiragemDao {
    public static int inserir(Tiragem t) throws Exception {
        int lastkey = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO tiragem(titulo, idVenda, idEncomenda, copias, preço, idMaterial) values (?, ?, ?, ?, ?, ?)";
        //Preparando statement e retornando a ultima chave primaria inserida
        stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        System.out.println(t.getIdMaterial());
        //Setando os valores
        stmt.setString(1, t.getTitulo());
        stmt.setInt(2, t.getIdVenda());
        stmt.setInt(3, t.getIdEncomenda());
        stmt.setInt(4, t.getCopias());
        stmt.setDouble(5, t.getPreço());
        stmt.setInt(6, t.getIdMaterial());
        //Execuntando comando de inserção no banco
        stmt.executeUpdate();

        //Pegando as chaves
        ResultSet rs = stmt.getGeneratedKeys();

        //Pegando a ultima chave inserida
        if (rs.next()) {
            lastkey = rs.getInt(1);
        }

        ConnectionFactory.closeConnection(con, stmt);

        return lastkey;
    }
}
