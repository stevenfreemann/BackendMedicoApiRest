/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Consulta;
import edu.unicundi.repo.IConsultaRepo;
import edu.unicundi.view.ConsultaView;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ASUS-PC
 */
@Stateless
public class ConsultaRepoImp extends AbstractFacade<Consulta, Integer> implements IConsultaRepo {

    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    public ConsultaRepoImp() {
        super(Consulta.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    

    @Override
    public List<Consulta> listarTodos() {
        //this.em.getEntityManagerFactory().getCache().evictAll();
        TypedQuery<Consulta> query = em.createNamedQuery("Consulta.listarTodos", Consulta.class);
        List<Consulta> listaConsulta = query.getResultList();
        //Para que no genere bucle infinito, la otra opción es utilizar @JsonIgnore
        /*for (Consulta cs : listaConsulta) {
         Consulta consultaAuliar = new Consulta();
         consultaAuliar.setId(cs.getId());
         for (DetalleConsulta dc : cs.getDetalleConsulta()) {
         dc.setConsulta(consultaAuliar);
         }            
         }*/

        //Para que traiga el objeto sin detalleConsulta
        /*for (Consulta con : listaConsulta) {
         con.setDetalleConsulta(null);
         }*/
        return listaConsulta;
    }
    
    @Override
    public List<ConsultaView> listarOptimoPaginado(Integer limit, Integer offSet) {
        TypedQuery<ConsultaView> query = em.createNamedQuery("Consulta.listarOptimoPaginacion", ConsultaView.class);
        query.setParameter(1, limit);
        query.setParameter(2, offSet);
        List<ConsultaView> listaConsulta = query.getResultList();
        return listaConsulta;                
        
    }
    

   /* @Override
    public Consulta listar(int id) {
        return em.find(Consulta.class, id);
    }

    @Override
    public void guardar(Consulta consulta) {
        em.persist(consulta);
    }

    @Override
    public void eliminar(Consulta consulta) {
        em.remove(consulta);
    }

    @Override
    public void editar(Consulta consulta) {
        em.merge(consulta);
    }*/

    @Override
    public Integer obtenerUltimoIdPorMedico(String nombreMedico) {
        Query query = em.createNamedQuery("Consulta.listarUltimoIdPorMedico", Integer.class);
        query.setParameter("nombreMedico", nombreMedico);
        return (Integer) query.getSingleResult();
    }
}
