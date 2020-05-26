/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.repo;

import edu.unicundi.entity.Token;
import edu.unicundi.entity.login;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface ILoginRepo {
    public List<login> validar();
    
    public List<Token> listarTokens();
    
    public void guardarToken(String token);
}
