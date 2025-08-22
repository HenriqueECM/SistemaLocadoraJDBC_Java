package org.example.dao;

import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AluguelDAO {
    public void cadastrarAluguel (Aluguel aluguel){
        String query = "INSERT INTO aluguel (cliente_id, filme_id, dataAluguel) VALUES(?,?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, aluguel.getClienteId());
            stmt.setInt(2, aluguel.getFilmeId());
            stmt.setDate(3, Date.valueOf(aluguel.getDataAluguel()));
            stmt.executeUpdate();

            System.out.println("\nAluguel cadastrado com sucesso!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Aluguel> listarAluguel(){
        String query = "SELECT id, cliente_id, filme_id, dataAluguel, dataDevolucao FROM aluguel";

        List<Aluguel> aluguels = new ArrayList<>();
        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int cliente_id = rs.getInt("cliente_id");
                int filme_id = rs.getInt("filme_id");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();
                Date dataDevolucao = rs.getDate("dataDevolucao");

                Aluguel aluguel = new Aluguel(id, cliente_id, filme_id, dataAluguel, dataDevolucao);
                aluguels.add(aluguel);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return aluguels;
    }

    public List<Aluguel> listarAluguelPendente(){
        String query = "SELECT id, cliente_id, filme_id, dataAluguel, dataDevolucao FROM aluguel WHERE dataDevolucao IS NULL";

        List<Aluguel> aluguels = new ArrayList<>();

        try(Connection conm = Conexao.conectar();
            PreparedStatement stmt = conm.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                int id = rs.getInt("id");
                int cliente_id = rs.getInt("cliente_id");
                int filme_id = rs.getInt("filme_id");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();
                Date dataDevolucao = rs.getDate("dataDevolucao");

                Aluguel aluguel = new Aluguel(id, cliente_id, filme_id, dataAluguel, dataDevolucao);

                aluguels.add(aluguel);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return aluguels;
    }

    public List<Aluguel> listarClientePorFilme(int filmeId){
        String query = "SELECT f.titulo, a.id AS idAluguel, c.id AS idCliente, c.nome AS nomeCliente, c.email, a.dataAluguel, a.dataDevolucao " +
                "FROM aluguel a " +
                "JOIN filme f ON a.filme_id = f.id " +
                "JOIN cliente c ON a.cliente_id = c.id " +
                "WHERE f.id = ? " +
                "ORDER BY f.id";

        List<Aluguel> alugueis = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, filmeId);
            ResultSet rs = stmt.executeQuery();

            String nomeFilme = null;

            while(rs.next()){
                if (nomeFilme == null){
                    nomeFilme = rs.getString("titulo");
                    System.out.println("Nome do Filme: " + nomeFilme);
                }

                int id = rs.getInt("idCliente");
                String nome = rs.getString("nomeCliente");
                String email = rs.getString("email");

                Cliente cliente = new Cliente(id, nome, email);

                Aluguel aluguel = new Aluguel();

                aluguel.setId(rs.getInt("idAluguel"));
                aluguel.setCliente(cliente);
                aluguel.setDataAluguel(rs.getDate("dataAluguel").toLocalDate());
                Date dataDevolucao = rs.getDate("dataDevolucao");
                aluguel.setDataDevolucao(dataDevolucao != null ? dataDevolucao : null);

                alugueis.add(aluguel);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return alugueis;
    }

    public List<Aluguel> listarFilmePorCliente(int clienteId){
        String query = "SELECT c.nome AS nomeCliente, a.id AS idAluguel, f.id AS idFilme, f.titulo, f.genero, f.anoLancamento, a.dataAluguel, a.dataDevolucao " +
                "FROM aluguel a " +
                "JOIN filme f ON a.filme_id = f.id " +
                "JOIN cliente c ON a.cliente_id = c.id " +
                "WHERE c.id = ? " +
                "ORDER BY f.id";

        List<Aluguel> alugueis = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setInt(1, clienteId);

            ResultSet rs = stmt.executeQuery();

            String nomeCliente = null;

            while(rs.next()){
                if (nomeCliente == null) {
                    nomeCliente = rs.getString("nomeCliente");
                    System.out.println("\nCliente: " + nomeCliente);
                }

                int idFilme = rs.getInt("idFilme");
                String titulo = rs.getString("titulo");
                String genero = rs.getString("genero");
                int anoLancamento = rs.getInt("anoLancamento");

                Filme filme = new Filme(idFilme, titulo, genero, anoLancamento);

                Aluguel aluguel = new Aluguel();
                aluguel.setId(rs.getInt("idAluguel"));
                aluguel.setFilme(filme);
                aluguel.setDataAluguel(rs.getDate("dataAluguel").toLocalDate());
                Date dataDevolucao = rs.getDate("dataDevolucao");
                aluguel.setDataDevolucao(dataDevolucao != null ? dataDevolucao : null);

                alugueis.add(aluguel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alugueis;
    }

    public List<AluguelDevolucao> listarAluguelDevolucao(){
        String query = "SELECT a.id, f.titulo AS tituloFilme, c.nome AS nomeCliente, a.dataAluguel FROM aluguel a LEFT JOIN filme f ON a.filme_id = f.id LEFT JOIN cliente c ON a.cliente_id = c.id WHERE dataDevolucao IS NULL";

        List<AluguelDevolucao> devolucaos = new ArrayList<>();

        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String tituloFilme = rs.getString("tituloFilme");
                String nomeCliente = rs.getString("nomeCliente");
                LocalDate dataAluguel = rs.getDate("dataAluguel").toLocalDate();

                AluguelDevolucao devolucao = new AluguelDevolucao(dataAluguel, id, tituloFilme, nomeCliente);
                devolucaos.add(devolucao);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return devolucaos;
    }

    public void registroDevolucao(int id){
        String query = "UPDATE aluguel SET dataDevolucao = ? WHERE id = ?";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, id);
            stmt.executeUpdate();

            System.out.println("\nFilme devolvido com sucesso!");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}