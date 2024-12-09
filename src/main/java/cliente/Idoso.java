package cliente;

import constantes.CategoriaCliente;

public class Idoso extends PessoaFisica {

    private final double desconto;

    public Idoso(String nome, int idade, String cpf, String cidade) {
        super(nome, idade, cpf, cidade, CategoriaCliente.IDOSO);
        desconto = 0.5;
    }

    public double getDesconto() {
        return desconto;
    }

    @Override
    public String toString() {
        return "Idoso " +
                "\n  Nome: " + super.getNome() +
                "\n  Idade: " + super.getIdade() +
                "\n  CPF: " + super.getCpf() +
                "\n  Cidade: " + super.getCidade() +
                "\n  Desconto: " + String.format("%.0f", desconto * 100) + "%" +
                "\n";
    }
}