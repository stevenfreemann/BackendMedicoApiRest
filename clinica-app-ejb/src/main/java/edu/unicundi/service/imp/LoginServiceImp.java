/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.service.imp;

import edu.unicundi.entity.login;
import edu.unicundi.repo.ILoginRepo;
import edu.unicundi.service.ILoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;



/**
 *
 * @author Steven
 */
@Stateless
public class LoginServiceImp implements ILoginService{
    @EJB
    private ILoginRepo rep;
            
    @Override
    public String validacion(login l) {
      String jwt="";
      List<login> users = rep.validar();
      for(login usuario: users){
          if(l.getUsuario().equals(usuario.getUsuario()) && l.getPassword().equals(usuario.getPassword())){
              String key = "firma"; 
              Long horaActual = System.currentTimeMillis();
               jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, key)
                      .setSubject("prueba token")
                      .setIssuedAt(new Date(horaActual))
                      .setExpiration(new Date(horaActual+900000))
                      .claim("Nombre Usuario", usuario.getUsuario())
                      .compact();
               rep.guardarToken(jwt);
          }
      }
      return jwt;
    }
    
    
}
