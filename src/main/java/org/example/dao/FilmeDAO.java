package org.example.dao;

import com.sun.security.jgss.GSSUtil;
import org.example.model.Filme;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Filme> listarFilmes(){
        String query = "SELECT id, titulo, genero, anoLancamento FROM filme";

        List<Filme> filmes = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int anoLancamentoo = rs.getInt("anoLancamento");

                Filme filme = new Filme(id, titulo, genero, anoLancamentoo);
                filmes.add(filme);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return filmes;
    }
}
