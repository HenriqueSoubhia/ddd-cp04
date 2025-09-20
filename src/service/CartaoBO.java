package service;

import dao.CartaoDAO;
import dao.ClienteDAO;
import exceptions.RegraNegocioException;
import model.Cartao;
import model.Cliente;

import java.sql.SQLException;

public class CartaoBO {

    private final CartaoDAO cartaoDAO;
    private final ClienteDAO clienteDAO;

    public CartaoBO(CartaoDAO cartaoDAO, ClienteDAO clienteDAO) {
        this.cartaoDAO = cartaoDAO;
        this.clienteDAO = clienteDAO;
    }

    public Cartao solicitarCartao(String cpf, String bandeira) throws SQLException, RegraNegocioException {
        Cliente cliente = clienteDAO.buscarPorCPF(cpf);
        if (cliente == null) {
            throw new RegraNegocioException("Cliente não encontrado!");
        }

        // Validação de renda mínima
        double renda = cliente.getRenda();
        double limite;
        if (bandeira.equalsIgnoreCase("VISA")) {
            if (renda < 1500) throw new RegraNegocioException("Renda insuficiente para Visa!");
            limite = renda * 2;
        } else if (bandeira.equalsIgnoreCase("MASTERCARD")) {
            if (renda < 2000) throw new RegraNegocioException("Renda insuficiente para Mastercard!");
            limite = renda * 2.5;
        } else {
            throw new RegraNegocioException("Bandeira inválida!");
        }

        String numero = CartaoDAO.gerarNumeroCartaoUnico();
        Cartao cartao = new Cartao(numero, bandeira, limite, limite, cliente.getId());
        cartaoDAO.salvar(cartao);
        return cartao;
    }

    public Cartao consultarCartaoPorNumero(String numero) throws SQLException, RegraNegocioException {
        Cartao cartao = cartaoDAO.buscarPorNumero(numero);
        if (cartao == null) {
            throw new RegraNegocioException("Cartão não encontrado!");
        }
        return cartao;
    }


}
