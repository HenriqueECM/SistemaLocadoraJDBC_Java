package org.example.main;

import org.example.dao.AluguelDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Aluguel;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);

    public static void main(String[]args){
        inicio();
    }

    private static void inicio() {

        boolean sair = false;

        System.out.println("========MENU=======");
        System.out.println("1 - CADASTRAR CLIENTE");
        System.out.println("2 - CADASTRAR FILME");
        System.out.println("3 - ALUGAR FILME");
        System.out.println("0 - SAIR");
        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao){
            case 1: {
                cadastrarCliente();
                break;
            }
            case 2: {
                cadastrarFilme();
                break;
            }
            case 3: {
                alugarFilme();
                break;
            }
            case 0: {
                sair = true;
                System.out.println("Sistema finalizado!");
                break;
            }
        }

        if (!sair) {
            inicio();
        }
    }

    private static void cadastrarFilme() {
        System.out.println("Digite o titulo do filme: ");
        String titulo = SC.nextLine();

        System.out.println("Digite o genero do filme: ");
        String genero = SC.nextLine();

        System.out.println("Digite o ano de lançamento: ");
        int anoLancamento = SC.nextInt();

        Filme filme = new Filme(titulo, genero, anoLancamento);
        FilmeDAO dao = new FilmeDAO();

        dao.cadastrarFilme(filme);
    }

    public static  void cadastrarCliente(){
        System.out.println("Digite o nome cliente: ");
        String nome = SC.nextLine();

        System.out.println("Digite o email do cliente: ");
        String email = SC.nextLine();

        Cliente cliente = new Cliente(nome, email);
        ClienteDAO dao = new ClienteDAO();

        dao.cadastrarCliente(cliente);
    }

    public static void alugarFilme(){

        ClienteDAO clienteDao = new ClienteDAO();

        List<Cliente> clientes = clienteDao.listarClientes();

        for(Cliente cliente: clientes){
            System.out.println("Id: " + cliente.getId() + " Nome: " + cliente.getNome() + " Email: " + cliente.getEmail());
        }
        System.out.println("\nDigite o id do cliente que ira fazer locação: ");
        int idCliente = SC.nextInt();
        SC.nextLine();

        FilmeDAO filmeDao = new FilmeDAO();

        List<Filme> filmes = filmeDao.listarFilmes();

        for (Filme filme: filmes){
            System.out.println("Id: " + filme.getId() + " Titulo: " + filme.getTitulo() + " Genero: " + filme.getGenero() + " Ano Lançamento: " + filme.getAnoLancamento());
        }
        System.out.println("\nDigite o id do filme que será alugado: ");
        int idFilme = SC.nextInt();
        SC.nextLine();

        LocalDate data = LocalDate.now();

        Aluguel aluguel = new Aluguel(idCliente, idFilme, data, null);

        AluguelDAO aluguelDao = new AluguelDAO();

        aluguelDao.cadastrarAluguel(aluguel);
    }
}
