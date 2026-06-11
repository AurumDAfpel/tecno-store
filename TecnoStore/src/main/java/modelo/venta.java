/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andres Santiago Gomez Rojas
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class venta {
    private int id;
    private cliente cliente;
    private LocalDateTime fecha;
    private List<item_venta> items = new ArrayList<>();
    private double total;

    public venta() {
        this.fecha = LocalDateTime.now();
    }

    public venta(int id, cliente cliente, LocalDateTime fecha, double total) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.total = total;
    }

    public void agregarItem(celular celular, int cantidad) {
        items.add(new item_venta(celular, cantidad));
        calcularTotal();
    }

    private void calcularTotal() {
        double subtotalNeto = items.stream().mapToDouble(item_venta::getSubtotal).sum();
        this.total = subtotalNeto * 1.19; // IVA del 19%
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public cliente getCliente() { return cliente; }
    public void setCliente(cliente cliente) { this.cliente = cliente; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public List<item_venta> getItems() { return items; }
    public void setItems(List<item_venta> items) { this.items = items; }
    public double getTotal() { return total; }
}

