/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.MedicoDto;
import edu.unicundi.entity.Medico;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.service.IMedicoService;
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
@Path("/medicos")
public class MedicoController {
    
    @EJB
    private IMedicoService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<Medico> listaMedico = service.listarTodos();
        return Response.status(Response.Status.OK).entity(listaMedico).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response buscar(@PathParam("id") Integer id) throws NotFoundModelException{      
        MedicoDto medico = service.listar(id);
        return Response.status(Response.Status.OK).entity(medico).build();
    }   
    
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid Medico medico) {  
        service.guardar(medico);
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
    public Response editar(@Valid MedicoDto dto) throws NotFoundModelException{  
        service.editar(dto);
        return Response.status(Response.Status.OK).build();
    }       
    
}
