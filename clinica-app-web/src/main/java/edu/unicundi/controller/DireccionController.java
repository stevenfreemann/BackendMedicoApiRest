/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.DireccionDto;
import edu.unicundi.entity.Direccion;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.service.IDireccionService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ASUS-PC
 */
@Stateless
@Path("/direccion")
public class DireccionController {
    
    @EJB
    IDireccionService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<Direccion> listaDireccion = service.listarTodos();
        return Response.status(Response.Status.OK).entity(listaDireccion).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response buscarPorId(@PathParam("id") Integer id) throws NotFoundModelException{      
        DireccionDto direccion = service.listar(id);
        return Response.status(Response.Status.OK).entity(direccion).build();
    }     
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid Direccion direccion) throws ObjetcRequiredException {  
        service.guardar(direccion);
        return Response.status(Response.Status.CREATED).build();
    }  
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eliminar/{id}")
    public Response eliminar(@PathParam("id") Integer id) throws NotFoundModelException{      
        service.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }   
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editar")
    public Response editar(@Valid DireccionDto direccion) throws NotFoundModelException {  
        service.editar(direccion);
        return Response.status(Response.Status.CREATED).build();
    }    
    
}
