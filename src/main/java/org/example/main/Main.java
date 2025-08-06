package org.example.main;

import org.example.dao.ClienteDAO;
import org.example.dao.FilmeDAO;
import org.example.model.Cliente;
import org.example.model.Filme;

import java.sql.SQLOutput;
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
}
