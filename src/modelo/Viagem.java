package modelo;

import java.io.Serializable;

public abstract class Viagem implements Serializable {
    protected Cliente cliente;
    protected Motorista motorista;
    protected Veiculo veiculo;
    protected String origem;
    protected String destino;
    protected double valor;
    protected boolean concluida;

    public Viagem(Cliente cliente, Motorista motorista, Veiculo veiculo, String origem, String destino, double valor) {
        this.cliente = cliente;
        this.motorista = motorista;
        this.veiculo = veiculo;
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.concluida = false;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public abstract void iniciar();

    public void concluir() {
        this.concluida = true;
        System.out.println("Viagem concluída.");
    }

    @Override
    public String toString() {
        return "Origem: " + origem + ", Destino: " + destino + ", Valor: R$" + valor +
                ", Cliente: " + cliente.getNome() +
                ", Motorista: " + motorista.getNome() +
                ", Veículo: " + veiculo.getModelo() +
                ", Concluída: " + (concluida ? "Sim" : "Não");
    }
}
