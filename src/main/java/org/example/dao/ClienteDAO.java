package org.example.dao;

import org.example.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente){
        String query = "INSERT INTO cliente (nome, email) VALUES (?,?)";

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.executeUpdate();

            System.out.println("\nCliente cadastrado com sucesso!");

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Cliente> listarClientes(){
        String query = "SELECT id, nome, email FROM cliente";
        List<Cliente> clientes = new ArrayList<>();

        try(Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(query)){

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return clientes;
    }
}
