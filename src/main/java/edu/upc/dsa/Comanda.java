package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.LinkedList;
import java.util.List;

public class Comanda {

    //private String usuari;
    private String usuariID;
    private List<ElementComanda> llistaCompra = new LinkedList<ElementComanda>();
    private double preuTotal;

    public Comanda(String userID) { // Constructor
        this.usuariID = userID;
        this.llistaCompra = new LinkedList<ElementComanda>();
        this.preuTotal = 0;
    }

    /*
    public Comanda(Usuari user) { // Constructor
        this.usuari = user.getNomUsuari();
        this.usuariID = user.getUsuariID();
        this.preuTotal = 0;
    }
    */

    public String getUsuariID() {
        return this.usuariID;
    }

    public List<ElementComanda> getLlistaCompra(){
        return this.llistaCompra;
    }


    public void addLP(int quantitat, Producto producto) {
        //producto.ventaRealizada(quantitat);
        //ElementComanda comanda = new ElementComanda(producto,quantitat);
        //this.llistaCompra.add(comanda);
        this.llistaCompra.add(new ElementComanda(producto, quantitat));
        preuTotal=this.preuTotal+quantitat*producto.getPrecio();

    }

}
