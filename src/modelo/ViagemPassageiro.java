package modelo;

public class ViagemPassageiro extends Viagem {

    public ViagemPassageiro(Cliente cliente, Motorista motorista, Veiculo veiculo,
                            String origem, String destino, double valor) {
        super(cliente, motorista, veiculo, origem, destino, valor);
    }

    @Override
    public void iniciar() {
        if (!motorista.isValidado()) {
            System.out.println("Motorista não está validado. Viagem não pode iniciar.");
            return;
        }

        System.out.println("Iniciando viagem de passageiro...");
        System.out.println(this.toString());
    }
}
