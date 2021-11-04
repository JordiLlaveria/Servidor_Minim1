package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ManagerTest {
    ManagerImpl manager;
    Comanda comanda;
    final static Logger logger = Logger.getLogger(ManagerTest.class);
    @Before
    public void setUp  () {
        //Declarat null per fer us del Singleton
        manager = null;

        manager.getInstance().añadirUsuario(new Usuari("Joana","22222222X"));
        manager.getInstance().añadirUsuario(new Usuari("Jordi","33333333Y"));
        manager.getInstance().añadirUsuario(new Usuari("Aida","11111111Z"));

        Producto cafe = new Producto("Cafe",0.8);
        Producto cheesecake = new Producto("Cheese Cake",2.5);
        Producto croissant = new Producto("Croissant",1.2);
        Producto sucdetaronja = new Producto("Suc de taronja",4.1);
        Producto donut = new Producto("Donut",1.30);

        manager.getInstance().añadirProductoLista(cafe);
        manager.getInstance().añadirProductoLista(cheesecake);
        manager.getInstance().añadirProductoLista(croissant);
        manager.getInstance().añadirProductoLista(sucdetaronja);
        manager.getInstance().añadirProductoLista(donut);

        comanda = new Comanda("11111111Z");
        comanda.addLP(2, donut);
        comanda.addLP(1, cafe);
        comanda.addLP(4,croissant);

        manager.getInstance().realizarPedido(comanda);
    }
    /*
    public static void main(String[] args) {
        ManagerImpl manager;
        Comanda comanda;
        manager = new ManagerImpl();
        manager.añadirUsuario(new Usuari("Joana","22222222X"));
        manager.añadirUsuario(new Usuari("Jordi","33333333Y"));
        manager.añadirUsuario(new Usuari("Aida","11111111Z"));

        Producto cafe = new Producto("Cafe",0.8);
        Producto cheesecake = new Producto("Cheese Cake",2.5);
        Producto croissant = new Producto("Croissant",1.2);
        Producto sucdetaronja = new Producto("Suc de taronja",4.1);
        Producto donut = new Producto("Donut",1.30);

        manager.añadirProductoLista(cafe);
        manager.añadirProductoLista(cheesecake);
        manager.añadirProductoLista(croissant);
        manager.añadirProductoLista(sucdetaronja);
        manager.añadirProductoLista(donut);

        comanda = new Comanda("11111111Z");
        comanda.addLP(2, donut);
        comanda.addLP(1, cafe);
        comanda.addLP(4,croissant);

        manager.realizarPedido(comanda);

        Comanda comanda2 = new Comanda("22222222X");
        comanda2.addLP(1,croissant);
        comanda2.addLP(1,cafe);

        manager.realizarPedido(comanda2);

        Comanda comanda3 = new Comanda("33333333Y");
        comanda3.addLP(1,cheesecake);
        comanda3.addLP(1,sucdetaronja);

        manager.realizarPedido(comanda3);

        manager.servirPedido();
        manager.servirPedido();

        Comanda comanda4 = new Comanda("11111111Z");
        comanda4.addLP(2,donut);
        comanda4.addLP(2,sucdetaronja);
        manager.realizarPedido(comanda4);

        manager.servirPedido();
        manager.servirPedido();

        List<Producto> miListaOrdenadaPrecio = manager.ordenarProductosPrecio();
        List<Producto> miListaOrdenadaVentas = manager.ordenarProductosVentas();
        List<Comanda> miListaPedidosUsuario = manager.listadoPedidosUser("11111111Z");

    }
     */

    @Test
    public void ProbaRealizarPedido() {
        Comanda miComanda = new Comanda("11111111Z");
        logger.info("Pedido realizado");
        Assert.assertEquals(manager.getInstance().GetNumComandes(),1);
        manager.getInstance().realizarPedido(comanda);
        Assert.assertEquals(manager.getInstance().GetNumComandes(),2);
    }
    @Test
    public void ProbaServirPedido() {
        Assert.assertEquals(comanda.getUsuariID(), "11111111Z");
        Assert.assertEquals(manager.getInstance().GetNumComandes(), 1);
        manager.getInstance().servirPedido();
        Assert.assertEquals(manager.getInstance().GetNumComandes(), 0);
    }
    @Test
    public void ProbaListadoPedidosUser() {
        List<Comanda> comandas_1 = new LinkedList<>();
        Comanda miComanda = new Comanda("11111111Z");
        comandas_1.add(miComanda);
        Assert.assertEquals(manager.getInstance().listadoPedidosUser("11111111Z"), comandas_1);
    }
    @Test
    public void ProbaOrdenarProductosPrecio() {
        List<Producto> misProductos = new LinkedList<>();
        Producto cafe = new Producto("Cafe", 0.8); //1r
        Producto cheesecake = new Producto("Cheese Cake", 2.5); //4t
        Producto croissant = new Producto("Croissant", 1.2); //2n
        Producto sucdetaronja = new Producto("Suc de taronja",4.1); //5e
        Producto donut = new Producto("Donut",1.30); //3r
        misProductos.add(cafe);
        misProductos.add(croissant);
        misProductos.add(cheesecake);
        misProductos.add(sucdetaronja);
        misProductos.add(donut);
        Assert.assertEquals(manager.getInstance().GetProducteMesEconomic().getPrecio(),cafe.getPrecio(),0);
    }
    @Test
    public void ProbaOrdenarProductosVentas() {
        Comanda miComanda = new Comanda("11111111Z");
        Producto cafe = new Producto("Cafe", 0.8);
        Producto cheesecake = new Producto("Cheese Cake", 2.5);
        Producto croissant = new Producto("Croissant", 1.2);
        miComanda.addLP(3, cafe);
        miComanda.addLP(1, cheesecake);
        miComanda.addLP(2, croissant);
        List<Producto> misProductos2 = new LinkedList<>();
        misProductos2.add(cheesecake);
        misProductos2.add(croissant);
        misProductos2.add(cafe);
        Assert.assertEquals(manager.getInstance().ordenarProductosVentas(), misProductos2);
    }
    @Test
    public void ProbaAñadirProductoLista(){
        Assert.assertEquals(manager.getInstance().GetNumProductos(),0);
        Producto cafe = new Producto("Cafe",1);
        manager.getInstance().añadirProductoLista(cafe);
        Assert.assertEquals(manager.getInstance().GetNumProductos(),1);
    }
    @Test
    public void ProbaAñadirUsuario(){
        Assert.assertEquals(manager.getInstance().GetNumUsuarios(),0);
        Usuari Aida = new Usuari("Aida","11111111T");
        manager.getInstance().añadirUsuario(Aida);
        Assert.assertEquals(manager.getInstance().GetNumUsuarios(),1);
    }

}
