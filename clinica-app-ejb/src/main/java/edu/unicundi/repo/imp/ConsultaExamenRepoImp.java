/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.ConsultaExamen;
import edu.unicundi.repo.IConsultaExamenRepo;
import edu.unicundi.view.ExamenConsultaId;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class ConsultaExamenRepoImp implements IConsultaExamenRepo{
    
    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;    

    @Override
    public List<ConsultaExamen> listar() {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.listarTodos", ConsultaExamen.class);
        List<ConsultaExamen> listaConsultaExamen = query.getResultList();
        return listaConsultaExamen;
    }

    @Override
    public List<ConsultaExamen> listarPorConsulta(Integer idConsulta) {
        TypedQuery<ConsultaExamen> query = em.createNamedQuery("ConsultaExamen.listarPorIdConsulta", ConsultaExamen.class);
        query.setParameter("idConsulta", idConsulta);
        List<ConsultaExamen> listaConsultaExamen = query.getResultList();
        return listaConsultaExamen;        
    }

    @Override
    public void guardar(ConsultaExamen consultaExamen) {
        em.persist(consultaExamen);
    }

    @Override
    public List<ExamenConsultaId> listarExamenPorConulta(Integer idConsulta) {
        /**
            * Cosnulta nativa
            * TypedQuery<ExamenConsultaId> query = em.createNamedQuery("ConsultaExamen.listarExamenPorConsultaNativo", ExamenConsultaId.class);
        */
        
        //Consulta por medio de vista
        TypedQuery<ExamenConsultaId> query = em.createNamedQuery("ConsultaExamen.listarVistaExamenPorConsultaNativo", ExamenConsultaId.class);
        query.setParameter(1, idConsulta);
        List<ExamenConsultaId> listaConsultaExamen = query.getResultList();
        return listaConsultaExamen;                
    }

}
        
