/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo.imp;

import edu.unicundi.entity.Medico;
import edu.unicundi.entity.Token;
import edu.unicundi.entity.login;
import edu.unicundi.repo.ILoginRepo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Steven
 */
@Stateless
public class LoginRepoImp implements ILoginRepo {

    @PersistenceContext(unitName = "edu.unicundi_clinica-app-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;    

    @Override
    public List<login> validar() {
        TypedQuery<login> query = em.createNamedQuery("login.listarTodos", login.class);
        List<login> listaUsuarios = query.getResultList();
        return listaUsuarios;
    }

    @Override
    public List<Token> listarTokens() {
        TypedQuery<Token> query = em.createNamedQuery("Token.listarTodos", Token.class);
        List<Token> listaTokens = query.getResultList();
        return listaTokens;
    }

    @Override
    public void guardarToken(String token) {
        Token t = new Token();
        t.setToken(token);
        em.persist(t);
    }
    
    
    
}
