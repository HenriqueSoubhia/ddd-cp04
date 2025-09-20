package service;

import dao.TransacaoDAO;
import dao.CartaoDAO;
import model.Cartao;
import model.Transacao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TransacaoBO {

    private TransacaoDAO transacaoDAO;
    private CartaoDAO cartaoDAO;

    public TransacaoBO(TransacaoDAO tDao, CartaoDAO cDao) {
        this.transacaoDAO = tDao;
        this.cartaoDAO = cDao;
    }

    public void registrarCompra(String numeroCartao, double valor, String descricao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) throw new Exception("Cartão não encontrado!");
        if (valor <= 0) throw new Exception("Valor deve ser maior que zero!");
        if (valor > cartao.getLimiteDisponivel()) throw new Exception("Limite insuficiente!");

        cartao.setLimiteDisponivel(cartao.getLimiteDisponivel() - valor);
        cartaoDAO.atualizar(cartao);

        Transacao t = new Transacao(0, cartao.getId(), LocalDateTime.now(), descricao, valor, "ABERTA");
        transacaoDAO.salvar(t);
    }

    public List<Transacao> consultarFatura(String numeroCartao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) throw new Exception("Cartão não encontrado!");
        return transacaoDAO.listarAbertasPorCartaoMesAtual(cartao.getId());
    }

    public void pagarFatura(String numeroCartao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) throw new Exception("Cartão não encontrado!");
        // Atualiza limite
        List<Transacao> transacoes = transacaoDAO.listarAbertasPorCartaoMesAtual(cartao.getId());
        double total = transacoes.stream().mapToDouble(Transacao::getValor).sum();
        cartao.setLimiteDisponivel(cartao.getLimiteDisponivel() + total);
        cartaoDAO.atualizar(cartao);

        transacaoDAO.pagarFatura(cartao.getId());
    }

    public List<Transacao> listarTransacoes(String numeroCartao) throws Exception {
        Cartao cartao = cartaoDAO.buscarPorNumero(numeroCartao);
        if (cartao == null) throw new Exception("Cartão não encontrado!");
        return transacaoDAO.listarPorCartao(cartao.getId());
    }
}
