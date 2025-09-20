package ui;

import controller.TransacaoController;
import java.util.Scanner;

public class MenuTransacao {

    public static void exibir(Scanner scanner) {
        boolean voltar = false;

        while (!voltar) {
            System.out.println("\n--- MENU TRANSAÇÃO ---");
            System.out.println("1. Registrar Compra");
            System.out.println("2. Consultar Fatura (Mês Atual)");
            System.out.println("3. Pagar Fatura");
            System.out.println("4. Listar Transações por Cartão");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Número do cartão: ");
                    String numeroCartao = scanner.nextLine();
                    System.out.print("Valor da compra: ");
                    double valor = Double.parseDouble(scanner.nextLine());
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    try {
                        TransacaoController.registrarCompra(numeroCartao, valor, descricao);
                        System.out.println("Compra registrada com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "2":
                    System.out.print("Número do cartão: ");
                    numeroCartao = scanner.nextLine();
                    try {
                        TransacaoController.consultarFatura(numeroCartao);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "3":
                    System.out.print("Número do cartão: ");
                    numeroCartao = scanner.nextLine();
                    try {
                        TransacaoController.pagarFatura(numeroCartao);
                        System.out.println("Fatura paga com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "4":
                    System.out.print("Número do cartão: ");
                    numeroCartao = scanner.nextLine();
                    try {
                        TransacaoController.listarTransacoesPorCartao(numeroCartao);
                    } catch (Exception e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;

                case "5":
                    voltar = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
