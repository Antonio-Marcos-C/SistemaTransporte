package negocio;

import modelo.Avaliacao;
import dados.Persistencia;
import java.util.ArrayList;

public class AvaliacaoService {
    private static final String CAMINHO = "avaliacoes.dat";

    public static void salvarAvaliacao(Avaliacao avaliacao, ArrayList<Avaliacao> lista) {
        lista.add(avaliacao);
        Persistencia.salvar(CAMINHO, lista);
    }

    public static void listarAvaliacoes(ArrayList<Avaliacao> lista) {
        if (lista.isEmpty()) {
            System.out.println("Nenhuma avaliação registrada.");
        } else {
            lista.forEach(System.out::println);
        }
    }
}
