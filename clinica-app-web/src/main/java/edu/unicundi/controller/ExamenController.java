/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;


import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.service.IExamenService;
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
@Path("/examen")
public class ExamenController {
    
    @EJB
    IExamenService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<Examen> listaExamen= service.listarTodos();
        return Response.status(Response.Status.OK).entity(listaExamen).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{idexamen}")
    public Response buscarPorId(@PathParam("idexamen") Integer id) throws NotFoundModelException{      
        ExamenDto examen = service.listar(id);
        return Response.status(Response.Status.OK).entity(examen).build();
    }     
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid Examen examen) throws ObjetcRequiredException {  
        service.guardar(examen);
        return Response.status(Response.Status.CREATED).build();
    }  
    
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/eliminar/{idexamen}")
    public Response eliminar(@PathParam("idexamen") Integer idexamen) throws NotFoundModelException{      
        service.eliminar(idexamen);
        return Response.status(Response.Status.NO_CONTENT).build();
    }   
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editar")
    public Response editar(@Valid ExamenDto examen) throws NotFoundModelException {  
        service.editar(examen);
        return Response.status(Response.Status.CREATED).build();
    }    
    
}
