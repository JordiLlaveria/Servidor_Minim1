package edu.upc.dsa;

import edu.upc.dsa.models.Producto;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

public class ProductoManagerImpl implements ProductoManager {
    private static ProductoManager instance;
    protected List<Producto> productos;
    final static Logger logger = Logger.getLogger(ProductoManagerImpl.class);

    private ProductoManagerImpl() {
        this.productos = new LinkedList<>();
    }

    public static ProductoManager getInstance() {
        if (instance==null) instance = new ProductoManagerImpl();
        return instance;
    }

    public int size() {
        int ret = this.productos.size();
        logger.info("size " + ret);

        return ret;
    }

    public Producto addProducto(Producto t) {
        logger.info("new Track " + t);

        this.productos.add (t);
        logger.info("new Track added");
        return t;
    }

    public Producto addProducto(String nombre, Double precio, Double ventas) {
        return this.addProducto(new Producto(nombre, precio, ventas));
    }

    public Producto getProducto(String nombre) {
        logger.info("getTrack("+ nombre +")");

        for (Producto t: this.productos) {
            if (t.getNombre().equals(nombre)) {
                logger.info("getTrack("+ nombre +"): "+t);

                return t;
            }
        }

        logger.warn("not found " + nombre);
        return null;
    }

    public List<Producto> findAll() {
        return this.productos;
    }

    @Override
    public void deleteProducto(String nombre) {

        Producto t = this.getProducto(nombre);
        if (t==null) {
            logger.warn("not found " + t);
        }
        else logger.info(t+" deleted ");

        this.productos.remove(t);

    }
    @Override
    public Producto updateProducto(Producto p) {
        Producto t = this.getProducto(p.getNombre());
        return t;
        /*

        if (t!=null) {
            logger.info(p+" rebut!!!! ");

            t.setVentas(p.getVentas());
            t.setPrecio(p.getPrecio());

            logger.info(t+" updated ");
        }
        else {
            logger.warn("not found "+p);
        }

        return t;
        */
    }
}