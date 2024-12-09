package cliente;

import constantes.CategoriaCliente;

public class Empresa extends Cliente {

    private String cnpj;
    private double desconto;


    public Empresa(String nome, String cidade, String cnpj, double desconto) {
        super(nome, cidade, CategoriaCliente.EMPRESA);
        this.cnpj = cnpj;
        this.desconto = desconto;
    }


    @Override
    public String getId() {
        return cnpj;
    }

    @Override
    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "Empresa" +
                "\n  " + super.toString() +
                "\n  CNPJ: " + cnpj +
                "\n  Desconto: " + String.format("%.0f", desconto * 100) + "%" +
                "\n";
    }
}