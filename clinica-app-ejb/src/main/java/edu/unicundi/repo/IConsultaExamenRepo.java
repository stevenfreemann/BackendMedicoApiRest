/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.ConsultaExamen;
import edu.unicundi.view.ExamenConsultaId;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IConsultaExamenRepo {
    
    public List<ConsultaExamen> listar();
    
    public List<ConsultaExamen> listarPorConsulta(Integer idConsulta);
    
    public void guardar(ConsultaExamen consultaExamen);
    
    public List<ExamenConsultaId> listarExamenPorConulta(Integer idConsulta);
        
    
}
