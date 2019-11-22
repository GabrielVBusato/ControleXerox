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
import javax.swing.JOptionPane;
import model.bean.Cidade;

/**
 *
 * @author Gabriel
 */
public class PaisDao {

    public static void inserir(String nome) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO pais(nome) values (?)";

        try {
            //Preparando statement e retornando a ultima chave primaria inserida
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Setando os valores
            stmt.setString(1, nome);

            //Execuntando comando de inserção no banco
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}
