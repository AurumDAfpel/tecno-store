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
import modelo.Venta;
import persistencia.CelularDAO;
import persistencia.VentaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorVentas {
    private VentaDAO ventaDAO = new VentaDAO();
    private CelularDAO celularDAO = new CelularDAO();

    public void registrarVenta(Venta v) throws SQLException {
        ventaDAO.registrarVenta(v);
    }

    public List<Venta> obtenerVentas() throws SQLException {
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
        List<Venta> ventas = ventaDAO.listarVentasCompletas();
        System.out.println("\n--- REPORTE: VENTAS TOTALES POR MES ---");
        
        Map<String, Double> porMes = ventas.stream()
                .collect(Collectors.groupingBy(
                        v -> v.getFecha().getMonth().name() + " " + v.getFecha().getYear(),
                        Collectors.summingDouble(Venta::getTotal)
                ));

        porMes.forEach((mes, total) -> 
            System.out.printf("Periodo: %s | Total Recaudado (con IVA): $%.2f\n", mes, total)
        );
    }
}
