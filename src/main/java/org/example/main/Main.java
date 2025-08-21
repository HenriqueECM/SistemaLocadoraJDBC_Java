package org.example.main;

import org.example.dao.AluguelDAO;
import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Aluguel;
import org.example.model.AluguelDevolucao;
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

        System.out.print("""

╔══════════════════════════════════════════════════════════╗
║                 🎬 LOCADORA DE FILMES 🎬                ║
╠══════════════════════════════════════════════════════════╣
║  1 - Cadastrar Cliente                                   ║
║  2 - Cadastrar Filme                                     ║
║  3 - Alugar Filme                                        ║
║  4 - Devolver Filme                                      ║
║  5 - Listar Todos Clientes                               ║
║  6 - Listar Todos Filmes                                 ║
║  7 - Listar Todos Aluguéis                               ║
║  8 - Listar Aluguéis Pendentes                           ║
║  9 - Listar Filmes por Cliente                           ║
║  10 - Listar Clientes por Filme                          ║
║  0 - Sair                                                ║
╚══════════════════════════════════════════════════════════╝
Escolha uma operação do sistema: """);

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
            case 4: {
                devolverFilme();
                break;
            }
            case 5: {
                listarCliente();
                break;
            }
            case 6: {
                listarFilme();
                break;
            }
            case 7: {
                listarAluguel();
                break;
            }
            case 8: {
                listarAlugueisPendentes();
                break;
            }
            case 9: {
                listarFilmePorCliente();
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

    private static void listarFilmePorCliente() {
        System.out.print("Informe o ID do cliente: ");
        int clienteId = SC.nextInt();

        AluguelDAO aluguelDao = new AluguelDAO();
        List<Aluguel> alugueis = aluguelDao.listarFilmePorCliente(clienteId);

        if (alugueis.isEmpty()) {
            System.out.println("\nNenhum filme encontrado para este cliente.");
            return;
        }

        System.out.println("Filmes alugados pelo cliente: ");
        for (Aluguel a : alugueis){
            System.out.println(
                    "ID do Aluguel: " + a.getId() +
                            " | Filme: " + a.getFilme().getTitulo() +
                            " (" + a.getFilme().getAnoLancamento() + ")" +
                            " | Gênero: " + a.getFilme().getGenero() +
                            " | Data Aluguel: " + a.getDataAluguel() +
                            " | Data Devolução: " + (a.getDataDevolucao() != null ? a.getDataDevolucao() : "Ainda não devolvido")
            );
        }
    }


    private static void listarAlugueisPendentes() {
        AluguelDAO aluguelDao = new AluguelDAO();

        List<Aluguel> aluguels = aluguelDao.listarAluguelPendente();

        System.out.println("\nLISTA DE ALUGUÉIS PENDENTES: ");
        for (Aluguel aluguel : aluguels){
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Id: " + aluguel.getId() + " | ClienteID: " + aluguel.getClienteId() + " | FilmeID: " + aluguel.getFilmeId() + " | Data Aluguel: " + aluguel.getDataAluguel() + " | Data Devolução: " + aluguel.getDataDevolucao());
        }
    }

    private static void listarAluguel() {
        AluguelDAO aluguelDao = new AluguelDAO();

        List<Aluguel> aluguels = aluguelDao.listarAluguel();

        System.out.println("\nLISTA DE TODOS ALUGUÉIS: ");
        for(Aluguel aluguel : aluguels){
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.println("Id: " + aluguel.getId() + " | ClienteID: " + aluguel.getClienteId() + " | FilmeID: " + aluguel.getFilmeId() + " | Data Aluguel: " + aluguel.getDataAluguel() + " | Data Devolução: " + aluguel.getDataDevolucao());
        }
    }

    private static void listarFilme() {
        FilmeDAO filmeDao = new FilmeDAO();

        List<Filme> filmes = filmeDao.listarFilmes();

        System.out.println("\nLISTA DE TODOS FILMES: ");
        for (Filme filme: filmes){
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Id: " + filme.getId() + " Titulo: " + filme.getTitulo() + " Genero: " + filme.getGenero() + " Ano Lançamento: " + filme.getAnoLancamento());
        }
    }

    private static void listarCliente() {
        ClienteDAO clienteDao = new ClienteDAO();

        List<Cliente> clientes = clienteDao.listarClientes();

        System.out.println("\nLISTA DE TODOS CLIENTES: ");
        for (Cliente cliente : clientes){
            System.out.println("------------------------------------------------------------");
            System.out.println("Id: " + cliente.getId() + " Nome: " + cliente.getNome() + " Email: " + cliente.getEmail());
        }
    }

    private static void devolverFilme() {

        AluguelDAO aluguelDAO = new AluguelDAO();

        List<AluguelDevolucao> devolucaos = aluguelDAO.listarAluguelDevolucao();

        for (AluguelDevolucao devolucao : devolucaos){
            System.out.println("-------------------------------------------------------------------------------------------------------------");
            System.out.println("IdAluguel: " + devolucao.getId() + " | Nome Filme: " + devolucao.getTituloFilme() + " | Cliente: " + devolucao.getClienteNome() + " | Data de Locação: " + devolucao.getDataAluguel());
        }

        System.out.print("\nDigite o id do Aluguel que deseja devolver: ");
        int idAluguel = SC.nextInt();
        SC.nextLine();

        aluguelDAO.registroDevolucao(idAluguel);
    }

    private static void cadastrarFilme() {
        System.out.print("\nDigite o titulo do filme: ");
        String titulo = SC.nextLine();

        System.out.print("Digite o genero do filme: ");
        String genero = SC.nextLine();

        System.out.print("Digite o ano de lançamento: ");
        int anoLancamento = SC.nextInt();

        Filme filme = new Filme(titulo, genero, anoLancamento);
        FilmeDAO dao = new FilmeDAO();

        dao.cadastrarFilme(filme);
    }

    public static  void cadastrarCliente(){
        System.out.print("\nDigite o nome cliente: ");
        String nome = SC.nextLine();

        System.out.print("Digite o email do cliente: ");
        String email = SC.nextLine();

        Cliente cliente = new Cliente(nome, email);
        ClienteDAO dao = new ClienteDAO();

        dao.cadastrarCliente(cliente);
    }

    public static void alugarFilme(){

        ClienteDAO clienteDao = new ClienteDAO();

        List<Cliente> clientes = clienteDao.listarClientes();

        for(Cliente cliente: clientes){
            System.out.println("------------------------------------------------------------");
            System.out.println("Id: " + cliente.getId() + " Nome: " + cliente.getNome() + " Email: " + cliente.getEmail());
        }
        System.out.print("\nDigite o id do cliente que ira fazer locação: ");
        int idCliente = SC.nextInt();
        SC.nextLine();

        FilmeDAO filmeDao = new FilmeDAO();

        List<Filme> filmes = filmeDao.listarFilmes();

        for (Filme filme: filmes){
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("Id: " + filme.getId() + " Titulo: " + filme.getTitulo() + " Genero: " + filme.getGenero() + " Ano Lançamento: " + filme.getAnoLancamento());
        }
        System.out.print("\nDigite o id do filme que será alugado: ");
        int idFilme = SC.nextInt();
        SC.nextLine();

        LocalDate data = LocalDate.now();

        Aluguel aluguel = new Aluguel(idCliente, idFilme, data, null);

        AluguelDAO aluguelDao = new AluguelDAO();

        aluguelDao.cadastrarAluguel(aluguel);
    }
}
