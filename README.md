# 🚗 Sistema de Transporte

Sistema de gerenciamento de transporte com suporte a cadastro de clientes, motoristas, veículos e viagens. Desenvolvido em Java com foco em Programação Orientada a Objetos (POO).

## 📚 Descrição

Este projeto simula um sistema de transporte no qual é possível:

- Cadastrar e validar motoristas
- Cadastrar veículos (Econômico, SUV, Luxo, Motocicleta)
- Cadastrar clientes e formas de pagamento (Pix, Dinheiro, Cartão de Crédito)
- Solicitar viagens do tipo Passageiro ou Entrega
- Gerenciar dados com persistência em arquivos `.dat`

## 🧠 Tecnologias Utilizadas

- Java 17
- IntelliJ IDEA
- POO (Herança, Polimorfismo, Encapsulamento)
- Serialização (para persistência dos dados)
- Git/GitHub

## ⚙️ Funcionalidades

- [x] Cadastro de clientes, motoristas e veículos  
- [x] Adição e validação de formas de pagamento  
- [x] Solicitação de viagens com simulação de processamento  
- [x] Listagem, atualização e remoção de dados  
- [x] Submenus organizados para melhor navegação  

## 📁 Estrutura do Projeto

```
SistemaTransporte/
├── src/
│   ├── app/                # Menu principal e testes
│   ├── dados/              # Persistência de dados (.dat)
│   ├── modelo/             # Classes de domínio (Cliente, Motorista, etc)
│   └── negocio/            # Lógicas de serviço (Validações, Pagamento)
├── clientes.dat
├── motoristas.dat
├── veiculos.dat
└── viagens.dat
```

## 📝 Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/Antonio-Marcos-C/SistemaTransporte.git
   ```

2. Abra no IntelliJ IDEA (ou IDE de sua preferência)

3. Execute a classe `MenuSistema.java` (`src/app/MenuSistema.java`)

---

## 👨‍💻 Autores

**Antonio Marcos**  
Estudante de Ciência da Computação – UFAPE  
[GitHub](https://github.com/Antonio-Marcos-C)

**Thiago Mauricio**  
Estudante de Ciência da Computação – UFAPE  
[GitHub](https://github.com/teagomorrice)

---

## 📄 Licença

Este projeto está sob a licença MIT. Sinta-se à vontade para usar e contribuir.
