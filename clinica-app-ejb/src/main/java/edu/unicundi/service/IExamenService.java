/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ExamenDto;
import edu.unicundi.entity.Examen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IExamenService {
    
    public List<Examen> listarTodos();
    
    public ExamenDto listar(int idExamen) throws NotFoundModelException;   
    
    public void guardar(Examen examen) throws ObjetcRequiredException;
    
    public void eliminar(int idExamen) throws NotFoundModelException;
    
    public void editar(ExamenDto examen) throws NotFoundModelException;     
    
}
