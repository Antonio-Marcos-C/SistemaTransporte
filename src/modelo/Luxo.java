package modelo;

public class Luxo extends Veiculo {

    public Luxo(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public String toString() {
        return "[Luxo] " + super.toString();
    }
}
