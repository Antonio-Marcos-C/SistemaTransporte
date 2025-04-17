package controller;

import negocio.ViagemService;

import java.util.Scanner;

public class ViagemController {

    private final Scanner scanner;

    public ViagemController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void listarCidadesRestritas() {
        try {
            ViagemService.listarCidadesRestritas();
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar cidades: " + e.getMessage());
        }
    }

    public void adicionarCidadeRestrita() {
        try {
            System.out.print("Digite o nome da cidade a ser adicionada: ");
            String cidade = scanner.nextLine();
            ViagemService.adicionarCidadeRestrita(cidade);
        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar cidade: " + e.getMessage());
        }
    }

    public void removerCidadeRestrita() {
        try {
            System.out.print("Digite o nome da cidade a ser removida: ");
            String cidade = scanner.nextLine();
            ViagemService.removerCidadeRestrita(cidade);
        } catch (Exception e) {
            System.out.println("❌ Erro ao remover cidade: " + e.getMessage());
        }
    }
}
