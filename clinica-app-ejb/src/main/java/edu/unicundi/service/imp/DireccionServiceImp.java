/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;


import edu.unicundi.dto.DireccionDto;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Direccion;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.repo.IDireccionRepo;
import edu.unicundi.service.IDetalleConsultaService;
import edu.unicundi.service.IDireccionService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class DireccionServiceImp implements IDireccionService{

    @EJB
    IDireccionRepo repo;   

    @Override
    public List<Direccion> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public DireccionDto listar(int id) throws NotFoundModelException {
        Direccion direccion = repo.listar(id);
        if(direccion == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");

        //Para evitar la relación ciclica pero tiene problema, elimina la información de base datos
        //detalleConsulta.getConsulta().setDetalleConsulta(null);
        
        
        /*dto.setId(detalleConsulta.getId());
        dto.setDiagnostico(detalleConsulta.getDiagnostico());
        dto.setTratamiento(detalleConsulta.getTratamiento());
        ConsultaDto consultaDto = new ConsultaDto();
        consultaDto.setId(detalleConsulta.getConsulta().getId());
        consultaDto.setNombreMedico(detalleConsulta.getConsulta().getNombreMedico());
        consultaDto.setFecha(detalleConsulta.getConsulta().getFecha());
        dto.setConsulta(consultaDto);*/
        
        ModelMapper modelMapper = new ModelMapper();
        DireccionDto dto = modelMapper.map(direccion, DireccionDto.class);     
        dto.getMedico().setDireccion(null);
        return dto;
    }

    @Override
    public void guardar(Direccion direccion) throws ObjetcRequiredException{
        if(direccion.getMedico() == null || direccion.getMedico().getId() == null)
            throw new ObjetcRequiredException("Id Consula es requerido");
        repo.guardar(direccion);
    }

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        Direccion direccion = repo.listar(id);
        if(direccion == null)
            throw new NotFoundModelException("Objeto direccion no encontrado");
        repo.eliminar(direccion);
    }

   @Override
    public void editar(DireccionDto dto) throws NotFoundModelException {
        Direccion direccion = repo.listar(dto.getId());
        if(direccion == null)
            throw new NotFoundModelException("Objeto direccion no encontrado");

      
        if(dto.getDetalle() != null)
            direccion.setDetalle(dto.getDetalle());
        
        repo.editar(direccion);        
    }
}
