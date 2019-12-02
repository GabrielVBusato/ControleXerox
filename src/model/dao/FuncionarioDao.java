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
import model.bean.Funcionario;
import model.bean.Pessoa;

/**
 *
 * @author Gabriel
 */
public class FuncionarioDao {

    public static int inserir(Funcionario f) throws Exception {
        int lastkey = 0;
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

    public static boolean cpfExists(String cpf) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "SELECT * FROM pessoa WHERE cpf = " + "'" + cpf + "'";
        try {
            stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }
            ConnectionFactory.closeConnection(con, stmt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static ArrayList<Funcionario> buscaTodos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        String sql = "SELECT f.idPessoa, p.nome FROM funcionario f join pessoa p on f.idPessoa = p.idPessoa";
        ArrayList<Funcionario> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario f = new Funcionario();

                f.setIdPessoa(rs.getInt("idPessoa"));
                f.setNome(rs.getString("nome"));

                list.add(f);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! falha ao inserir dados na tabela de ocorrencia");
        }
        return list;
    }
}
