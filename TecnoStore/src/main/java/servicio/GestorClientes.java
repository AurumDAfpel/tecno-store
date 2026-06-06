/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Janus
 */
import modelo.cliente;
import DAO.ClienteDAO;
import java.sql.SQLException;
import java.util.List;

public class GestorClientes {
    private ClienteDAO ClienteDAO = new ClienteDAO();

    public void registrarCliente(cliente c) throws SQLException {
        ClienteDAO.registrar(c);
    }

    public List<cliente> listarClientes() throws SQLException {
        return ClienteDAO.listarTodos();
    }

    public boolean existeIdentificacion(String iden) throws SQLException {
        return ClienteDAO.existeIdentificacion(iden);
    }
}
