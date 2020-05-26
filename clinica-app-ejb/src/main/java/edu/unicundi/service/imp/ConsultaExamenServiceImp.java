/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.ConsultaExamen;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.repo.IConsultaExamenRepo;
import edu.unicundi.repo.IConsultaRepo;
import edu.unicundi.service.IConsultaExamenService;
import edu.unicundi.view.ExamenConsultaId;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class ConsultaExamenServiceImp implements IConsultaExamenService{

    @EJB
    private IConsultaExamenRepo repo;
    
    @EJB
    private IConsultaRepo repoConsulta;
    
    @Override
    public List<ConsultaExamenListaDto> listar() {
        List<ConsultaExamen> listaConsultaExamen = repo.listar();
        List<ConsultaExamenListaDto> listaDto = new ArrayList<>();
        for (ConsultaExamen le : listaConsultaExamen) {
            ModelMapper modelMapper = new ModelMapper();
            ConsultaExamenListaDto dto =  modelMapper.map(le, ConsultaExamenListaDto.class);             
            //dto.getConsulta().setDetalleConsulta(null);
            for (DetalleConsultaDto detalleConsulta : dto.getConsulta().getDetalleConsulta()) {
                detalleConsulta.setConsulta(null);
            }            
            
            if(!validarExistenciaConsulta(listaDto, dto)) {
                listaDto.add(dto);
            }
            agregarExamen(le, listaDto);
            
        }
                
        return listaDto;
    }

    @Override
    public List<ConsultaExamen> listarPorConsulta(Integer idConsulta) {
        return repo.listarPorConsulta(idConsulta);
    }
    
    
    private void agregarExamen(ConsultaExamen le, List<ConsultaExamenListaDto> listaDto) {
        for (ConsultaExamenListaDto lista : listaDto) {
            if(lista.getConsulta().getId() == le.getConsulta().getId()) {
                ExamenDto examen = new ExamenDto();
                examen.setIdExamen(le.getExamen().getIdExamen());
                examen.setNombre(le.getExamen().getNombre());
                examen.setDescripcion(le.getExamen().getDescripcion());
                lista.getListaExamen().add(examen);
            }
        }
    }
    
    private boolean validarExistenciaConsulta(List<ConsultaExamenListaDto> listaDto, ConsultaExamenListaDto dto) {
         for (ConsultaExamenListaDto lista : listaDto) {
            if(lista.getConsulta().getId() == dto.getConsulta().getId()) {
                return true;                
            }
        }
        return false;
    }

    @Override
    public void guardar(ConsultaExamenDto dto) {
        
        ConsultaExamen consultaExamen = new ConsultaExamen();
        Consulta consulta = new Consulta();
        Examen examen = new Examen();
        
        consulta.setId(dto.getIdConsulta());
        examen.setIdExamen(dto.getIdExamen());
        consultaExamen.setInfoAdicional(dto.getInfoAdicional());
        
        consultaExamen.setConsulta(consulta);
        consultaExamen.setExamen(examen);                
        
        repo.guardar(consultaExamen);
    }

    @Override
    public ConsultaExamenListaDto listarPorConsultaOpc2(Integer idConsulta) throws NotFoundModelException{
        ConsultaExamenListaDto consultaExamenDto = new ConsultaExamenListaDto();
        
        Consulta consulta = repoConsulta.listar(idConsulta);
        if (consulta == null) {
            throw new NotFoundModelException("Objeto consulta no encontrado") ;
        }
        
        ModelMapper modelMapper = new ModelMapper();
        ConsultaDto consultDto =  modelMapper.map(consulta, ConsultaDto.class);      
        for(DetalleConsultaDto detalle: consultDto.getDetalleConsulta()) {
            detalle.setConsulta(null);
        }
        
        List<ExamenConsultaId> listaExamenConsuta = repo.listarExamenPorConulta(idConsulta);
        List<ExamenDto> listaExamen = new ArrayList<>();
        
        for (ExamenConsultaId ex : listaExamenConsuta) {
            modelMapper = new ModelMapper();
            ExamenDto examenDto =  modelMapper.map(ex, ExamenDto.class);    
            listaExamen.add(examenDto);
        }
        
        consultaExamenDto.setConsulta(consultDto);
        consultaExamenDto.setListaExamen(listaExamen);
        return consultaExamenDto;
    }
  
    
}
