package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Producto {

    String nombre;
    double precio;
    double ventas;
    static int lastId;

    public Producto(){}

    public Producto(String nombre, double precio, double ventas) {
        this();
        this.setNombre(nombre);
        this.setVentas(ventas);
        this.setPrecio(precio);
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getVentas() {
        return ventas;
    }

    public void setVentas(double ventas) {
        this.ventas = ventas;
    }

    @Override
    public String toString() {
        return "Producto[id="+ nombre +", precio=" + precio + ", ventas=" + ventas +"]";
    }

}