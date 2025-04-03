package modelo;

public class CartaoCredito extends FormaDePagamento {
    private static final long serialVersionUID = 1L;

    private String numeroCartao;
    private double limiteDisponivel;

    public CartaoCredito(String numeroCartao, double limiteDisponivel) {
        this.numeroCartao = numeroCartao;
        this.limiteDisponivel = limiteDisponivel;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public void setLimiteDisponivel(double limiteDisponivel) {
        this.limiteDisponivel = limiteDisponivel;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    @Override
    public boolean processarPagamento(double valor) {
        if (valor <= limiteDisponivel) {
            limiteDisponivel -= valor;
            System.out.println("Pagamento de R$" + valor + " realizado com cartão " + numeroCartao);
            return true;
        } else {
            System.out.println("Pagamento recusado. Limite insuficiente.");
            return false;
        }
    }
}
