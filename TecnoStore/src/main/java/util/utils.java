/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author Janus
 */
import modelo.venta;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class utils {
    public static void generarReporteVentasTxt(List<venta> ventas, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("==================================================\n");
            bw.write("         REPORTE GLOBAL DE VENTAS - TECNOSTORE     \n");
            bw.write("==================================================\n\n");
            
            double totalAcumulado = 0;
            for (venta v : ventas) {
                bw.write(String.format("Venta ID: %d | Fecha: %s\n", v.getId(), v.getFecha().toString()));
                bw.write(String.format("Cliente: %s (%s)\n", v.getCliente().getNombre(), v.getCliente().getIdentificacion()));
                bw.write(String.format("Total Facturado (con IVA 19%%): $%.2f\n", v.getTotal()));
                bw.write("--------------------------------------------------\n");
                totalAcumulado += v.getTotal();
            }
            bw.write(String.format("\nGran Total Recaudado en Tienda: $%.2f\n", totalAcumulado));
            System.out.println("\n[OK] Archivo '" + rutaArchivo + "' generado de forma exitosa en la raíz del proyecto.");
        } catch (IOException e) {
            System.err.println("Error al procesar la escritura del reporte: " + e.getMessage());
        }
    }
}
