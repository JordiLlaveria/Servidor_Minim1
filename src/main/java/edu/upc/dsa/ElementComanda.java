package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

public class ElementComanda {

    private Producto producto;
    private int quantitat;
    private double preutotal=0;

    public ElementComanda(){}
    public ElementComanda(Producto producto, int quantitat){

            this.producto = producto;
            this.quantitat = quantitat;
            this.preutotal=quantitat*producto.getPrecio();
    }
    public double getPreutotal(){ return this.preutotal;}
    public void setPreutotal(double preu){this.preutotal=preu;}

    public void setProducto(Producto p){this.producto=p;}
    public Producto getProducto(){return this.producto;}

    public void setQuantitat(int q){this.quantitat=q;}
    public String getNombreProducto(){
        return this.producto.getNombre();
    }

    public int getQuantitat(){
        return this.quantitat;
    }

}
