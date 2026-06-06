/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Andres Santiago Gomez Rojas
 */
public class celular {
    private int id;
    private String marca;
    private String modelo;
    private String sistemaOperativo;
    private categoria_gama gama;
    private double precio;
    private int stock;

    public celular() {}

    public celular(int id, String marca, String modelo, String sistemaOperativo, categoria_gama gama, double precio, int stock) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.gama = gama;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getSistemaOperativo() { return sistemaOperativo; }
    public void setSistemaOperativo(String sistemaOperativo) { this.sistemaOperativo = sistemaOperativo; }
    public categoria_gama getGama() { return gama; }
    public void setGama(categoria_gama gama) { this.gama = gama; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    @Override
    public String toString() {
        return String.format("ID: %d | %s %s (%s) | Gama: %s | Precio: $%.2f | Stock: %d", 
                id, marca, modelo, sistemaOperativo, gama, precio, stock);
    }
}

