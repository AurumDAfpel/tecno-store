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
import modelo.celular;
import modelo.categoria_gama;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CelularDAO {
    
    public void registrar(celular c) throws SQLException {
        String sql = "INSERT INTO celulares (marca, modelo, sistema_operativo, gama, precio, stock) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setString(3, c.getSistemaOperativo());
            ps.setString(4, c.getGama().name());
            ps.setDouble(5, c.getPrecio());
            ps.setInt(6, c.getStock());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        }
    }

    public void actualizar(celular c) throws SQLException {
        String sql = "UPDATE celulares SET marca=?, modelo=?, sistema_operativo=?, gama=?, precio=?, stock=? WHERE id=?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModelo());
            ps.setString(3, c.getSistemaOperativo());
            ps.setString(4, c.getGama().name());
            ps.setDouble(5, c.getPrecio());
            ps.setInt(6, c.getStock());
            ps.setInt(7, c.getId());
            ps.executeUpdate();
        }
    }

    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM celulares WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<celular> listarTodos() throws SQLException {
        List<celular> lista = new ArrayList<>();
        String sql = "SELECT * FROM celulares";
        try (Connection con = ConexionDB.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new celular(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("sistema_operativo"),
                    categoria_gama.valueOf(rs.getString("gama")),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        }
        return lista;
    }
}
