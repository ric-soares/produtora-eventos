package evento;

import cliente.Cliente;
import constantes.CategoriaCliente;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Evento {

    private String nome;
    private String atracao;
    private String cidade;
    private int duracao;
    private int capacidade;
    private double valorIng;
    private int proxNumIng = 100;
    private ArrayList<Ingresso> listaIng;

    public Evento(String nome, String atracao, String cidade, int duracao, int capacidade, double valorIng) {

        this.nome = nome;
        this.atracao = atracao;
        this.cidade = cidade;
        this.duracao = duracao;
        this.capacidade = capacidade;
        this.valorIng = valorIng;
        listaIng = new ArrayList<>();
    }

    public double venderIng(Cliente cli, int qntIng) {

        double valor = valorIng - cli.getDesconto() * valorIng;

        for (int i=0 ; i < qntIng ; i++){
            Ingresso ing = new Ingresso(proxNumIng, valor, cli);
            listaIng.add(ing);
            proxNumIng++;
        }

        return valor * qntIng;
    }

    public ArrayList<Ingresso> getListaIng() {
        return listaIng;
    }

    public List<Ingresso> getListaIng(CategoriaCliente categoria) {
        return listaIng.stream()
                .filter(i ->  i.getCliente()
                        .getCategoria()
                        .equals(categoria))
                .toList();

    }

    public List<Ingresso> listarIngressosCliente(String id) {
        return listaIng.stream()
                .filter(i ->  i.getCliente()
                        .getId()
                        .equals(id))
                .toList();
    }

    public double valorTotalIngressos() {
        return listaIng.stream()
                .mapToDouble(Ingresso::getValor)
                .sum();
    }

    public double valorTotalIngressos(CategoriaCliente categoria) {
        return listaIng.stream()
                .filter(i ->  i.getCliente()
                        .getCategoria()
                        .equals(categoria)
                )
                .mapToDouble(Ingresso::getValor)
                .sum();
    }

    public double getQuantIngVendidos(CategoriaCliente categoria) {
        return listaIng.stream()
                .filter(i ->  i.getCliente()
                        .getCategoria()
                        .equals(categoria)
                )
                .count();
    }

    public double getValorIngresso(CategoriaCliente categoria) {
        return listaIng.stream()
                .filter(i ->  i.getCliente()
                        .getCategoria()
                        .equals(categoria)
                )
                .mapToDouble(Ingresso::getValor)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Nenhum ingresso encontrado para a categoria: " + categoria));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtracao() {
        return atracao;
    }

    public void setAtracao(String atracao) {
        this.atracao = atracao;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getQuantIngVendidos() {
        return listaIng.size();
    }

    public double getValorIngresso() {
        return valorIng;
    }

    @Override
    public String toString() {
        return "Evento " +
                "\n  Nome: " + nome +
                "\n  Atração: " + atracao +
                "\n  Cidade: " + cidade +
                "\n  Duração: " + duracao + " Min" +
                "\n  Capacidade: " + capacidade + " Pessoas" +
                "\n";
    }
}