package cliente;

import constantes.CategoriaCliente;

public class PessoaFisica extends Cliente {

    private int idade;
    private String cpf;

    public PessoaFisica(String nome, int idade, String cpf, String cidade) {
        super(nome, cidade, CategoriaCliente.PESSOA_FISICA);
        this.cpf = cpf;
        this.idade = idade;
    }

    public PessoaFisica(String nome, int idade, String cpf, String cidade, CategoriaCliente categoria) {
        super(nome, cidade, categoria);
        this.cpf = cpf;
        this.idade = idade;
    }

    @Override
    public String getId() {
        return cpf;
    }

    @Override
    public double getDesconto() {
        return 0;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String toString() {
        return "Cliente Normal: " +
                "\n  " + super.toString() +
                "\n  Idade: " + idade +
                "\n  CPF: " + cpf +
                "\n";
    }
}