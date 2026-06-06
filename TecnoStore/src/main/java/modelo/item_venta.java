/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andres Santiago Gomez Rojas
 */
public class item_venta {

    private celular celular;
    private int cantidad;
    private double subtotal;

    public item_venta() {
    }

    public item_venta(celular celular, int cantidad) {
        this.celular = celular;
        this.cantidad = cantidad;
        this.subtotal = celular.getPrecio() * cantidad;
    }

    public celular getCelular() {
        return celular;
    }

    public void setCelular(celular celular) {
        this.celular = celular;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        recalcularSubtotal();
    }

    public double getSubtotal() {
        return subtotal;
    }

    private void recalcularSubtotal() {
        if (celular != null) {
            subtotal = celular.getPrecio() * cantidad;
        }
    }

    @Override
    public String toString() {
        return "ItemVenta{" +
                "celular=" + celular.getModelo() +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}

