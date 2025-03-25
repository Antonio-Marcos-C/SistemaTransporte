package modelo;

import java.io.Serializable;

public abstract class FormaDePagamento implements Serializable {
    public abstract boolean processarPagamento(double valor);
}
