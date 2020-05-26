/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.controller;

import edu.unicundi.dto.ConsultaExamenDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.ConsultaExamen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.service.IConsultaExamenService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/consultasexamenes")
public class ConsultaExamenController {

    @EJB
    private IConsultaExamenService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar")
    public Response buscar(){      
        List<ConsultaExamenListaDto> listaConsultaExamenListaDto = service.listar();
        return Response.status(Response.Status.OK).entity(listaConsultaExamenListaDto).build();
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscarPorIdConsulta/{idConsulta}")
    public Response buscarPorIdCosnulta(@PathParam("idConsulta") Integer idConsulta) throws NotFoundModelException{      
        ConsultaExamenListaDto consultaExamenListaDto = service.listarPorConsultaOpc2(idConsulta);
        return Response.status(Response.Status.OK).entity(consultaExamenListaDto).build();
    }    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/buscar/{id}")
    public Response buscarPorIdConsulta(@PathParam("id") Integer id) throws NotFoundModelException{      
        List<ConsultaExamen> listaConsultaExamen = service.listarPorConsulta(id);
        return Response.status(Response.Status.OK).entity(listaConsultaExamen).build();
    }     
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/guardar")
    public Response guardar(@Valid ConsultaExamenDto dto) {  
        service.guardar(dto);
        return Response.status(Response.Status.CREATED).build();
    }      
}
