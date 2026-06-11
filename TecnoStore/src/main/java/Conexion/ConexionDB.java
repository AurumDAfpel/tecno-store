/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

/**
 *
 * @author Janus
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "mysql://avnadmin:AVNS_BY1uBEpznFWa6F-GDPA@mysql-244c2b64-santiagogomezrojas2007-43b2.l.aivencloud.com:20146/defaultdb?ssl-mode=REQUIRED";
    private static final String USER = "";
    private static final String PASSWORD = ""; 
    private static Connection conexion = null;

    private ConexionDB() {}

    public static Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            try {
                // Registro del Driver cargado desde tu archivo JAR
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("No se encontró el driver de MySQL en el proyecto.", e);
            }
        }
        return conexion;
    }
}
