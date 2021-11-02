package edu.upc.dsa.services;

import edu.upc.dsa.*;
import edu.upc.dsa.models.Producto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/product", description = "Endpoint to Track Service")
@Path("/product")
public class ProductsService extends EmptyList{
    private Manager tm;
        public ProductsService() throws EmptyList {
            this.tm = ManagerImpl.getInstance();
            if (this.tm.size()==0){
                this.tm.añadirUsuario(new Usuari("Jordi","1"));
                this.tm.añadirUsuario(new Usuari("Joana","2"));

                Producto cafe = new Producto("Cafe",0.8);
                Producto cheese_cake = new Producto("Cheese Cake",2.5);
                Producto croissant = new Producto("Croissant",1.2);
                Producto donut = new Producto("Donut",1.1);
                this.tm.añadirProductoLista(cafe);
                this.tm.añadirProductoLista(cheese_cake);
                this.tm.añadirProductoLista(croissant);
                this.tm.añadirProductoLista(donut);

                Comanda comanda = new Comanda("1");
                comanda.addLP(2,cafe);
                comanda.addLP(1,donut);
                this.tm.realizarPedido(comanda);
            }
    }
    /*
    @GET
    @ApiOperation(value = "realizar pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(Usuari usuario, Producto producto) {
        //Comanda comanda = new Comanda(usuario.getUsuariID());
        //comanda.addLP(2, donut);
        //comanda.addLP(1, cafe);
        //comanda.addLP(4,croissant);

        //manager.getInstance().realizarPedido(comanda);
        //List<Producto> productos = this.tm.findAll();

        GenericEntity<Producto> entity = new GenericEntity<Producto>(cafe) {};
        return Response.status(201).entity(entity).build();

    }
    */
    @GET
    @ApiOperation(value = "get Productos Ordenados Precio", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/ordenados_precio")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosPrecio() throws EmptyList {

        List<Producto> productos = this.tm.ordenarProductosPrecio();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @GET
    @ApiOperation(value = "get Comandes User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class, responseContainer="List"),
            @ApiResponse(code = 404, message = "Comanda not found")
    })
    @Path("/{id_usuari}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComandesUser(@PathParam("id_usuari") String idusuari) {
        List<Comanda> comandas_user = tm.listadoPedidosUser(idusuari);
        GenericEntity<List<Comanda>> entity = new GenericEntity<List<Comanda>>(comandas_user) {};
        return Response.status(201).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "get Productos Ordenados Ventas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/ordenados_ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosVentas() throws EmptyList {

        List<Producto> ventas = this.tm.ordenarProductosVentas();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(ventas) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @GET
    @ApiOperation(value = "servir pedido", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/servir pedido")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTrack() {
        this.tm.servirPedido();
        return Response.status(404).build();
    }
    /*
    @DELETE
    @ApiOperation(value = "delete a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{nombre}")
    public Response deleteTrack(@PathParam("nombre") String nombre) {
        Producto t = this.tm.getProducto(nombre);
        if (t == null) return Response.status(404).build();
        else this.tm.deleteProducto(nombre);
        return Response.status(201).build();
    }

    @PUT
    @ApiOperation(value = "update a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful"),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/")
    public Response updateProduct(Producto producto) {

        Producto t = this.tm.updateProducto(producto);

        if (t == null) return Response.status(404).build();

        return Response.status(201).build();
    }

    @POST
    @ApiOperation(value = "create a new Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Producto.class),
            @ApiResponse(code = 500, message = "Validation Error")

    })
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newProduct(Producto producto) {

        if (producto.getVentas()==0 || producto.getPrecio()==0)  return Response.status(500).entity(producto).build();
        this.tm.addProducto(producto);
        return Response.status(201).entity(producto).build();
    }
    */

}