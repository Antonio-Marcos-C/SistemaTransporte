package modelo;

public class Economico extends Veiculo {

    public Economico(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public String toString() {
        return "[Econômico] " + super.toString();
    }
}
