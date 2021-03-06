/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.service.IConsultaService;
import edu.unicundi.view.ConsultaView;
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
@Path("/consultas")
public class ConsultaController {
    
   @EJB
   IConsultaService service;    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<Consulta> listaConsulta = service.listarTodos();
        return Response.status(Response.Status.OK).entity(listaConsulta).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarOptimoPaginado/{limit}/{offSet}")
    public Response buscarOptimoPaginado(@PathParam("limit") Integer limit, @PathParam("offSet") Integer offSet){      
        List<ConsultaView> listaConsulta = service.listarOptimoPaginado(limit, offSet);
        return Response.status(Response.Status.OK).entity(listaConsulta).build();
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response buscar(@PathParam("id") Integer id) throws NotFoundModelException{      
        Consulta consulta = service.listar(id);
        return Response.status(Response.Status.OK).entity(consulta).build();
    }   
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid Consulta consulta) {  
        service.guardar(consulta);
        return Response.status(Response.Status.CREATED).build();
    }      
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardarTransaccion")
    public Response guardar(ConsultaExamenListaDto dto) throws NotFoundModelException {  
        service.guardarTransaccion(dto);
        return Response.status(Response.Status.CREATED).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardarTransaccion2")
    public Response guardar2(ConsultaExamenListaDto dto) throws NotFoundModelException {  
        service.guardarTransaccion2(dto);
        return Response.status(Response.Status.CREATED).build();
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardarTransaccion3")
    public Response guardar3(ConsultaExamenListaDto dto) throws NotFoundModelException {  
        service.guardarTransaccion3(dto);
        return Response.status(Response.Status.CREATED).build();
    } 
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardarTransaccion4")
    public Response guardar4(ConsultaExamenListaDto dto) throws NotFoundModelException {  
        service.guardarTransaccion4(dto);
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
    public Response editar(@Valid ConsultaDto consulta) throws NotFoundModelException {      
        service.editar(consulta);
        return Response.status(Response.Status.OK).build();
    }       
  
}
