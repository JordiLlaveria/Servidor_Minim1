package edu.upc.dsa.services;


import edu.upc.dsa.ProductoManager;
import edu.upc.dsa.ProductoManagerImpl;
import edu.upc.dsa.models.Producto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/product", description = "Endpoint to Track Service")
@Path("/product")
public class ProductsService {

    private ProductoManager tm;

    public ProductsService() {
        this.tm = ProductoManagerImpl.getInstance();
        if (tm.size()==0) {
            this.tm.addProducto("Cafe",0.8, 1.0);
            this.tm.addProducto("Cheese Cake",2.5, 1.0);
            this.tm.addProducto("Croissant",1.2, 1.0);
        }
    }

    @GET
    @ApiOperation(value = "get all Products", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class, responseContainer="List"),
    })
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response loadProducts() {

        List<Producto> productos = this.tm.findAll();

        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(201).entity(entity).build()  ;

    }

    @GET
    @ApiOperation(value = "get a Product", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Producto.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @Path("/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("nombre") String nombre) {
        Producto t = this.tm.getProducto(nombre);
        if (t == null) return Response.status(404).build();
        else  return Response.status(201).entity(t).build();
    }

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

}