package modelo;

import java.util.ArrayList;
import java.util.List;

public class Motorista extends Pessoa {
    private boolean validado;
    private List<Avaliacao> avaliacoes;

    public Motorista(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
        this.validado = false;
        this.validado = false;
        this.avaliacoes = new ArrayList<>();
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    // Método para adicionar uma avaliação
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
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
