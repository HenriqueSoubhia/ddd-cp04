package ui;

import controller.UsuarioController;
import exceptions.RegraNegocioException;
import model.Cliente;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    public static void exibir(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- MENU USUÁRIO ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Consultar Cliente por CPF");
            System.out.println("3. Ver todos os clientes");
            System.out.println("4. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    try {
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Renda: ");
                        double renda = Double.parseDouble(scanner.nextLine());

                        Cliente cliente = new Cliente(cpf, nome, email, renda);
                        UsuarioController.cadastrarCliente(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Erro no banco: " + e.getMessage());
                    } catch (NumberFormatException e) {
                        System.out.println("Renda inválida. Digite um número.");
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Digite o CPF do cliente: ");
                        String cpfConsulta = scanner.nextLine();

                        Cliente cliente = UsuarioController.consultarClientePorCPF(cpfConsulta);
                        System.out.println("\n--- Dados do Cliente ---");
                        System.out.println("ID: " + cliente.getId());
                        System.out.println("Nome: " + cliente.getNome());
                        System.out.println("CPF: " + cliente.getCpf());
                        System.out.println("Email: " + cliente.getEmail());
                        System.out.println("Renda: " + cliente.getRenda());
                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Erro no banco: " + e.getMessage());
                    }
                    break;

                case "3":
                    try {
                        List<Cliente> clientes = UsuarioController.listarTodosClientes();
                        System.out.println("\n--- Lista de Clientes ---");
                        for (Cliente c : clientes) {
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() +
                                    ", CPF: " + c.getCpf() + ", Email: " + c.getEmail() +
                                    ", Renda: " + c.getRenda());
                        }
                    } catch (SQLException e) {
                        System.out.println("Erro no banco: " + e.getMessage());
                    }
                    break;
                case "4":
                    voltar = true;
                    break;


                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
