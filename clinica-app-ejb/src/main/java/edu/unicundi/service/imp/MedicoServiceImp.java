/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.dto.MedicoDto;
import edu.unicundi.entity.Medico;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.repo.IMedicoRepo;
import edu.unicundi.service.IMedicoService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.modelmapper.ModelMapper;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class MedicoServiceImp implements IMedicoService{

    @EJB
    private IMedicoRepo repo;
    
    @Override
    public List<Medico> listarTodos() {
        return repo.listarTodos();
    }

    @Override
    public MedicoDto listar(Integer id) throws NotFoundModelException {
        Medico medico = repo.listar(id);
        if (medico == null) {
            throw new NotFoundModelException("Objeto medico no encontrado");
        }
        
        ModelMapper modelMapper = new ModelMapper();
        MedicoDto medicoDto = modelMapper.map(medico, MedicoDto.class);
        medicoDto.getDireccion().setMedico(null);
        return medicoDto;        
        
    }

    @Override
    public void guardar(Medico medico) {
        medico.getDireccion().setMedico(medico);
        repo.guardar(medico);
    }

    @Override
    public void eliminar(Integer id) throws NotFoundModelException {
        Medico medico = repo.listar(id);
        if (medico == null) {
            throw new NotFoundModelException("Objeto medico no encontrado");
        }
        repo.eliminar(medico);
    }

    @Override
    public void editar(MedicoDto dto) throws NotFoundModelException {
        Medico medico = repo.listar(dto.getId());
        if (medico == null) {
            throw new NotFoundModelException("Objeto medico no encontrado");
        }

        if(dto.getNombreMedico() != null) 
            medico.setNombreMedico(dto.getNombreMedico());
        if(dto.getDireccion() != null && dto.getDireccion().getDetalle() != null)
            medico.getDireccion().setDetalle(dto.getDireccion().getDetalle());
        
        repo.editar(medico);
    }


}
