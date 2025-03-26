package modelo;

public class Pix extends FormaDePagamento {
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
        System.out.println("Pagamento d R$" + valor + " realizado via Pix: " + chavePix);
        return true;
    }
}
