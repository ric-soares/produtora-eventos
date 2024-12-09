package evento;

import cliente.Cliente;

public class Ingresso {

    private int idIngresso;
    private double valor;
    private final Cliente cliente;

    public Ingresso (int id, double valor, Cliente cliente) {

        this.idIngresso = id;
        this.valor = valor;
        this.cliente = cliente;

    }

    public int getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(int idIngresso) {
        this.idIngresso = idIngresso;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public String toString() {
        return "Ingresso - " +
                "ID: " + idIngresso +
                "\n  Valor: R$" + String.format("%.2f", valor) +
                " -  Comprador: " + cliente.getNome() +
                "\n";
    }
}