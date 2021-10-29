package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.List;

public interface ProductoManager {


    public Producto addProducto(String nombre, Double precio, Double ventas);
    public Producto addProducto(Producto t);
    public Producto getProducto(String nombre);
    public List<Producto> findAll();
    public void deleteProducto(String nombre);
    public Producto updateProducto(Producto t);

    public int size();
}
