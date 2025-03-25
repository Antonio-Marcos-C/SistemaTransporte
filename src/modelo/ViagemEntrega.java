package modelo;

public class ViagemEntrega extends Viagem {

    public ViagemEntrega(Cliente cliente, Motorista motorista, Veiculo veiculo,
                         String origem, String destino, double valor) {
        super(cliente, motorista, veiculo, origem, destino, valor);
    }

    @Override
    public void iniciar() {
        if (!motorista.isValidado()) {
            System.out.println("Motorista não está validado. Viagem não pode iniciar.");
            return;
        }

        if (!negocio.ViagemService.veiculoPermitidoParaEntrega(veiculo, origem)) {
            System.out.println("Entregas com motocicleta não são permitidas nesta cidade.");
            return;
        }


        System.out.println("Iniciando viagem de entrega...");
        System.out.println(this.toString());
    }

    private boolean cidadeComRestricao(String cidade) {
        // Simulação de cidades com restrição para motos
        return cidade.equalsIgnoreCase("Recife") || cidade.equalsIgnoreCase("São Paulo");
    }
}
