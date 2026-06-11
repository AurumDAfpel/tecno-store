/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tecnostore;

/**
 *
 * @author Andres Santiago Gomez Rojas
 */
import servicio.GestorCelulares;
import servicio.GestorClientes;
import servicio.GestorVentas;
import modelo.*;
import patron.FactoryCelular;
import util.utils;
import util.validar;

import java.util.List;
import java.util.Scanner;

public class TecnoStore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorCelulares gestorCelulares = new GestorCelulares();
        GestorClientes gestorClientes = new GestorClientes();
        GestorVentas gestorVentas = new GestorVentas();

        while (true) {
            System.out.println("\n=================================");
            System.out.println("     SISTEMA TECNOSTORE - MENÚ    ");
            System.out.println("=================================");
            System.out.println("1. Registrar Celular (Factory)");
            System.out.println("2. Listar Celulares");
            System.out.println("3. Actualizar Celular");
            System.out.println("4. Eliminar Celular");
            System.out.println("5. Registrar Cliente");
            System.out.println("6. Registrar Venta (Transacción SQL)");
            System.out.println("7. Reporte: Stock Bajo (< 5 unidades)");
            System.out.println("8. Reporte: Ventas por Mes");
            System.out.println("9. Generar archivo 'reporte_ventas.txt'");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar salto de línea

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Marca: "); String marca = sc.nextLine();
                        System.out.print("Modelo: "); String modelo = sc.nextLine();
                        System.out.print("S.O: "); String so = sc.nextLine();
                        System.out.print("Gama (ALTA, MEDIA, BAJA): "); String gama = sc.nextLine();
                        System.out.print("Precio: "); double precio = sc.nextDouble();
                        System.out.print("Stock: "); int stock = sc.nextInt();

                        if (!validar.esPositivo(precio) || !validar.esPositivo(stock)) {
                            System.out.println("[Error] El precio y stock deben ser positivos.");
                            break;
                        }

                        celular nuevoCel = FactoryCelular.crearCelular(marca, modelo, so, gama, precio, stock);
                        gestorCelulares.registrarCelular(nuevoCel);
                        System.out.println("¡Celular guardado con éxito! ID: " + nuevoCel.getId());
                        break;

                    case 2:
                        System.out.println("\n--- CATÁLOGO DE CELULARES ---");
                        gestorCelulares.listarCelulares().forEach(System.out::println);
                        break;

                    case 3:
                        System.out.print("ID del celular a modificar: "); int idMod = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Nueva Marca: "); String nMarca = sc.nextLine();
                        System.out.print("Nuevo Modelo: "); String nModelo = sc.nextLine();
                        System.out.print("Nuevo S.O: "); String nSo = sc.nextLine();
                        System.out.print("Nueva Gama (ALTA, MEDIA, BAJA): "); String nGama = sc.nextLine();
                        System.out.print("Nuevo Precio: "); double nPrecio = sc.nextDouble();
                        System.out.print("Nuevo Stock: "); int nStock = sc.nextInt();

                        celular celMod = new celular(idMod, nMarca, nModelo, nSo, categoria_gama.valueOf(nGama.toUpperCase()), nPrecio, nStock);
                        gestorCelulares.actualizarCelular(celMod);
                        System.out.println("¡Celular modificado!");
                        break;

                    case 4:
                        System.out.print("ID del celular a eliminar: "); int idEli = sc.nextInt();
                        gestorCelulares.eliminarCelular(idEli);
                        System.out.println("¡Celular eliminado con éxito!");
                        break;

                    case 5:
                        System.out.print("Nombre completo: "); String nom = sc.nextLine();
                        System.out.print("Identificación única: "); String iden = sc.nextLine();
                        System.out.print("Correo electrónico: "); String correo = sc.nextLine();
                        System.out.print("Teléfono: "); String tel = sc.nextLine();

                        if (!validar.validarCorreo(correo)) {
                            System.out.println("[Error] El formato del correo electrónico es inválido.");
                            break;
                        }
                        if (gestorClientes.existeIdentificacion(iden)) {
                            System.out.println("[Error] La identificación ya existe.");
                            break;
                        }

                        cliente nuevoCli = new cliente(0, nom, iden, correo, tel);
                        gestorClientes.registrarCliente(nuevoCli);
                        System.out.println("¡Cliente guardado exitosamente!");
                        break;

                    case 6:
                        System.out.println("--- REGISTRO DE VENTA ---");
                        List<cliente> clientes = gestorClientes.listarClientes();
                        clientes.forEach(System.out::println);
                        System.out.print("Seleccione ID del Cliente: "); int idCli = sc.nextInt();
                        cliente cSel = clientes.stream().filter(c -> c.getId() == idCli).findFirst().orElse(null);

                        if (cSel == null) { System.out.println("Cliente no válido."); break; }

                        List<celular> celulares = gestorCelulares.listarCelulares();
                        celulares.forEach(System.out::println);
                        System.out.print("Seleccione ID del Celular: "); int idCel = sc.nextInt();
                        celular celSel = celulares.stream().filter(c -> c.getId() == idCel).findFirst().orElse(null);

                        if (celSel == null || celSel.getStock() <= 0) { System.out.println("Celular sin stock."); break; }

                        System.out.print("Cantidad: "); int cant = sc.nextInt();
                        if (cant > celSel.getStock()) { System.out.println("Stock insuficiente."); break; }

                        venta venta = new venta();
                        venta.setCliente(cSel);
                        venta.agregarItem(celSel, cant);

                        gestorVentas.registrarVenta(venta);
                        System.out.printf("¡Venta completada! Total (con IVA 19%%): $%.2f\n", venta.getTotal());
                        break;

                    case 7:
                        gestorVentas.reportarStockBajo();
                        break;

                    case 8:
                        gestorVentas.reportarVentasPorMes();
                        break;

                    case 9:
                        List<venta> listaVentas = gestorVentas.obtenerVentas();
                        utils.generarReporteVentasTxt(listaVentas, "reporte_ventas.txt");
                        break;

                    case 10:
                        System.out.println("Saliendo del panel...");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            }
        }
    }
}
