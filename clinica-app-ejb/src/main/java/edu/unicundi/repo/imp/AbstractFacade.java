/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import javax.persistence.EntityManager;

/**
 *
 * @author ASUS-PC
 */
public abstract class AbstractFacade<T, V> {
    
    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }    
    
    protected abstract EntityManager getEntityManager();
    
    public T listar(V id) {
        //getEntityManager().getEntityManagerFactory().getCache().evictAll();
        return getEntityManager().find(entityClass, id);
    }

    public void guardar(T entity) {
         getEntityManager().persist(entity);                  
    }

    public void eliminar(T entity) {
        getEntityManager().remove(entity);
    }

    public void editar(T entity) {
        getEntityManager().merge(entity);
    }    
}
