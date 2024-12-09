package cliente;

import constantes.CategoriaCliente;

public class Estudante extends PessoaFisica {

    private double desconto;
    private int numCarteiraEstudante;

    public Estudante(String nome, int idade, String cpf, String cidade, int numCarteirinha) {
        super(nome, idade, cpf, cidade, CategoriaCliente.ESTUDANTE);
        this.numCarteiraEstudante = numCarteirinha;
        desconto = 0.5;
    }

    public int getNumCarteiraEstudante() {
        return numCarteiraEstudante;
    }

    public void setNumCarteiraEstudante(int numCarteirinha) {
        this.numCarteiraEstudante = numCarteirinha;
    }

    public double getDesconto() {
        return desconto;
    }
    @Override
    public String toString() {
        return "Estudante " +
                "\n  Nome: " + super.getNome() +
                "\n  Idade: " + super.getIdade() +
                "\n  CPF: " + super.getCpf() +
                "\n  Cidade: " + super.getCidade() +
                "\n  Número carteirinha: " + numCarteiraEstudante +
                "\n  Desconto: " + String.format("%.0f", desconto * 100) + "%" +
                "\n";
    }
}