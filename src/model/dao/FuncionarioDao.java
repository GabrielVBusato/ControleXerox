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
import model.bean.Funcionario;

/**
 *
 * @author Gabriel
 */
public class FuncionarioDao {
    public static int inserir(Funcionario f) throws Exception {
        int lastkey =0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO funcionario(idPessoa) values (?)";
        //Preparando statement e retornando a ultima chave primaria inserida
        stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //Setando os valores
        stmt.setInt(1, f.getIdPessoa());

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
