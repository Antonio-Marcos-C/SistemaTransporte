package negocio;

import dados.Persistencia;

import java.util.ArrayList;

public class ViagemService {

    private static final String CAMINHO_CIDADES = "cidades_restritas.dat";
    private static final ArrayList<String> cidadesRestritas = Persistencia.carregar(CAMINHO_CIDADES);

    public static boolean cidadeComRestricaoParaMotos(String cidade) {
        return cidadesRestritas.stream().anyMatch(c -> c.equalsIgnoreCase(cidade));
    }

    public static void listarCidadesRestritas() {
        if (cidadesRestritas.isEmpty()) {
            System.out.println("Nenhuma cidade com restrição registrada.");
        } else {
            System.out.println("🛑 Cidades com restrição para motos:");
            cidadesRestritas.forEach(cidade -> System.out.println("• " + cidade));
        }
    }

    public static void adicionarCidadeRestrita(String cidade) {
        if (cidadeComRestricaoParaMotos(cidade)) {
            System.out.println("❌ Cidade já está na lista.");
            return;
        }
        cidadesRestritas.add(cidade);
        Persistencia.salvarAutomaticamente(CAMINHO_CIDADES, cidadesRestritas);
        System.out.println("✅ Cidade adicionada com sucesso!");
    }

    public static void removerCidadeRestrita(String cidade) {
        boolean removido = cidadesRestritas.removeIf(c -> c.equalsIgnoreCase(cidade));
        if (removido) {
            Persistencia.salvarAutomaticamente(CAMINHO_CIDADES, cidadesRestritas);
            System.out.println("✅ Cidade removida com sucesso!");
        } else {
            System.out.println("❌ Cidade não encontrada.");
        }
    }

    public static boolean veiculoPermitidoParaEntrega(modelo.Veiculo veiculo, String cidadeOrigem) {
        return !(veiculo instanceof modelo.Motocicleta && cidadeComRestricaoParaMotos(cidadeOrigem));
    }
}
