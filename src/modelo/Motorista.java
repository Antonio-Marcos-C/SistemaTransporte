package modelo;

public class Motorista extends Pessoa {
    private boolean validado;

    public Motorista(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
        this.validado = false;
        this.validado = false;
    }

    public boolean isValidado() {
        return validado;
    }

    public void validar() {
        this.validado = true;
    }

    @Override
    public String toString() {
        return super.toString() + (validado ? " [VALIDADO]" : " [NÃO VALIDADO]");
    }
}
