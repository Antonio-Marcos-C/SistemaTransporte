package app;

import dados.Persistencia;
import modelo.*;
import negocio.PagamentoService;
import negocio.MotoristaService;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class MenuSistema {

        private static final Scanner scanner = new Scanner(System.in);

        private static ArrayList<Cliente> clientes = Persistencia.carregar("clientes.dat");
        private static ArrayList<Motorista> motoristas = Persistencia.carregar("motoristas.dat");
        private static ArrayList<Veiculo> veiculos = Persistencia.carregar("veiculos.dat");
        private static ArrayList<Viagem> viagens = Persistencia.carregar("viagens.dat");

        public static void main(String[] args) {
            int tipoUsuario;

            do {
                System.out.println("Bem-vindo ao Sistema de Transporte!!");
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
                System.out.println("1. Gerenciar Motoristas");
                System.out.println("2. Gerenciar Clientes");
                System.out.println("3. Gerenciar Veículos");
                System.out.println("4. Lista Tudo");
                System.out.println("5. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> menuMotoristasadm();
                    case 2 -> menuClientesadm();
                    case 3 -> menuVeiculosadm();
                    case 4 -> listarTudo();
                    case 5 -> System.out.println("Retornando...");
                    default -> System.out.println("Opção inválida.");
                }
            } while (opcao != 5);

        }

        private static void menuMotoristasadm() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR MOTORISTAS ---");
            System.out.println("1. Cadastrar Motorista");
            System.out.println("2. Atualizar Motorista");
            System.out.println("3. Remover Motorista");
            System.out.println("4. Listar Motoristas");
            System.out.println("5. Validar Motorista");
            System.out.println("6. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarMotorista();
                case 2 -> atualizarMotorista();
                case 3 -> removerMotorista();
                case 4 -> listarMotoristas();
                case 5 -> validarMotorista();
                case 6 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

        private static void menuClientesadm() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR CLIENTES ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atualizar Cliente");
            System.out.println("3. Remover Cliente");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> atualizarCliente();
                case 3 -> removerCliente();
                case 4 -> listarClientes();
                case 5 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }

        private static void menuVeiculosadm() {
        int opcao;
        do {
            System.out.println("\n--- GERENCIAR VEÍCULOS ---");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Atualizar Veículo");
            System.out.println("3. Remover Veículo");
            System.out.println("4. Listar Veículos");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarVeiculo();
                case 2 -> atualizarVeiculo();
                case 3 -> removerVeiculo();
                case 4 -> listarVeiculos();
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
                System.out.println("2. Avaliar Passageiro");
                System.out.println("3. Ver Avaliações");
                System.out.println("4. Voltar");
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer de entrada

                switch (opcao) {
                    case 1 -> cadastrarVeiculo();
                    case 2 -> avaliarPassageiro();
                    case 3 -> listarAvaliacoesMotorista();
                    case 4 -> System.out.println("Retornando...");
                    default -> System.out.println("Opção inválida.");
                }

            } while (opcao != 4);
        }
        private static void menuCliente() {
        int opcao;
        do {
            System.out.println("\n===== MENU CLIENTE =====");
            System.out.println("1. Solicitar Viagem");
            System.out.println("2. Listar Viagens do Cliente");
            System.out.println("3. Adicionar Forma de Pagamento");
            System.out.println("4. Avaliar Motorista");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> solicitarViagem();
                case 2 -> listarViagensDoCliente();
                case 3 -> adicionarFormaDePagamento();
                case 4 -> avaliarMotorista();
                case 5 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 5);
    }
        private static void cadastrarCliente() {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            clientes.add(new Cliente(nome, cpf, telefone));
            Persistencia.salvarAutomaticamente("clientes.dat", clientes);
            System.out.println("Cliente cadastrado com sucesso!");

            System.out.print("Deseja adicionar uma forma de pagamento agora? [1] Sim | [2] Não: ");
            int resposta = scanner.nextInt();
            scanner.nextLine();

            if (resposta == 1) {
                adicionarFormaDePagamento();
            }
        }
        private static void cadastrarMotorista() {
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Telefone: ");
            String telefone = scanner.nextLine();

            motoristas.add(new Motorista(nome, cpf, telefone));
            Persistencia.salvarAutomaticamente("motorista.dat", motoristas);
            System.out.println("Motorista cadastrado (não validado).");
        }
        private static void atualizarCliente() {
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }

            listarClientes();
            System.out.print("Digite o número do cliente que deseja atualizar: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("Índice inválido.");
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
                String novoNome = scanner.nextLine();
                cliente.setNome(novoNome);
            }

            System.out.print("Deseja alterar o CPF? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo CPF: ");
                String novoCpf = scanner.nextLine();
                cliente.setCpf(novoCpf);
            }

            System.out.print("Deseja alterar o telefone? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo telefone: ");
                String novoTelefone = scanner.nextLine();
                cliente.setTelefone(novoTelefone);
            }
            Persistencia.salvarAutomaticamente("clientes.dat", clientes);
            System.out.println("Cliente atualizado com sucesso!");
        }
        private static void atualizarMotorista() {
            if (motoristas.isEmpty()) {
                System.out.println("Nenhum motorista cadastrado.");
                return;
            }

            listarMotoristas();
            System.out.print("Digite o número do motorista que deseja atualizar: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= motoristas.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idx);

            System.out.println("\n-- Dados atuais do motorista --");
            System.out.println("Nome: " + motorista.getNome());
            System.out.println("CPF: " + motorista.getCpf());
            System.out.println("Telefone: " + motorista.getTelefone());
            System.out.println("Status de Validação: " + (motorista.isValidado() ? "VALIDADO" : "NÃO VALIDADO"));

            System.out.print("Deseja alterar o nome? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo nome: ");
                String novoNome = scanner.nextLine();
                motorista.setNome(novoNome);
            }

            System.out.print("Deseja alterar o CPF? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo CPF: ");
                String novoCpf = scanner.nextLine();
                motorista.setCpf(novoCpf);
            }

            System.out.print("Deseja alterar o telefone? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo telefone: ");
                String novoTelefone = scanner.nextLine();
                motorista.setTelefone(novoTelefone);
            }
            Persistencia.salvarAutomaticamente("motorista.dat", motoristas);
            System.out.println("Motorista atualizado com sucesso!");
        }
        private static void atualizarVeiculo() {
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo cadastrado.");
                return;
            }

            listarVeiculos();
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
                String novaPlaca = scanner.nextLine();
                veiculo.setPlaca(novaPlaca);
            }

            System.out.print("Deseja alterar o modelo? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Novo modelo: ");
                String novoModelo = scanner.nextLine();
                veiculo.setModelo(novoModelo);
            }

            System.out.print("Deseja alterar a cor? (s/n): ");
            if (scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.print("Nova cor: ");
                String novaCor = scanner.nextLine();
                veiculo.setCor(novaCor);
            }
            Persistencia.salvarAutomaticamente("veiculos.dat", veiculos);
            System.out.println("O veículo atualizado com sucesso!");
        }
        private static void validarMotorista() {
            System.out.println("== Validar Motorista ==");
            MotoristaService.listarMotoristas(motoristas);

            System.out.print("Digite o número do motorista a validar: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < motoristas.size()) {
                MotoristaService.validarMotorista(motoristas.get(index));
            } else {
                System.out.println("Índice inválido.");
            }
        }
        private static void cadastrarVeiculo() {
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
        private static void adicionarFormaDePagamento() {
            System.out.println("== Adicionar Forma de Pagamento ==");
            listarClientes();

            System.out.print("Escolha o cliente: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            Cliente cliente = clientes.get(idx);

            System.out.println("Tipos disponíveis:");
            System.out.println("[1] Pix");
            System.out.println("[2] Dinheiro");
            System.out.println("[3] Cartão de Crédito");

            System.out.print("Escolha o tipo: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            FormaDePagamento forma = null;

            switch (tipo) {
                case 1 -> {
                    System.out.print("Digite a chave Pix: ");
                    String chavePix = scanner.nextLine();
                    forma = new Pix(chavePix); // Passa a chavePix ao construtor
                }
                case 2 -> forma = new Dinheiro();
                case 3 -> {
                    System.out.print("Número do cartão: ");
                    String numero = scanner.nextLine();

                    System.out.print("Limite: ");
                    double limite = scanner.nextDouble();
                    scanner.nextLine();

                    forma = new CartaoCredito(numero, limite);
                }
                default -> {
                    System.out.println("Tipo inválido.");
                    return;
                }
            }

            cliente.adicionarFormaPagamento(forma);
            System.out.println("Forma de pagamento adicionada com sucesso!");
        }
        private static void listarClientes() {
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente cadastrado.");
                return;
            }

            System.out.println("\n== Lista de Clientes ===");
            for (int i = 0; i < clientes.size(); i++) {
                Cliente c = clientes.get(i);
                System.out.println("[" + i + "] " + c.getNome() + " | CPF: " + c.getCpf() + " | Tel: " + c.getTelefone());
            }
        }
        private static void listarMotoristas() {
            if (motoristas.isEmpty()) {
                System.out.println("Nenhum motorista cadastrado.");
                return;
            }

            System.out.println("\n== Lista de Motoristas ==");
            for (int i = 0; i < motoristas.size(); i++) {
                Motorista m = motoristas.get(i);
                String status = m.isValidado() ? "VALIDADO" : "NÃO VALIDADO";
                System.out.println("[" + i + "] " + m.getNome() + " | CPF: " + m.getCpf() + " | Tel: " + m.getTelefone() + " | " + status);
            }
        }
        private static void listarVeiculos() {
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
        private static void listarViagensDoCliente() {
            if (clientes.isEmpty()) {
                System.out.println("Não há clientes cadastrados.");
                return;
            }

            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx < 0 || idx >= clientes.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Cliente cliente = clientes.get(idx);
            System.out.println("\n== Viagens do cliente " + cliente.getNome() + " ==");

            boolean encontrou = false;

            for (Viagem v : viagens) {
                if (v.getCliente().equals(cliente)) {
                    System.out.println(v);
                    encontrou = true;
                }
            }
            if (!encontrou) {
                System.out.println("Nenhuma viagem encontrada para este cliente.");
            }
        }
        private static void removerCliente() {
            if (clientes.isEmpty()) {
                System.out.println("Nenhum cliente para remover.");
                return;
            }

            listarClientes();
            System.out.print("Digite o número do cliente que deseja remover: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx >= 0 && idx < clientes.size()) {
                Cliente removido = clientes.remove(idx);
                System.out.println("Cliente removido: " + removido.getNome());
            } else {
                System.out.println("Índice inválido.");
            }
        }
        private static void removerMotorista() {
            if (motoristas.isEmpty()) {
                System.out.println("Nenhum motorista para remover.");
                return;
            }

            listarMotoristas();
            System.out.print("Digite o número do motorista que deseja remover: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx >= 0 && idx < motoristas.size()) {
                Motorista removido = motoristas.remove(idx);
                System.out.println("Motorista removido: " + removido.getNome());
            } else {
                System.out.println("Índice inválido.");
            }
        }
        private static void removerVeiculo() {
            if (veiculos.isEmpty()) {
                System.out.println("Nenhum veículo para remover.");
                return;
            }

            listarVeiculos();
            System.out.print("Digite o número do veículo que deseja remover: ");
            int idx = scanner.nextInt();
            scanner.nextLine();

            if (idx >= 0 && idx < veiculos.size()) {
                Veiculo removido = veiculos.remove(idx);
                System.out.println("Veículo removido: " + removido);
            } else {
                System.out.println("Índice inválido.");
            }
        }
        private static void solicitarViagem() {
            if (clientes.isEmpty() || motoristas.isEmpty() || veiculos.isEmpty()) {
                System.out.println("É necessário ter cliente, motorista e veículo cadastrados.");
                return;
            }
            System.out.println("\n== Solicitação de Viagem ==");

            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idxCliente = scanner.nextInt();
            scanner.nextLine();

            System.out.println("-- Motoristas --");
            for (int i = 0; i < motoristas.size(); i++) {
                System.out.println("[" + i + "] " + motoristas.get(i).getNome());
            }
            System.out.print("Escolha o motorista: ");
            int idxMotorista = scanner.nextInt();
            scanner.nextLine();

            if (!motoristas.get(idxMotorista).isValidado()) {
                System.out.println("Motorista não validado! Escolha outro.");
                return;
            }

            System.out.println("-- Veículos --");
            for (int i = 0; i < veiculos.size(); i++) {
                System.out.println("[" + i + "] " + veiculos.get(i));
            }
            System.out.print("Escolha o veículo: ");
            int idxVeiculo = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Origem: ");
            String origem = scanner.nextLine();

            System.out.print("Destino: ");
            String destino = scanner.nextLine();

            System.out.print("Valor da viagem: R$");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Tipo de viagem: [1] Passageiro | [2] Entrega");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            Cliente clienteSelecionado = clientes.get(idxCliente);
            ArrayList<FormaDePagamento> formas = clienteSelecionado.getFormasDePagamento();

            if (formas.isEmpty()) {
                System.out.println("Cliente não possui formas de pagamento cadastradas.");
                return;
            }

            System.out.println("\n== Formas de Pagamento ==");
            for (int i = 0; i < formas.size(); i++) {
                System.out.println("[" + i + "] " + formas.get(i).getClass().getSimpleName());
            }

            System.out.print("Escolha a forma de pagamento: ");
            int idxForma = scanner.nextInt();
            scanner.nextLine();

            FormaDePagamento forma = formas.get(idxForma);
            boolean pago = PagamentoService.realizarPagamento(forma, valor);

            if (!pago) {
                System.out.println("Pagamento falhou. Viagem não pode ser realizada.");
                return;
            }

            Viagem viagem;
            if (tipo == 1) {
                viagem = new ViagemPassageiro(
                        clientes.get(idxCliente),
                        motoristas.get(idxMotorista),
                        veiculos.get(idxVeiculo),
                        origem, destino, valor
                );
            } else {
                viagem = new ViagemEntrega(
                        clientes.get(idxCliente),
                        motoristas.get(idxMotorista),
                        veiculos.get(idxVeiculo),
                        origem, destino, valor
                );
            }

            viagem.iniciar();

            System.out.print("Viagem em andamento");
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(1000);
                    System.out.print(".");
                }
                System.out.println();
            } catch (InterruptedException e) {
                System.out.println("\nErro durante a simulação da viagem.");
            }

            viagem.concluir();
            viagens.add(viagem);
            System.out.println("✔ Viagem registrada com sucesso!");
        }
        private static void listarTudo() {
            System.out.println("\n-- Clientes --");
            clientes.forEach(System.out::println);

            System.out.println("\n-- Motoristas --");
            motoristas.forEach(System.out::println);

            System.out.println("\n-- Veículos --");
            veiculos.forEach(System.out::println);
        }
        private static void avaliarMotorista() {
            System.out.println("\n== Avaliar Motorista ==");

            // Escolher o cliente
            listarClientes();
            System.out.print("Escolha o cliente: ");
            int idxCliente = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Cliente cliente = clientes.get(idxCliente);

            // Escolher o motorista
            listarMotoristas();
            System.out.print("Escolha o motorista a ser avaliado: ");
            int idxMotorista = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idxMotorista);

            // Informações da avaliação
            System.out.print("Digite a nota (1-5): ");
            int nota = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (nota < 1 || nota > 5) {
                System.out.println("Nota inválida.");
                return;
            }

            System.out.print("Digite um comentário (opcional): ");
            String comentario = scanner.nextLine();

            // Criar a avaliação e adicionar ao motorista
            Avaliacao avaliacao = new Avaliacao(cliente.getNome(), motorista.getNome(), nota, comentario);
            motorista.adicionarAvaliacao(avaliacao);
            System.out.println("Avaliação realizada com sucesso!");
        }
        private static void avaliarPassageiro() {
            System.out.println("\n== Avaliar Passageiro ==");

            // Escolher o motorista
            listarMotoristas();
            System.out.print("Escolha o motorista: ");
            int idxMotorista = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idxMotorista);

            // Escolher o cliente (passageiro)
            listarClientes();
            System.out.print("Escolha o passageiro a ser avaliado: ");
            int idxCliente = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (idxCliente < 0 || idxCliente >= clientes.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Cliente cliente = clientes.get(idxCliente);

            // Informações da avaliação
            System.out.print("Digite a nota (1-5): ");
            int nota = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (nota < 1 || nota > 5) {
                System.out.println("Nota inválida.");
                return;
            }

            System.out.print("Digite um comentário (opcional): ");
            String comentario = scanner.nextLine();

            // Criar a avaliação e adicionar ao cliente
            Avaliacao avaliacao = new Avaliacao(motorista.getNome(), cliente.getNome(), nota, comentario);
            cliente.adicionarAvaliacao(avaliacao);
            System.out.println("Avaliação realizada com sucesso!");
        }
        private static void listarAvaliacoesMotorista() {
            System.out.println("\n== Avaliações do Motorista ==");

            // Escolher o motorista
            listarMotoristas();
            System.out.print("Escolha o motorista: ");
            int idxMotorista = scanner.nextInt();
            scanner.nextLine();  // Limpar o buffer

            if (idxMotorista < 0 || idxMotorista >= motoristas.size()) {
                System.out.println("Índice inválido.");
                return;
            }

            Motorista motorista = motoristas.get(idxMotorista);

            // Listar as avaliações
            List<Avaliacao> avaliacoes = motorista.getAvaliacoes();
            if (avaliacoes.isEmpty()) {
                System.out.println("Este motorista não tem avaliações.");
            } else {
                for (Avaliacao avaliacao : avaliacoes) {
                    System.out.println(avaliacao);
                }
            }
        }
    }