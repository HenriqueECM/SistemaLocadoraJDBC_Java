package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class AluguelDevolucao extends Aluguel {
    private String tituloFilme, clienteNome;
    public AluguelDevolucao (LocalDate dataAluguel, int idAluguel, String tituloFilme, String clienteNome){
        super(dataAluguel, idAluguel);
        this.clienteNome = clienteNome;
        this.tituloFilme = tituloFilme;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }
}