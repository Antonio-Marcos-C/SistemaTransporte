package modelo;

public class Dinheiro extends FormaDePagamento {
    private static final long serialVersionUID = 1L;

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado em dinheiro.");
        return true;
    }
}
