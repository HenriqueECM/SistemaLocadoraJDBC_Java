package org.example.dao;

import com.sun.security.jgss.GSSUtil;
import org.example.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilmeDAO {
    public void cadastrarFilme(Filme filme){
        String query = "INSERT INTO filme (titulo, genero, anoLancamento) VALUES (?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, filme.getTitulo());
            stmt.setString(2, filme.getGenero());
            stmt.setInt(3, filme.getAnoLancamento());
            stmt.executeUpdate();

            System.out.println("Filme cadastrado com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
