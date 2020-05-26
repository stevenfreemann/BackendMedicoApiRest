/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.repo.IDetalleConsultaRepo;
import edu.unicundi.service.IDetalleConsultaService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class DetalleConsultaServiceImp implements IDetalleConsultaService{

    @EJB
    IDetalleConsultaRepo repo;   

    @Override
    public List<DetalleConsulta> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public DetalleConsultaDto listar(int id) throws NotFoundModelException {
        DetalleConsulta detalleConsulta = repo.listar(id);
        if(detalleConsulta == null)
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
        DetalleConsultaDto dto = modelMapper.map(detalleConsulta, DetalleConsultaDto.class);     
        dto.getConsulta().setDetalleConsulta(null);
        return dto;
    }

    @Override
    public void guardar(DetalleConsulta detalleConsulta) throws ObjetcRequiredException{
        if(detalleConsulta.getConsulta() == null || detalleConsulta.getConsulta().getId() == null)
            throw new ObjetcRequiredException("Id Consula es requerido");
        repo.guardar(detalleConsulta);
    }

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        DetalleConsulta detalleConsulta = repo.listar(id);
        if(detalleConsulta == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");
        repo.eliminar(detalleConsulta);
    }

    @Override
    public void editar(DetalleConsultaDto dto) throws NotFoundModelException {
        DetalleConsulta detalleConsulta = repo.listar(dto.getId());
        if(detalleConsulta == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");

        if(dto.getDiagnostico() != null)
            detalleConsulta.setDiagnostico(dto.getDiagnostico());
        if(dto.getTratamiento() != null)
            detalleConsulta.setTratamiento(dto.getTratamiento());
        
        repo.editar(detalleConsulta);        
    }
}
