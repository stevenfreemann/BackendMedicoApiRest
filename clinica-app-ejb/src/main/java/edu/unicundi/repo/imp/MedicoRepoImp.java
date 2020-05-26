/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Medico;
import edu.unicundi.repo.IMedicoRepo;
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
public class MedicoRepoImp extends AbstractFacade<Medico, Integer> implements IMedicoRepo{

    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;    

    public MedicoRepoImp() {        
        super(Medico.class);        
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<Medico> listarTodos() {        
        TypedQuery<Medico> query = em.createNamedQuery("Medico.listarTodos", Medico.class);
        List<Medico> listaMedico = query.getResultList();
        return listaMedico;
    }

   
   
}
