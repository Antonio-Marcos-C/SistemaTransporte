package negocio;

import modelo.Motorista;

import java.util.ArrayList;

public class MotoristaService {

    public static void validarMotorista(Motorista motorista) {
        motorista.validar();
        System.out.println("Motorista validado com sucesso!");
    }

    public static void listarMotoristas(ArrayList<Motorista> motoristas) {
        for (int i = 0; i < motoristas.size(); i++) {
            System.out.println("[" + i + "] " + motoristas.get(i).getNome() +
                    (motoristas.get(i).isValidado() ? " (VALIDADO)" : " (NÃO VALIDADO)"));
        }
    }

    public static boolean motoristaDisponivel(Motorista motorista) {

        return motorista.isValidado();
    }
}
