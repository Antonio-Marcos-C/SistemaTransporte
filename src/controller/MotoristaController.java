package controller;

import modelo.Cliente;
import modelo.Motorista;
import modelo.Avaliacao;
import dados.Persistencia;
import negocio.MotoristaService;
import negocio.ValidadorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static app.MenuSistema.scanner;

public class MotoristaController {

    private final ArrayList<Motorista> motoristas;

    public MotoristaController(ArrayList<Motorista> motoristas) {
        this.motoristas = motoristas;
    }

    public void cadastrarMotorista(Scanner scanner) {
        try {
            System.out.println("🚗 Cadastro de Motorista");

            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            String cpf;
            do {
                System.out.print("CPF (somente números): ");
                cpf = scanner.nextLine();
                if (!ValidadorUtils.validarCPF(cpf)) {
                    System.out.println("❌ CPF inválido. Deve conter 11 dígitos numéricos.");
                }
            } while (!ValidadorUtils.validarCPF(cpf));

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();
            if (!ValidadorUtils.validarTelefone(telefone)) {
                System.out.println("❌ Telefone inválido. Deve conter entre 8 e 15 dígitos numéricos.");
                return;
            }

            Motorista motorista = new Motorista(nome, cpf, telefone);
            motoristas.add(motorista);
            Persistencia.salvarAutomaticamente("motoristas.dat", motoristas);

            System.out.println("✅ Motorista cadastrado com sucesso!");

        } catch (Exception e) {
            System.out.println("❌ Erro ao cadastrar motorista: " + e.getMessage());
        }
    }

    public void atualizarMotorista(Scanner scanner) {
        listarMotoristas();

        System.out.print("Digite o índice do motorista a atualizar: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx < 0 || idx >= motoristas.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Motorista motorista = motoristas.get(idx);

        System.out.print("Novo nome (atual: " + motorista.getNome() + "): ");
        motorista.setNome(scanner.nextLine());

        System.out.print("Novo CPF (atual: " + motorista.getCpf() + "): ");
        motorista.setCpf(scanner.nextLine());

        System.out.print("Novo telefone (atual: " + motorista.getTelefone() + "): ");
        motorista.setTelefone(scanner.nextLine());

        Persistencia.salvarAutomaticamente("motoristas.dat", motoristas);
        System.out.println("Motorista atualizado com sucesso.");
    }

    public void removerMotorista(Scanner scanner) {
        listarMotoristas();
        System.out.print("Digite o índice do motorista a remover: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx >= 0 && idx < motoristas.size()) {
            Motorista removido = motoristas.remove(idx);
            Persistencia.salvarAutomaticamente("motoristas.dat", motoristas);
            System.out.println("Removido: " + removido.getNome());
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void listarMotoristas() {
        MotoristaService.listarMotoristas(motoristas);
    }


    public void validarMotorista(Scanner scanner) {
        listarMotoristas();
        System.out.print("Digite o índice do motorista a validar: ");
        int idx = scanner.nextInt();
        scanner.nextLine();

        if (idx >= 0 && idx < motoristas.size()) {
            MotoristaService.validarMotorista(motoristas.get(idx));
            Persistencia.salvarAutomaticamente("motoristas.dat", motoristas);
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void avaliarPassageiro(ArrayList<Cliente> clientes) {
        try {
            if (motoristas.isEmpty()) {
                System.out.println("❌ Nenhum motorista disponível.");
                return;
            }

            if (clientes.isEmpty()) {
                System.out.println("❌ Nenhum passageiro (cliente) disponível.");
                return;
            }

            System.out.println("\n== 🚗 Avaliação de Passageiro ==");
            listarMotoristas();

            System.out.print("Escolha o motorista que fará a avaliação: ");
            int idxMotorista = Integer.parseInt(scanner.nextLine());

            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("❌ Índice de motorista inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idxMotorista);

            System.out.println("\n== 👤 Lista de Passageiros ==");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println("[" + i + "] " + clientes.get(i).getNome());
            }

            System.out.print("Escolha o passageiro a ser avaliado: ");
            int idxCliente = Integer.parseInt(scanner.nextLine());

            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("❌ Índice de cliente inválido.");
                return;
            }

            Cliente cliente = clientes.get(idxCliente);

            System.out.print("Nota (1 a 5): ");
            int nota = Integer.parseInt(scanner.nextLine());

            if (nota < 1 || nota > 5) {
                System.out.println("❌ Nota inválida. A nota deve estar entre 1 e 5.");
                return;
            }

            System.out.print("Comentário (opcional): ");
            String comentario = scanner.nextLine();

            Avaliacao avaliacao = new Avaliacao(motorista.getNome(), cliente.getNome(), nota, comentario);
            cliente.adicionarAvaliacao(avaliacao);

            Persistencia.salvarAutomaticamente("clientes.dat", clientes);

            System.out.println("✅ Avaliação registrada com sucesso!");
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Use apenas números nos campos numéricos.");
        } catch (Exception e) {
            System.out.println("❌ Ocorreu um erro ao realizar a avaliação: " + e.getMessage());
        }
    }
    public void listarAvaliacoesMotorista() {
        try {
            if (motoristas.isEmpty()) {
                System.out.println("❌ Nenhum motorista cadastrado.");
                return;
            }

            System.out.println("\n== Lista de Motoristas ==");
            listarMotoristas();

            System.out.print("Escolha o motorista para ver as avaliações: ");
            int idx = Integer.parseInt(scanner.nextLine());

            if (idx < 0 || idx >= motoristas.size()) {
                System.out.println("❌ Índice inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idx);
            List<Avaliacao> avaliacoes = motorista.getAvaliacoes();

            System.out.println("\n📄 Avaliações de " + motorista.getNome() + ":");
            if (avaliacoes.isEmpty()) {
                System.out.println("⚠️ Nenhuma avaliação registrada para este motorista.");
            } else {
                for (Avaliacao avaliacao : avaliacoes) {
                    System.out.println("- " + avaliacao);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Digite apenas números.");
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar avaliações: " + e.getMessage());
        }
    }


}
