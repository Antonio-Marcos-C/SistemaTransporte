package controller;

import dados.Persistencia;
import modelo.*;
import negocio.PagamentoService;

import java.util.ArrayList;
import java.util.Scanner;

public class SolicitarViagemController {

    private final ArrayList<Cliente> clientes;
    private final ArrayList<Motorista> motoristas;
    private final ArrayList<Veiculo> veiculos;
    private final ArrayList<Viagem> viagens;

    private final Scanner scanner = new Scanner(System.in);

    public SolicitarViagemController(ArrayList<Cliente> clientes, ArrayList<Motorista> motoristas,
                                     ArrayList<Veiculo> veiculos, ArrayList<Viagem> viagens) {
        this.clientes = clientes;
        this.motoristas = motoristas;
        this.veiculos = veiculos;
        this.viagens = viagens;
    }

    public void solicitarViagem() {
        try {
            if (clientes.isEmpty() || motoristas.isEmpty() || veiculos.isEmpty()) {
                System.out.println("⚠️ É necessário ter cliente, motorista e veículo cadastrados.");
                return;
            }

            System.out.println("== Solicitar Viagem ==");

            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idxCliente = Integer.parseInt(scanner.nextLine());
            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("❌ Índice de cliente inválido.");
                return;
            }
            Cliente cliente = clientes.get(idxCliente);

            listarMotoristas();
            System.out.print("Escolha o motorista: ");
            int idxMotorista = Integer.parseInt(scanner.nextLine());
            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("❌ Índice de motorista inválido.");
                return;
            }
            Motorista motorista = motoristas.get(idxMotorista);
            if (!motorista.isValidado()) {
                System.out.println("❌ Este motorista ainda não está validado.");
                return;
            }

            listarVeiculos();
            System.out.print("Escolha o veículo: ");
            int idxVeiculo = Integer.parseInt(scanner.nextLine());
            if (idxVeiculo < 0 || idxVeiculo >= veiculos.size()) {
                System.out.println("❌ Índice de veículo inválido.");
                return;
            }
            Veiculo veiculo = veiculos.get(idxVeiculo);

            System.out.print("Origem: ");
            String origem = scanner.nextLine();

            System.out.print("Destino: ");
            String destino = scanner.nextLine();

            System.out.print("Valor da viagem: R$ ");
            double valor = Double.parseDouble(scanner.nextLine());

            if (cliente.getFormasDePagamento().isEmpty()) {
                System.out.println("⚠️ Cliente não possui formas de pagamento cadastradas.");
                return;
            }

            System.out.println("\nFormas de Pagamento:");
            ArrayList<FormaDePagamento> formas = cliente.getFormasDePagamento();
            for (int i = 0; i < formas.size(); i++) {
                System.out.println("[" + i + "] " + formas.get(i).getClass().getSimpleName());
            }
            System.out.print("Escolha a forma de pagamento: ");
            int idxForma = Integer.parseInt(scanner.nextLine());
            if (idxForma < 0 || idxForma >= formas.size()) {
                System.out.println("❌ Índice de pagamento inválido.");
                return;
            }
            FormaDePagamento forma = formas.get(idxForma);

            boolean pago = PagamentoService.realizarPagamento(forma, valor);
            if (!pago) {
                System.out.println("❌ Pagamento não autorizado.");
                return;
            }

            System.out.println("Tipo de viagem: [1] Passageiro | [2] Entrega");
            int tipoViagem = Integer.parseInt(scanner.nextLine());

            Viagem viagem;
            if (tipoViagem == 1) {
                viagem = new ViagemPassageiro(cliente, motorista, veiculo, origem, destino, valor);
            } else if (tipoViagem == 2) {
                viagem = new ViagemEntrega(cliente, motorista, veiculo, origem, destino, valor);
            } else {
                System.out.println("❌ Tipo de viagem inválido.");
                return;
            }

            viagem.iniciar();
            System.out.print("Viagem em andamento");
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                System.out.print(".");
            }
            System.out.println();
            viagem.concluir();

            viagens.add(viagem);
            Persistencia.salvarAutomaticamente("viagens.dat", viagens);
            System.out.println("✅ Viagem registrada com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Digite apenas números onde for solicitado.");
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    private void listarClientes() {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            System.out.println("[" + i + "] " + c.getNome());
        }
    }

    private void listarMotoristas() {
        for (int i = 0; i < motoristas.size(); i++) {
            Motorista m = motoristas.get(i);
            System.out.println("[" + i + "] " + m.getNome() + (m.isValidado() ? " ✅" : " ❌"));
        }
    }

    private void listarVeiculos() {
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo v = veiculos.get(i);
            System.out.println("[" + i + "] " + v);
        }
    }
}
