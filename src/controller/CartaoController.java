package controller;

import model.Cartao;
import service.CartaoBO;
import exceptions.RegraNegocioException;

import java.sql.SQLException;

public class CartaoController {

    private static CartaoBO cartaoBO;

    public static void init(CartaoBO bo) {
        cartaoBO = bo;
    }

    public static Cartao solicitarCartao(String cpf, String bandeira) throws SQLException, RegraNegocioException {
        return cartaoBO.solicitarCartao(cpf, bandeira);
    }

    public static Cartao consultarCartaoPorNumero(String numero) throws SQLException, RegraNegocioException {
        return cartaoBO.consultarCartaoPorNumero(numero);
    }
}
