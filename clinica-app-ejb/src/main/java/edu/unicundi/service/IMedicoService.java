/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.MedicoDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.Medico;
import edu.unicundi.exception.NotFoundModelException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IMedicoService {
    
    public List<Medico> listarTodos();
    
    public MedicoDto listar(Integer id) throws NotFoundModelException;   
    
    public void guardar(Medico medico);
    
    public void eliminar(Integer id) throws NotFoundModelException;
    
    public void editar(MedicoDto medico) throws NotFoundModelException;    
    
    
        
    
}
