/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron;

/**
 *
 * @author Janus
 */
import modelo.celular;
import modelo.categoria_gama;

public class FactoryCelular {
    public static celular crearCelular(String marca, String modelo, String so, String gamaStr, double precio, int stock) {
        categoria_gama gama = categoria_gama.valueOf(gamaStr.toUpperCase());
        return new celular(0, marca, modelo, so, gama, precio, stock);
    }
}
