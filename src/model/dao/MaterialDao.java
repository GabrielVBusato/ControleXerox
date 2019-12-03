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
import model.bean.Material;

/**
 *
 * @author Gabriel
 */
public class MaterialDao {
    public static int inserir(Material f) throws Exception {
        int lastkey = 0;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        String sql = "INSERT INTO material(titulo, copias, preço, idPasta) values (?, ?, ?, ?)";
        //Preparando statement e retornando a ultima chave primaria inserida
        stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        //Setando os valores
        stmt.setString(1, f.getTitulo());
        stmt.setInt(2, f.getCopias());
        stmt.setDouble(3, f.getPreço());
        stmt.setInt(4, f.getIdPasta());

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
    
    public static ArrayList<Material> buscaTodos(int idPasta) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        String sql = "SELECT * FROM material m join pasta p on m.idPasta = p.idPasta where m.idPasta =" + "'" + idPasta + "'";
        ArrayList<Material> list = new ArrayList<>();
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material f = new Material();
                f.setIdMaterial(rs.getInt("idMaterial"));
                f.setTitulo(rs.getString("titulo"));
                f.setCopias(rs.getInt("copias"));
                f.setPreço(rs.getDouble("preço"));
                f.setIdPasta(rs.getInt("idPasta"));
                list.add(f);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro sql");
        }
        return list;
    }
}
