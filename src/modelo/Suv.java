package modelo;

public class Suv extends Veiculo {

    public Suv(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public String toString() {
        return "[Suv] " + super.toString();
    }
}
