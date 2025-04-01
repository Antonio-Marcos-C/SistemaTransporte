package modelo;

import java.util.ArrayList;
import java.util.List;
import modelo.Avaliacao;
import modelo.FormaDePagamento;

import java.util.ArrayList;


public class Cliente extends Pessoa {
    private ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();
    private List<Avaliacao> avaliacoes;

    public Cliente(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao); // Adiciona a avaliação ao cliente
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void adicionarFormaPagamento(FormaDePagamento forma) {
        formasDePagamento.add(forma);
    }

    public ArrayList<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }
}
