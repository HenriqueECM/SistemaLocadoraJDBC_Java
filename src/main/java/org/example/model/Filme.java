package org.example.model;

public class Filme {
    private int id, anoLancamento;
    private String titulo, genero;

    public Filme (int id, String titulo, String genero, int anoLancamento){
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.id = id;
        this.titulo = titulo;
    }

    public Filme (String titulo, String genero, int anoLancamento){
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
