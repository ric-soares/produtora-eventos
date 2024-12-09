package evento;

import cliente.Cliente;

public class Ingresso {

    private int id;
    private double valor;
    private Cliente cliente;

    public Ingresso (int id, double valor, Cliente cliente) {

        this.id = id;
        this.valor = valor;
        this.cliente = cliente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "ID: " + id +
                "\n  Valor: R$" + String.format("%.2f", valor) +
                " -  Comprador: " + cliente.getNome() +
                "\n";
    }
}