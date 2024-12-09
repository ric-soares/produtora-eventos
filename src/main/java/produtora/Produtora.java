package produtora;

import cliente.Cliente;
import constantes.CategoriaCliente;
import evento.Evento;
import evento.Ingresso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Produtora {

    private String nome;
    private final String cnpj;
    private final List<Cliente> clientesAssociados;
    private final HashMap<Evento, ArrayList<Cliente>> tabelaEventoCliente;

    public Produtora(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.clientesAssociados = new ArrayList<>();
        this.tabelaEventoCliente = new HashMap<>();
    }

    public boolean adicionarEvento(Evento novoEvento) {
        if (!tabelaEventoCliente.containsKey(novoEvento)) {
            tabelaEventoCliente.put(novoEvento, new ArrayList<>());
            return true;
        }
        return false;
    }

    public boolean associarCliente(Cliente novoCliente) {
        if (clientesAssociados.stream().noneMatch(cliente -> cliente.getId().equals(novoCliente.getId()))) {
            clientesAssociados.add(novoCliente);
            return true;
        }

        return false;
    }

    public double venderIngressos(String idCliente, String idEvento, int qntIng)
            throws IllegalArgumentException {

        Cliente cliente = filtrarCliente(idCliente);

        Evento evento = filtrarEvento(idEvento);

        if (evento.getQuantIngVendidos() + qntIng <= evento.getCapacidade()) {
            return evento.venderIng(cliente, qntIng);
        } else {
            throw new IllegalArgumentException("Capacidade do evento excedida");
        }
    }

    public double valorIngresso(String idEvento) {
        Evento e = filtrarEvento(idEvento);
        return e.getValorIngresso();
    }

    public double valorIngresso(String idEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(idEvento);
        return e.getValorIngresso(categoriaCliente);
    }

    public int totalIngressosVendidos() {
        return tabelaEventoCliente.keySet().stream()
                .mapToInt(Evento::getQuantIngVendidos)
                .sum();
    }

    public int totalIngressosVendidos(String idEvento) {
        Evento e = filtrarEvento(idEvento);
        return e.getQuantIngVendidos();
    }

    public double totalIngressosVendidos(String idEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(idEvento);
        return e.getQuantIngVendidos(categoriaCliente);
    }

    public double receitaTotal() {
        return tabelaEventoCliente.keySet().stream()
                .mapToDouble(Evento::valorTotalIngressos)
                .sum();
    }

    public double receitaTotal(String idEvento) {
        Evento e = filtrarEvento(idEvento);
        return e.valorTotalIngressos();
    }

    public double receitaTotal(String idEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(idEvento);
        return e.valorTotalIngressos(categoriaCliente);
    }

    public double receitaTotal(String idEvento, String idCliente) {
        Evento e = filtrarEvento(idEvento);
        return e.getListaIng().stream()
                .filter(evento -> evento.getCliente().getId().equals(idCliente))
                .mapToDouble(Ingresso::getValor)
                .sum();
    }

    private Evento filtrarEvento(String idEvento) {
        return tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getIdEvento().equals(idEvento))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
    }

    private Cliente filtrarCliente(String idCliente) {
        return clientesAssociados.stream()
                .filter(c -> c.getId().equals(idCliente))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public String listarClientes() {
        StringBuilder clientes = new StringBuilder();

        for (Cliente c : clientesAssociados) {
            clientes.append(c).append("\n");
        }

        return clientes.toString();
    }

    public String listarEventos() {
        StringBuilder resultado = new StringBuilder();

        for (Evento e : tabelaEventoCliente.keySet()) {
            resultado.append(e).append("\n");
        }

        return resultado.toString();
    }

    public List<Ingresso> listarIngressos(String idEvento, String idCliente) {
        return tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getIdEvento().equals(idEvento))
                .findFirst()
                .map(evento -> evento.listarIngressosCliente(idCliente))
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
    }

    public ArrayList<Ingresso> listarIngressos(String idEvento) {
        return tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getIdEvento().equals(idEvento))
                .findFirst()
                .map(Evento::getListaIng)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
    }

    public List<Ingresso> listarIngressos(String idEvento, CategoriaCliente categoriaCliente) {
        Evento e = tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getIdEvento().equals(idEvento))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));

        return e.getListaIng(categoriaCliente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public String toString() {
        return "Produtora " +
                "\n  Nome: " + nome +
                "\n  CNPJ: " + cnpj +
                "\n";
    }
}