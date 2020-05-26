/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service;

import edu.unicundi.entity.login;
import javax.ejb.Local;

/**
 *
 * @author Steven
 */
@Local
public interface ILoginService {
    public String validacion(login l);
    
}
