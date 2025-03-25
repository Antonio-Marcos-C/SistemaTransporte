package modelo;

import java.util.ArrayList;
import modelo.FormaDePagamento;

import java.util.ArrayList;


public class Cliente extends Pessoa {
    private ArrayList<FormaDePagamento> formasDePagamento = new ArrayList<>();

    public Cliente(String nome, String cpf, String telefone) {
        super(nome, cpf, telefone);
    }

    public void adicionarFormaPagamento(FormaDePagamento forma) {
        formasDePagamento.add(forma);
    }

    public ArrayList<FormaDePagamento> getFormasDePagamento() {
        return formasDePagamento;
    }
}
