package ui;

import controller.CartaoController;
import exceptions.RegraNegocioException;
import model.Cartao;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuCartao {

    public static void exibir(Scanner scanner) {
        boolean voltar = false;
        while (!voltar) {
            System.out.println("\n--- MENU CARTÃO ---");
            System.out.println("1. Solicitar Cartão");
            System.out.println("2. Consultar Cartão por Número");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    try {
                        System.out.print("CPF do cliente: ");
                        String cpf = scanner.nextLine();

                        System.out.print("Bandeira (VISA/MASTERCARD): ");
                        String bandeira = scanner.nextLine().toUpperCase();

                        Cartao cartao = CartaoController.solicitarCartao(cpf, bandeira);
                        System.out.println("Cartão solicitado com sucesso!");
                        System.out.println("Número do cartão: " + cartao.getNumero());
                        System.out.println("Limite total: " + cartao.getLimiteTotal());
                        System.out.println("Limite disponível: " + cartao.getLimiteDisponivel());

                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Erro no banco: " + e.getMessage());
                    }
                    break;

                case "2":
                    try {
                        System.out.print("Número do cartão: ");
                        String numero = scanner.nextLine();

                        Cartao cartao = CartaoController.consultarCartaoPorNumero(numero);
                        System.out.println("\n--- Dados do Cartão ---");
                        System.out.println("ID: " + cartao.getId());
                        System.out.println("Número: " + cartao.getNumero());
                        System.out.println("Bandeira: " + cartao.getBandeira());
                        System.out.println("Limite total: " + cartao.getLimiteTotal());
                        System.out.println("Limite disponível: " + cartao.getLimiteDisponivel());
                        System.out.println("Cliente ID: " + cartao.getClienteId());

                    } catch (RegraNegocioException e) {
                        System.out.println("Erro: " + e.getMessage());
                    } catch (SQLException e) {
                        System.out.println("Erro no banco: " + e.getMessage());
                    }
                    break;

                case "3":
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
