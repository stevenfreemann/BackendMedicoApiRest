/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.Medico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IMedicoRepo {
    
    public List<Medico> listarTodos();
    
    public Medico listar(Integer id);   
    
    public void guardar(Medico medico);
    
    public void eliminar(Medico medico);
    
    public void editar(Medico medico);  
      
    
}
