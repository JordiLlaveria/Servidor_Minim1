package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Producto implements Comparable<Producto>{

    String nombre;
    double precio;
    double ventas;
    static int lastId;

    public Producto(){}

    public Producto(String nombre, double precio) {
        this();
        this.setNombre(nombre);
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

    public void ventaRealizada(int numero){
        this.ventas = this.ventas + numero;
    }

    public int compareTo(Producto a)
    {
        int res = (int) (this.getPrecio()-a.getPrecio());
        return res;
    }

}