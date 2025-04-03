package modelo;

public class Pix extends FormaDePagamento {
    private static final long serialVersionUID = 1L;

    private String chavePix;

    // Construtor
    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getChavePix() {
        return chavePix;
    }

    @Override
    public boolean processarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via Pix: " + chavePix);
        return true;
    }
}
