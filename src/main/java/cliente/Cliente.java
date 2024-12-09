package cliente;

import constantes.CategoriaCliente;

public abstract class Cliente {

    private String nome;
    private String cidade;
    private CategoriaCliente categoria;

    public Cliente(String nome, String cidade, CategoriaCliente categoria) {
        this.nome = nome;
        this.cidade = cidade;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String novoNome) {
        this.nome=novoNome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String novaCidade) {
        this.cidade=novaCidade;
    }

    public abstract String getId();

    public abstract double getDesconto();

    public CategoriaCliente getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "Cidade: "+ cidade;
    }
}