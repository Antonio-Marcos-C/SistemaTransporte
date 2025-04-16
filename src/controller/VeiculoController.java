package controller;

import dados.Persistencia;
import modelo.Veiculo;
import modelo.Economico;
import modelo.Suv;
import modelo.Luxo;
import modelo.Motocicleta;

import java.util.ArrayList;
import java.util.Scanner;

public class VeiculoController {

    private final ArrayList<Veiculo> veiculos;

    public VeiculoController(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void cadastrarVeiculo(Scanner scanner) {
        System.out.print("Placa: ");
        String placa = scanner.nextLine();
        System.out.print("Modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Cor: ");
        String cor = scanner.nextLine();

        System.out.println("Tipo de Veículo: [1] Econômico, [2] SUV, [3] Luxo, [4] Motocicleta");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Veiculo veiculo = switch (tipo) {
            case 1 -> new Economico(placa, modelo, cor);
            case 2 -> new Suv(placa, modelo, cor);
            case 3 -> new Luxo(placa, modelo, cor);
            case 4 -> new Motocicleta(placa, modelo, cor);
            default -> null;
        };

        if (veiculo != null) {
            veiculos.add(veiculo);
            Persistencia.salvarAutomaticamente("veiculos.dat", veiculos);
            System.out.println("Veículo cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    public void listarVeiculos() {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("\n== Lista de Veículos ==");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.println("[" + i + "] " + v.getClass().getSimpleName() +
                    " | Placa: " + v.getPlaca() +
                    " | Modelo: " + v.getModelo() +
                    " | Cor: " + v.getCor());
        }
    }

    public void atualizarVeiculo(Scanner scanner) {
        listarVeiculos();
        if (veiculos.isEmpty()) return;

        System.out.print("Digite o número do veículo que deseja atualizar: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= veiculos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Veiculo veiculo = veiculos.get(idx);

        System.out.println("\n--- Dados atuais do veículo ---");
        System.out.println("Tipo: " + veiculo.getClass().getSimpleName());
        System.out.println("Placa: " + veiculo.getPlaca());
        System.out.println("Modelo: " + veiculo.getModelo());
        System.out.println("Cor: " + veiculo.getCor());

        System.out.print("Deseja alterar a placa? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nova placa: ");
            veiculo.setPlaca(scanner.nextLine());
        }

        System.out.print("Deseja alterar o modelo? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Novo modelo: ");
            veiculo.setModelo(scanner.nextLine());
        }

        System.out.print("Deseja alterar a cor? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Nova cor: ");
            veiculo.setCor(scanner.nextLine());
        }

        Persistencia.salvarAutomaticamente("veiculos.dat", veiculos);
        System.out.println("Veículo atualizado com sucesso!");
    }

    public void removerVeiculo(Scanner scanner) {
        listarVeiculos();
        if (veiculos.isEmpty()) return;

        System.out.print("Digite o número do veículo que deseja remover: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= veiculos.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Veiculo removido = veiculos.remove(idx);
        Persistencia.salvarAutomaticamente("veiculos.dat", veiculos);
        System.out.println("Veículo removido: " + removido);
    }
}