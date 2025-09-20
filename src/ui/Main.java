package ui;

import controller.CartaoController;
import controller.TransacaoController;
import controller.UsuarioController;
import dao.CartaoDAO;
import dao.ClienteDAO;
import dao.TransacaoDAO;
import service.CartaoBO;
import service.ClienteBO;
import service.TransacaoBO;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        ClienteBO clienteBo = new ClienteBO(clienteDAO);
        UsuarioController.init(clienteBo);

        CartaoDAO cartaoDAO = new CartaoDAO();
        CartaoBO cartaoBO = new CartaoBO(cartaoDAO, clienteDAO);
        CartaoController.init(cartaoBO);

        TransacaoDAO tDao = new TransacaoDAO();
        CartaoDAO cDao = new CartaoDAO();
        TransacaoBO tBo = new TransacaoBO(tDao, cDao);
        TransacaoController.init(tBo);



        Scanner scanner = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("=== SISTEMA DE CARTOES ===");
            System.out.println("1. Cartão");
            System.out.println("2. Usuário");
            System.out.println("3. Transação");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    MenuCartao.exibir(scanner);
                    break;
                case "2":
                    MenuUsuario.exibir(scanner);
                    break;
                case "3":
                    MenuTransacao.exibir(scanner);
                    break;
                case "0":
                    System.out.println("Encerrando sistema...");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
