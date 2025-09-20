package controller;

import service.TransacaoBO;
import model.Transacao;

import java.util.List;

public class TransacaoController {

    private static TransacaoBO bo;

    public static void init(TransacaoBO boInst) {
        bo = boInst;
    }

    public static void registrarCompra(String numeroCartao, double valor, String descricao) {
        try {
            bo.registrarCompra(numeroCartao, valor, descricao);
            System.out.println("Compra registrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void consultarFatura(String numeroCartao) {
        try {
            List<Transacao> fatura = bo.consultarFatura(numeroCartao);
            double total = 0;
            for (Transacao t : fatura) {
                System.out.println(t.getData() + " | " + t.getDescricao() + " | R$ " + t.getValor());
                total += t.getValor();
            }
            System.out.println("Total da fatura: R$ " + total);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void pagarFatura(String numeroCartao) {
        try {
            bo.pagarFatura(numeroCartao);
            System.out.println("Fatura paga com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public static void listarTransacoesPorCartao(String numeroCartao) {
        try {
            List<Transacao> lista = bo.listarTransacoes(numeroCartao);
            for (Transacao t : lista) {
                System.out.println(t.getData() + " | " + t.getDescricao() + " | R$ " + t.getValor() + " | " + t.getStatus());
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
