/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Janus
 */
import Conexion.ConexionDB;
import modelo.cliente;
import modelo.item_venta;
import modelo.venta;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {

    public void registrarVenta(venta v) throws SQLException {
        Connection con = null;
        try {
            con = ConexionDB.getConexion();
            con.setAutoCommit(false); // Transacción asegurada para datos sensibles

            String sqlVenta = "INSERT INTO ventas (id_cliente, fecha, total) VALUES (?, ?, ?)";
            try (PreparedStatement psV = con.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS)) {
                psV.setInt(1, v.getCliente().getId());
                psV.setTimestamp(2, Timestamp.valueOf(v.getFecha()));
                psV.setDouble(3, v.getTotal());
                psV.executeUpdate();

                try (ResultSet rs = psV.getGeneratedKeys()) {
                    if (rs.next()) v.setId(rs.getInt(1));
                }
            }

            String sqlDetalle = "INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES (?, ?, ?, ?)";
            String sqlUpdateStock = "UPDATE celulares SET stock = stock - ? WHERE id = ?";
            
            try (PreparedStatement psD = con.prepareStatement(sqlDetalle);
                 PreparedStatement psU = con.prepareStatement(sqlUpdateStock)) {
                
                for (item_venta item : v.getItems()) {
                    psD.setInt(1, v.getId());
                    psD.setInt(2, item.getCelular().getId());
                    psD.setInt(3, item.getCantidad());
                    psD.setDouble(4, item.getSubtotal());
                    psD.addBatch();

                    psU.setInt(1, item.getCantidad());
                    psU.setInt(2, item.getCelular().getId());
                    psU.addBatch();
                }
                psD.executeBatch();
                psU.executeBatch();
            }

            con.commit();
        } catch (SQLException e) {
            if (con != null) con.rollback();
            throw e;
        } finally {
            if (con != null) con.setAutoCommit(true);
        }
    }

    public List<venta> listarVentasCompletas() throws SQLException {
        List<venta> ventas = new ArrayList<>();
        String sql = "SELECT v.id, v.fecha, v.total, c.id AS cid, c.nombre, c.identificacion, c.correo, c.telefono " +
                     "FROM ventas v JOIN clientes c ON v.id_cliente = c.id";
        
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                cliente cl = new cliente(rs.getInt("cid"), rs.getString("nombre"), 
                        rs.getString("identificacion"), rs.getString("correo"), rs.getString("telefono"));
                venta v = new venta(rs.getInt("id"), cl, rs.getTimestamp("fecha").toLocalDateTime(), rs.getDouble("total"));
                ventas.add(v);
            }
        }
        return ventas;
    }
}
