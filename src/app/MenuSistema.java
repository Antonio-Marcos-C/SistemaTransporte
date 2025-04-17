package app;

import controller.*;
import dados.Persistencia;
import modelo.Cliente;
import modelo.Motorista;
import modelo.Veiculo;
import modelo.Viagem;

import java.util.ArrayList;
import java.util.Scanner;


public class MenuSistema {
    private static final ArrayList<Cliente> clientes = Persistencia.carregar("clientes.dat");
    private static final ArrayList<Motorista> motoristas = Persistencia.carregar("motoristas.dat");
    private static final ArrayList<Viagem> viagens = Persistencia.carregar("viagens.dat");
    private static final ArrayList<Veiculo> veiculos = Persistencia.carregar("veiculos.dat");

    public static final Scanner scanner = new Scanner(System.in);
    private static final ClienteController clienteController = new ClienteController(clientes, viagens);
    private static final MotoristaController motoristaController = new MotoristaController(motoristas);
    private static final VeiculoController veiculoController = new VeiculoController(veiculos);
    private static final SolicitarViagemController solicitarViagemController = new SolicitarViagemController(clientes, motoristas, veiculos, viagens);
    private static final ViagemController viagemController = new ViagemController(scanner);





    public static void main(String[] args) {
        int tipoUsuario;

        do {
            System.out.println("Bem-vindo ao Sistema de Transporte!");
            System.out.println("Escolha o tipo de usuário:");
            System.out.println("[1] Cliente");
            System.out.println("[2] Motorista");
            System.out.println("[3] Administrador");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");
            tipoUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (tipoUsuario) {
                case 1 -> menuCliente();
                case 2 -> menuMotorista();
                case 3 -> menuAdministrador();
                case 4 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (tipoUsuario != 4);
    }

    private static void menuAdministrador() {
        int opcao;
        do {
            System.out.println("\n===== MENU ADMINISTRADOR =====");
            System.out.println("1. Cadastrar Motorista");
            System.out.println("2. Atualizar Motorista");
            System.out.println("3. Remover Motorista");
            System.out.println("4. Listar Motoristas");
            System.out.println("5. Validar Motorista");
            System.out.println("6. Cadastrar Cliente");
            System.out.println("7. Atualizar Cliente");
            System.out.println("8. Remover Cliente");
            System.out.println("9. Listar Clientes");
            System.out.println("10. Listar Cidades com Restrição");
            System.out.println("11. Adicionar Cidade com Restrição");
            System.out.println("12. Remover Cidade com Restrição");
            System.out.println("13. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> motoristaController.cadastrarMotorista(scanner);
                case 2 -> motoristaController.atualizarMotorista(scanner);
                case 3 -> motoristaController.removerMotorista(scanner);
                case 4 -> motoristaController.listarMotoristas();
                case 5 -> motoristaController.validarMotorista(scanner);
                case 6 -> clienteController.cadastrarCliente();
                case 7 -> clienteController.atualizarCliente();
                case 8 -> clienteController.removerCliente();
                case 9 -> clienteController.listarClientes();
                case 10 -> viagemController.listarCidadesRestritas();
                case 11 -> viagemController.adicionarCidadeRestrita();
                case 12 -> viagemController.removerCidadeRestrita();
                case 13 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 13);
    }

    private static void menuCliente() {
        int opcao;
        do {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("1. Adicionar Forma de Pagamento");
            System.out.println("2. Ver Minhas Viagens");
            System.out.println("3. Avaliar Motorista");
            System.out.println("4. Solicitar Viagem");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> clienteController.adicionarFormaDePagamento();
                case 2 -> clienteController.listarViagensDoCliente();
                case 3 -> clienteController.avaliarMotorista(motoristas);
                case 4 -> solicitarViagemController.solicitarViagem();
                case 5 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

    private static void menuMotorista() {
        int opcao;
        do {
            System.out.println("\n===== MENU MOTORISTA =====");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Remover Veículo");
            System.out.println("3. Avaliar Passageiro");
            System.out.println("4. Ver Avaliações");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> veiculoController.cadastrarVeiculo(scanner);
                case 2 -> veiculoController.removerVeiculo(scanner);
                case 3 -> motoristaController.avaliarPassageiro(clientes);
                case 4 -> motoristaController.listarAvaliacoesMotorista();
                case 5 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }


        } while (opcao != 4);
    }

}
