package negocio;

import modelo.FormaDePagamento;

public class PagamentoService {

    public static boolean realizarPagamento(FormaDePagamento forma, double valor) {
        System.out.println("\nProcessando pagamento no valor de R$" + valor + "...");

        boolean sucesso = forma.processarPagamento(valor);

        if (sucesso) {
            System.out.println(" Pagamento realizado com sucesso!");
        } else {
            System.out.println(" Pagamento recusado.");
        }

        return sucesso;
    }
}
