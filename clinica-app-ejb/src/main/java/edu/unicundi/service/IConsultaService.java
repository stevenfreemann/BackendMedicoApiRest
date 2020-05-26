/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.dto.ConsultaDto;
import edu.unicundi.dto.ConsultaExamenListaDto;
import edu.unicundi.entity.Consulta;
import edu.unicundi.exception.NotFoundModelException;
import edu.unicundi.view.ConsultaView;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ASUS-PC
 */
@Local
public interface IConsultaService {
    
    public List<Consulta> listarTodos();
    
    public Consulta listar(int id) throws NotFoundModelException;   
    
    public void guardar(Consulta consulta);
    
    public void guardarTransaccion(ConsultaExamenListaDto dto)throws NotFoundModelException;
    public void guardarTransaccion2(ConsultaExamenListaDto dto)throws NotFoundModelException;
    public void guardarTransaccion3(ConsultaExamenListaDto dto)throws NotFoundModelException;
    public void guardarTransaccion4(ConsultaExamenListaDto dto)throws NotFoundModelException;
    
    public void eliminar(int id) throws NotFoundModelException;
    
    public void editar(ConsultaDto consulta) throws NotFoundModelException;    
    
    public List<ConsultaView> listarOptimoPaginado(Integer limit, Integer offSet);    
   
}

