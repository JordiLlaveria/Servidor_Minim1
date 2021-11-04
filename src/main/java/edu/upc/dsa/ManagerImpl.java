package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.*;

import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {

    //Implementació Singleton
    private static ManagerImpl instance;
    private ManagerImpl(){}
    public static ManagerImpl getInstance(){
        if (instance==null){
            instance=new ManagerImpl();
        }
        return instance;
    }

    //Implementació Log4j
    final static Logger logger = Logger.getLogger(ManagerImpl.class);
    //logger.info("");

    private Queue<Comanda> misComandas = new LinkedList<Comanda>();
    private List<Producto> listaProductos = new LinkedList<Producto>();
    private Hashtable<String, Usuari> usuaris = new Hashtable<String, Usuari>();
    private OrdenarProductoPerVentas o;

    public int GetNumComandes() {
        return misComandas.size();
    }
    public int GetNumProductos() { return listaProductos.size();}
    public int GetNumUsuarios(){ return usuaris.size();}
    public Producto GetProducteMesEconomic() {return listaProductos.get(0);}

    public List<Producto> ordenarProductosPrecio(){
        List<Producto> listaOrdenada = this.listaProductos;
        Collections.sort(listaOrdenada);
        return listaOrdenada;
    }

    public void realizarPedido(Comanda comanda) {

        this.misComandas.add(comanda);
        logger.info("Pedido realizado");
    }

    @Override
    public void servirPedido() {
        for(ElementComanda e : misComandas.peek().getLlistaCompra()){
            for(Producto p : listaProductos){
                if (e.getNombreProducto() == p.getNombre()){
                    p.ventaRealizada(e.getQuantitat());
                }
            }
        }

        String usuariID = misComandas.peek().getUsuariID();
        Usuari usuari = this.usuaris.get(usuariID);
        usuari.afegirComanda(misComandas.poll());
    }

    @Override
    public List<Comanda> listadoPedidosUser(String usuariID) {
        return this.usuaris.get(usuariID).getLlistaComandesServides();
    }

    @Override
    public List<Producto> ordenarProductosVentas(){
        List<Producto> listaOrdenada = this.listaProductos;
        Collections.sort(listaOrdenada,new OrdenarProductoPerVentas().reversed());
        return listaOrdenada;
    }

    @Override
    public void añadirProductoLista(Producto producto){
        this.listaProductos.add(producto);
    }

    public void añadirUsuario(Usuari usuari){
        usuaris.put(usuari.getUsuariID(), usuari);
    }

    @Override
    public int size() {
        return listaProductos.size();
    }

    @Override
    public int getSizePedidos() {
        return misComandas.size();
    }

    public double getSizeProductos(){ return listaProductos.size();}


}
