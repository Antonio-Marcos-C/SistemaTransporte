package negocio;

import modelo.Motorista;

import java.util.ArrayList;

public class MotoristaService {

    public static void validarMotorista(Motorista motorista) {
        motorista.validar();
        System.out.println("Motorista validado com sucesso!");
    }

    public static void listarMotoristas(ArrayList<Motorista> motoristas) {
        try {
            if (motoristas == null || motoristas.isEmpty()) {
                System.out.println("⚠️ Nenhum motorista cadastrado.");
                return;
            }

            System.out.println("\n== Lista de Motoristas ==");
            for (int i = 0; i < motoristas.size(); i++) {
                Motorista m = motoristas.get(i);
                String status = m.isValidado() ? "✅ VALIDADO" : "❌ NÃO VALIDADO";
                System.out.println("[" + i + "] " + m.getNome() + " | CPF: " + m.getCpf() + " | Tel: " + m.getTelefone() + " | " + status);
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao listar motoristas: " + e.getMessage());
        }
    }
}

