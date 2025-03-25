package modelo;

public class Motocicleta extends Veiculo {

    public Motocicleta(String placa, String modelo, String cor) {
        super(placa, modelo, cor);
    }

    @Override
    public String toString() {
        return "[Motocicleta] " + super.toString();
    }
}
