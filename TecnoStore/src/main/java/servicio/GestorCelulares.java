/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author Janus
 */
import modelo.celular;
import DAO.CelularDAO;
import java.sql.SQLException;
import java.util.List;

public class GestorCelulares {
    private CelularDAO celularDAO = new CelularDAO();

    public void registrarCelular(celular c) throws SQLException {
        celularDAO.registrar(c);
    }

    public void actualizarCelular(celular c) throws SQLException {
        celularDAO.actualizar(c);
    }

    public void eliminarCelular(int id) throws SQLException {
        celularDAO.eliminar(id);
    }

    public List<celular> listarCelulares() throws SQLException {
        return celularDAO.listarTodos();
    }
}
