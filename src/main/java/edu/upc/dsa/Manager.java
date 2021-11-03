package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.List;

public interface Manager {
    public List<Producto> ordenarProductosPrecio() throws EmptyList; //TEST FET
    public void realizarPedido(Comanda comanda); //TEST FET
    public void servirPedido(); //TEST FET
    public List<Comanda> listadoPedidosUser(String nombre); //TEST FET
    public List<Producto> ordenarProductosVentas() throws EmptyList; //TEST FET
    public void añadirProductoLista(Producto producto);
    public void añadirUsuario(Usuari usuari);

    public int size();
    public int getSizePedidos();

}
