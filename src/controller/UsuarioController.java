package controller;

import exceptions.RegraNegocioException;
import model.Cliente;
import service.ClienteBO;

import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private static ClienteBO clienteBO;

    public static void init(ClienteBO bo) {
        clienteBO = bo;
    }

    public static void cadastrarCliente(Cliente cliente) throws RegraNegocioException, SQLException {
        clienteBO.cadastrarCliente(cliente);
    }

    public static Cliente consultarClientePorCPF(String cpf) throws RegraNegocioException, SQLException {
        return clienteBO.consultarPorCPF(cpf);
    }

    public static List<Cliente> listarTodosClientes() throws SQLException {
        return clienteBO.listarTodosClientes();
    }

}
