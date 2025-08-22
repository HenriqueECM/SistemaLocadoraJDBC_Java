package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class Aluguel {
    private int id, clienteId, filmeId;
    private Date dataDevolucao;
    private LocalDate dataAluguel;
    private Filme filme;
    private Cliente cliente;

    public Aluguel(int id, int clienteId, int filmeId, LocalDate dataAluguel, Date dataDevolucao){
        this.id = id;
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    public Aluguel(int clienteId, int filmeId, LocalDate dataAluguel, Date dataDevolucao){
        this.clienteId = clienteId;
        this.filmeId = filmeId;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }

    // Sobrecarga para devolução
    public Aluguel(LocalDate dataAluguel, int id){
        this.id = id;
        this.dataAluguel = dataAluguel;
    }

    public Aluguel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
