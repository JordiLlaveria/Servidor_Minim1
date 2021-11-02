package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

public class ElementComanda {

    private Producto producto;
    private int quantitat;
    private double preutotal=0;

    public ElementComanda(Producto producto, int quantitat){

            this.producto = producto;
            this.quantitat = quantitat;
            this.preutotal=quantitat*producto.getPrecio();
        }

        public double getPreutotal(){
            return this.preutotal;
        }

    public String getNombreProducto(){
        return this.producto.getNombre();
    }

    public int getQuantitat(){
        return this.quantitat;
    }

}
