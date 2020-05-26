/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.DetalleConsultaDto;
import edu.unicundi.dto.DireccionDto;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Direccion;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.exception.ObjetcRequiredException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IDireccionService {
    
    public List<Direccion> listarTodos();
    
    public DireccionDto listar(int id) throws NotFoundModelException;   
    
    public void guardar(Direccion direccion) throws ObjetcRequiredException;
    
    public void eliminar(int id) throws NotFoundModelException;
    
    public void editar(DireccionDto direccion) throws NotFoundModelException;     
    
}
