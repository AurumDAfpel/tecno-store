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
import modelo.venta;
import DAO.CelularDAO;
import DAO.VentaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorVentas {
    private VentaDAO ventaDAO = new VentaDAO();
    private CelularDAO celularDAO = new CelularDAO();

    public void registrarVenta(venta v) throws SQLException {
        ventaDAO.registrarVenta(v);
    }

    public List<venta> obtenerVentas() throws SQLException {
        return ventaDAO.listarVentasCompletas();
    }

    // Análisis Stream API: Celulares con stock bajo (< 5)
    public void reportarStockBajo() throws SQLException {
        List<celular> celulares = celularDAO.listarTodos();
        System.out.println("\n--- REPORTE: CELULARES CON STOCK BAJO (< 5 Unidades) ---");
        celulares.stream()
                .filter(c -> c.getStock() < 5)
                .forEach(System.out::println);
    }

    // Análisis Stream API: Ventas totales agrupadas por Mes
    public void reportarVentasPorMes() throws SQLException {
        List<venta> ventas = ventaDAO.listarVentasCompletas();
        System.out.println("\n--- REPORTE: VENTAS TOTALES POR MES ---");
        
        Map<String, Double> porMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth().name() + " " + v.getFecha().getYear(),
                        Collectors.summingDouble(venta::getTotal)
                ));

        porMes.forEach((mes, total) -> 
            System.out.printf("Periodo: %s | Total Recaudado (con IVA): $%.2f\n", mes, total)
        );
    }
}
