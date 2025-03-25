package negocio;

import modelo.Motocicleta;
import modelo.Veiculo;

public class ViagemService {

    public static boolean cidadeComRestricaoParaMotos(String cidade) {
        return cidade.equalsIgnoreCase("Recife") || cidade.equalsIgnoreCase("São Paulo");
    }

    public static boolean veiculoPermitidoParaEntrega(Veiculo veiculo, String cidadeOrigem) {
        if (veiculo instanceof Motocicleta && cidadeComRestricaoParaMotos(cidadeOrigem)) {
            return false;
        }
        return true;
    }
}
