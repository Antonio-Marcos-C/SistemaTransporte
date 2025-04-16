package controller;

import dados.Persistencia;
import modelo.Veiculo;
import modelo.Economico;
import modelo.Suv;
import modelo.Luxo;
import modelo.Motocicleta;
import negocio.ValidadorUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class VeiculoController {

    private final ArrayList<Veiculo> veiculos;

    public VeiculoController(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void cadastrarVeiculo(Scanner scanner) {
        try {
            System.out.print("Placa: ");
            String placa = scanner.nextLine().toUpperCase();

            while (!ValidadorUtils.placaValida(placa)) {
                System.out.println("❌ Placa inválida! Digite novamente:");
                placa = scanner.nextLine().toUpperCase();
            }

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
                System.out.println("✅ Veículo cadastrado com sucesso!");
            } else {
                System.out.println("❌ Tipo inválido.");
            }

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar veículo: " + e.getMessage());
        }
    }


    public void listarVeiculos() {
        try {
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo cadastrado.");
                return;
            }

            System.out.println("\n== Lista de Veículos ==");
            for (int i = 0; i < veiculos.size(); i++) {
                Veiculo v = veiculos.get(i);
                System.out.printf("[%d] %s | Placa: %s | Modelo: %s | Cor: %s%n",
                        i,
                        v.getClass().getSimpleName(),
                        v.getPlaca(),
                        v.getModelo(),
                        v.getCor()
                );
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar veículos: " + e.getMessage());
        }
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