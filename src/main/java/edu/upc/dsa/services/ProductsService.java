package edu.upc.dsa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upc.dsa.*;
import edu.upc.dsa.models.Producto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.swing.text.Element;
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
                Producto donut = new Producto("Donut",1.4);
                this.tm.añadirProductoLista(cafe);
                this.tm.añadirProductoLista(cheese_cake);
                this.tm.añadirProductoLista(croissant);
                this.tm.añadirProductoLista(donut);

                Comanda comanda = new Comanda("2");
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
    @Path("/ordenats_preu")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosPrecio() throws EmptyList {

        List<Producto> productos = this.tm.ordenarProductosPrecio();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(201).entity(entity).build()  ;
    }

    @GET
    @ApiOperation(value = "get Comandes User", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class),
            @ApiResponse(code = 404, message = "Comanda not found")
    })
    @Path("/{id_usuari}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComandesUser(@PathParam("id_usuari") String idusuari) {
        List<Comanda> comandas_user = tm.listadoPedidosUser(idusuari);
        GenericEntity<List<Comanda>> entity = new GenericEntity<List<Comanda>>(comandas_user) {};
        Gson jsonConverter = new GsonBuilder().create();
        return Response.status(201).entity(jsonConverter.toJson(comandas_user)).build();
    }

    @GET
    @ApiOperation(value = "get Productos Ordenados Ventas", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/ordenats_ventas")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenadosVentas() throws EmptyList {

        List<Producto> ventas = this.tm.ordenarProductosVentas();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(ventas) {};
        return Response.status(201).entity(entity).build()  ;
    }
    @GET
    @ApiOperation(value = "Servir Comanda", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class),
            @ApiResponse(code = 404, message = "La comanda no pot ser servida perquè no existeix")
    })
    @Path("/servir_comanda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response servirPedido() {
        if (this.tm.getSizePedidos()!=0) {
            this.tm.servirPedido();
            return Response.status(201).build();
        }
        else{
            return Response.status(404).build();
        }
    }
    @POST
    @ApiOperation(value = "Realitzar comanda", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Comanda.class),
            @ApiResponse(code = 404, message = "Comanda couldn't be done")
    })
    @Path("/realitzar_comanda")
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(Comanda comanda) {
        if (comanda.getUsuariID()!=null || comanda.getLlistaCompra() != null){
            this.tm.realizarPedido(comanda);
            return Response.status(201).entity(comanda).build();
        }
        else{
            return Response.status(404).entity(comanda).build();
        }
    }
}