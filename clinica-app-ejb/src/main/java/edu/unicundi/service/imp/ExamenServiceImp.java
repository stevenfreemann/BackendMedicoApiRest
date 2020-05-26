/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;


import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import edu.unicundi.repo.IExamenRepo;
import edu.unicundi.service.IExamenService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class ExamenServiceImp implements IExamenService{

    @EJB
    IExamenRepo repo;   

    @Override
    public List<Examen> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public ExamenDto listar(int id) throws NotFoundModelException {
        Examen examen = repo.listar(id);
        if(examen == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");        
        ModelMapper modelMapper = new ModelMapper();
        ExamenDto dto = modelMapper.map(examen, ExamenDto.class);  
        return dto;
    }

    @Override
    public void guardar(Examen examen) throws ObjetcRequiredException{
        //if(examen.getConsulta() == null || detalleConsulta.getConsulta().getId() == null)
         //   throw new ObjetcRequiredException("Id Consula es requerido");
        repo.guardar(examen);
    }

    @Override
    public void eliminar(int id) throws NotFoundModelException {
        Examen examen = repo.listar(id);
        if(examen == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");
        repo.eliminar(examen);
    }

    @Override
    public void editar(ExamenDto dto) throws NotFoundModelException {
        Examen examen = repo.listar(dto.getIdExamen());
        if(examen == null)
            throw new NotFoundModelException("Objeto detalleConsulta no encontrado");

        if(dto.getNombre() != null)
            examen.setNombre(dto.getNombre());
        if(dto.getDescripcion()!= null)
            examen.setDescripcion(dto.getDescripcion());
        
        repo.editar(examen);        
    }
}
