/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Direccion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IDireccionRepo {
    
    public List<Direccion> listarTodos();
    
    public Direccion listar(Integer id);   
    
    public void guardar(Direccion direccion);
    
    public void eliminar(Direccion direccion);
    
    public void editar(DetalleConsulta detalleConsulta);    

    public void editar(Direccion direccion);
    
}
