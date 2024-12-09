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
    private String cnpj;
    private List<Cliente> clientesAssociados;
    private HashMap<Evento, ArrayList<Cliente>> tabelaEventoCliente;

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

    public double venderIngressos(String idCliente, String nomeEvento, int qntIng)
            throws IllegalArgumentException {

        Cliente cliente = filtrarCliente(idCliente);

        Evento evento = filtrarEvento(nomeEvento);

        if (evento.getQuantIngVendidos() + qntIng <= evento.getCapacidade()) {
            return evento.venderIng(cliente, qntIng);
        } else {
            throw new IllegalArgumentException("Capacidade do evento excedida");
        }
    }

    public double valorIngresso(String nomeEvento) {
        Evento e = filtrarEvento(nomeEvento);
        return e.getValorIngresso();
    }

    public double valorIngresso(String nomeEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(nomeEvento);
        return e.getValorIngresso(categoriaCliente);
    }

    public int totalIngressosVendidos() {
        return tabelaEventoCliente.keySet().stream()
                .mapToInt(Evento::getQuantIngVendidos)
                .sum();
    }

    public int totalIngressosVendidos(String nomeEvento) {
        Evento e = filtrarEvento(nomeEvento);
        return e.getQuantIngVendidos();
    }

    public double totalIngressosVendidos(String nomeEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(nomeEvento);
        return e.getQuantIngVendidos(categoriaCliente);
    }

    public double receitaTotal() {
        return tabelaEventoCliente.keySet().stream()
                .mapToDouble(Evento::valorTotalIngressos)
                .sum();
    }

    public double receitaTotal(String nomeEvento) {
        Evento e = filtrarEvento(nomeEvento);
        return e.valorTotalIngressos();
    }

    public double receitaTotal(String nomeEvento, CategoriaCliente categoriaCliente) {
        Evento e = filtrarEvento(nomeEvento);
        return e.valorTotalIngressos(categoriaCliente);
    }

    public double receitaTotal(String nomeEvento, String idCliente) {
        Evento e = filtrarEvento(nomeEvento);
        return e.getListaIng().stream()
                .filter(evento -> evento.getCliente().getId().equals(idCliente))
                .mapToDouble(Ingresso::getValor)
                .sum();
    }

    private Evento filtrarEvento(String nomeEvento) {
        return tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getNome().equals(nomeEvento))
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

    public List<Ingresso> listarIngressos(Evento evento,String idCliente) {
        return evento.listarIngressosCliente(idCliente);
    }

    public ArrayList<Ingresso> listarIngressos(String nomeEvento) {
        return tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getNome().equals(nomeEvento))
                .findFirst()
                .map(Evento::getListaIng)
                .orElseThrow(() -> new IllegalArgumentException("Evento não encontrado"));
    }

    public List<Ingresso> listarIngressos(String nomeEvento, CategoriaCliente categoriaCliente) {
        Evento e = tabelaEventoCliente.keySet().stream()
                .filter(evento -> evento.getNome().equals(nomeEvento))
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