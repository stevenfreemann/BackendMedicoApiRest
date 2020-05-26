/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ConsultaExamenDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.entity.ConsultaExamen;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.view.ExamenConsultaId;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IConsultaExamenService {
    
    public List<ConsultaExamenListaDto> listar();
    
    public List<ConsultaExamen> listarPorConsulta(Integer idConsulta);
    
    public ConsultaExamenListaDto listarPorConsultaOpc2(Integer idConsulta) throws NotFoundModelException;
    
    public void guardar(ConsultaExamenDto dto);
    
   
    
}
