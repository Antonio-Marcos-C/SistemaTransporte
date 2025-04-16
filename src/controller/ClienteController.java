package controller;

import dados.Persistencia;
import modelo.*;
import negocio.ValidadorUtils;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ClienteController {
    private final ArrayList<Cliente> clientes;
    private final ArrayList<Viagem> viagens;
    private final Scanner scanner = new Scanner(System.in);

    public ClienteController(ArrayList<Cliente> clientes, ArrayList<Viagem> viagens) {
        this.clientes = clientes;
        this.viagens = viagens;
    }


    public void cadastrarCliente() {

            try {
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

                String telefone;
                do {
                    System.out.print("Telefone (somente números): ");
                    telefone = scanner.nextLine();
                    if (!ValidadorUtils.validarTelefone(telefone)) {
                        System.out.println("❌ Telefone inválido. Deve conter entre 10 e 11 dígitos.");
                    }
                } while (!ValidadorUtils.validarTelefone(telefone));

                Cliente cliente = new Cliente(nome, cpf, telefone);
                clientes.add(cliente);
                Persistencia.salvarAutomaticamente("clientes.dat", clientes);

                System.out.println("✅ Cliente cadastrado com sucesso!");

                System.out.print("Deseja adicionar uma forma de pagamento agora? [1] Sim | [2] Não: ");
                int resposta = scanner.nextInt();
                scanner.nextLine();

                if (resposta == 1) adicionarFormaDePagamento();

            } catch (InputMismatchException e) {
                System.out.println("❌ Entrada inválida. Por favor, use os tipos corretos.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Ocorreu um erro inesperado: " + e.getMessage());
            }
        }

    public void listarClientes() {
        try {
            if (clientes == null || clientes.isEmpty()) {
                System.out.println("📭 Nenhum cliente cadastrado no sistema.");
                return;
            }

            System.out.println("\n📋 Lista de Clientes:");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                System.out.printf("[%d] Nome: %s | CPF: %s | Tel: %s%n",
                        i, c.getNome(), c.getCpf(), c.getTelefone());
            }
        } catch (Exception e) {
            System.out.println("❌ Erro ao listar clientes: " + e.getMessage());
        }
    }
    public void adicionarFormaDePagamento() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        try {
            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("❌ Índice inválido.");
                return;
            }

            Cliente cliente = clientes.get(idx);

            System.out.println("Tipos disponíveis:");
            System.out.println("[1] Pix\n[2] Dinheiro\n[3] Cartão de Crédito");
            System.out.print("Escolha o tipo: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            FormaDePagamento forma = null;

            switch (tipo) {
                case 1 -> {
                    System.out.print("Digite a chave Pix: ");
                    String chave = scanner.nextLine();
                    if (chave.isBlank()) {
                        System.out.println("❌ Chave Pix não pode ser vazia.");
                        return;
                    }
                    forma = new Pix(chave);
                }
                case 2 -> {
                    forma = new Dinheiro();
                }
                case 3 -> {
                    System.out.print("Número do cartão: ");
                    String numero = scanner.nextLine();

                    System.out.print("Limite: ");
                    double limite = scanner.nextDouble();
                    scanner.nextLine();

                    if (numero.isBlank() || limite <= 0) {
                        System.out.println("❌ Número ou limite inválido.");
                        return;
                    }

                    forma = new CartaoCredito(numero, limite);
                }
                default -> {
                    System.out.println("❌ Tipo de pagamento inválido.");
                    return;
                }
            }

            cliente.adicionarFormaPagamento(forma);
            Persistencia.salvarAutomaticamente("clientes.dat", clientes);
            System.out.println("✅ Forma de pagamento adicionada com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida. Use números inteiros e decimais corretamente.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }
    public void listarViagensDoCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        try {
            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("❌ Índice inválido. Tente novamente.");
                return;
            }

            Cliente cliente = clientes.get(idx);
            System.out.println("\n== Viagens de " + cliente.getNome() + " ==");

            boolean encontrou = false;
            for (Viagem v : viagens) {
                if (v.getCliente().equals(cliente)) {
                    System.out.println(v);
                    encontrou = true;
                }
            }

            if (!encontrou) {
                System.out.println("ℹ️ Nenhuma viagem encontrada para este cliente.");
            }

        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida. Digite um número inteiro.");
            scanner.nextLine(); // Limpa o buffer do scanner
        } catch (Exception e) {
            System.out.println("❌ Ocorreu um erro ao listar as viagens: " + e.getMessage());
        }
    }
    public void atualizarCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        try {
            listarClientes();
            System.out.print("Digite o número do cliente que deseja atualizar: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("❌ Índice inválido.");
                return;
            }

            Cliente cliente = clientes.get(idx);

            System.out.println("\n-- Dados atuais do cliente --");
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Telefone: " + cliente.getTelefone());

            System.out.print("Deseja alterar o nome? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo nome: ");
                cliente.setNome(scanner.nextLine());
            }

            System.out.print("Deseja alterar o CPF? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                String novoCpf;
                do {
                    System.out.print("Novo CPF (somente números): ");
                    novoCpf = scanner.nextLine();
                    if (!novoCpf.matches("\\d{11}")) {
                        System.out.println("❌ CPF inválido. Deve conter 11 dígitos.");
                    }
                } while (!novoCpf.matches("\\d{11}"));
                cliente.setCpf(novoCpf);
            }

            System.out.print("Deseja alterar o telefone? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                String novoTelefone;
                do {
                    System.out.print("Novo telefone (somente números): ");
                    novoTelefone = scanner.nextLine();
                    if (!novoTelefone.matches("\\d{10,11}")) {
                        System.out.println("❌ Telefone inválido. Deve conter entre 10 e 11 dígitos.");
                    }
                } while (!novoTelefone.matches("\\d{10,11}"));
                cliente.setTelefone(novoTelefone);
            }

            Persistencia.salvarAutomaticamente("clientes.dat", clientes);
            System.out.println("✅ Cliente atualizado com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida. Certifique-se de digitar números onde for solicitado.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    public void removerCliente() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente para remover.");
            return;
        }
        try {
            listarClientes();
            System.out.print("Digite o número do cliente que deseja remover: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("❌ Índice inválido. Nenhum cliente removido.");
                return;
            }

            Cliente removido = clientes.remove(idx);
            Persistencia.salvarAutomaticamente("clientes.dat", clientes);
            System.out.println("✅ Cliente removido: " + removido.getNome());

        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida. Digite um número inteiro.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }

    public void avaliarMotorista(ArrayList<Motorista> motoristas) {
        if (clientes.isEmpty() || motoristas.isEmpty()) {
            System.out.println("❌ Não há clientes ou motoristas disponíveis para avaliação.");
            return;
        }

        try {
            listarClientes();
            System.out.print("Escolha o cliente que fará a avaliação: ");
            int idxCliente = scanner.nextInt();
            scanner.nextLine();

            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("❌ Índice de cliente inválido.");
                return;
            }
            Cliente cliente = clientes.get(idxCliente);

            System.out.println("\n== Lista de Motoristas ==");
            for (int i = 0; i < motoristas.size(); i++) {
                Motorista m = motoristas.get(i);
                String status = m.isValidado() ? "VALIDADO" : "NÃO VALIDADO";
                System.out.println("[" + i + "] " + m.getNome() + " | Status: " + status);
            }

            System.out.print("Escolha o motorista a ser avaliado: ");
            int idxMotorista = scanner.nextInt();
            scanner.nextLine();

            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("❌ Índice de motorista inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idxMotorista);

            System.out.print("Digite a nota (1 a 5): ");
            int nota = scanner.nextInt();
            scanner.nextLine();

            if (nota < 1 || nota > 5) {
                System.out.println("❌ Nota inválida. Deve estar entre 1 e 5.");
                return;
            }

            System.out.print("Digite um comentário (opcional): ");
            String comentario = scanner.nextLine();

            Avaliacao avaliacao = new Avaliacao(cliente.getNome(), motorista.getNome(), nota, comentario);
            motorista.adicionarAvaliacao(avaliacao);
            Persistencia.salvarAutomaticamente("motoristas.dat", motoristas);

            System.out.println("✅ Avaliação registrada com sucesso!");

        } catch (InputMismatchException e) {
            System.out.println("❌ Entrada inválida. Certifique-se de usar números inteiros onde solicitado.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado: " + e.getMessage());
        }
    }
}

