package service;

import dao.ClienteDAO;
import exceptions.RegraNegocioException;
import model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteBO {
    private ClienteDAO clienteDAO;

    public ClienteBO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void cadastrarCliente(Cliente cliente) throws RegraNegocioException, SQLException {
        if (clienteDAO.existeCPF(cliente.getCpf())) {
            throw new RegraNegocioException("CPF já cadastrado!");
        }
        if (clienteDAO.existeEmail(cliente.getEmail())) {
            throw new RegraNegocioException("E-mail já cadastrado!");
        }

        clienteDAO.salvar(cliente);
    }

    public Cliente consultarPorCPF(String cpf) throws RegraNegocioException, SQLException {
        Cliente cliente = clienteDAO.buscarPorCPF(cpf);
        if (cliente == null) {
            throw new RegraNegocioException("Cliente não encontrado!");
        }
        return cliente;
    }

    public List<Cliente> listarTodosClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }

}
