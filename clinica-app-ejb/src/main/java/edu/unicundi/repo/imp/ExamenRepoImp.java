/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Examen;
import edu.unicundi.repo.IDetalleConsultaRepo;
import edu.unicundi.repo.IExamenRepo;
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
public class ExamenRepoImp extends AbstractFacade<Examen, Integer> implements IExamenRepo{
    
    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;    

    public ExamenRepoImp() {
        super(Examen.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        

    @Override
    public List<Examen> listarTodos() {
        //this.em.getEntityManagerFactory().getCache().evictAll();
        TypedQuery<Examen> query = em.createNamedQuery("Examen.listarTodos", Examen.class);
        List<Examen> listaExamen = query.getResultList();
        return listaExamen;
    }

   


}
