/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Consulta;
import edu.unicundi.entity.DetalleConsulta;
import edu.unicundi.entity.Direccion;
import edu.unicundi.repo.IDetalleConsultaRepo;
import edu.unicundi.repo.IDireccionRepo;
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
public class DireccionRepoImp extends AbstractFacade<Direccion, Integer> implements IDireccionRepo{
    
    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;    

    public DireccionRepoImp() {
        super(Direccion.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        

    @Override
    public List<Direccion> listarTodos() {
        //this.em.getEntityManagerFactory().getCache().evictAll();
        TypedQuery<Direccion> query = em.createNamedQuery("Direccion.listarTodos", Direccion.class);
        List<Direccion> listaDireccion = query.getResultList();
        return listaDireccion;
    }

   /* @Override
    public DetalleConsulta listar(int id) {
        return em.find(DetalleConsulta.class, id);
    }

    @Override
    public void guardar(DetalleConsulta detalleConsulta) {
         em.persist(detalleConsulta);
    }

    @Override
    public void eliminar(DetalleConsulta detalleConsulta) {
        em.remove(detalleConsulta);
    }

    @Override
    public void editar(DetalleConsulta detalleConsulta) {
        em.merge(detalleConsulta);
    }*/

    @Override
    public void editar(DetalleConsulta detalleConsulta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    


}
