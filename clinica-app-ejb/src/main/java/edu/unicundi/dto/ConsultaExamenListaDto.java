/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS-PC
 */
public class ConsultaExamenListaDto implements Serializable{
    
    ConsultaDto consulta;
                
    List<ExamenDto> listaExamen;
    
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }

    public List<ExamenDto> getListaExamen() {
        if(listaExamen == null) {
            listaExamen = new ArrayList<>();
        }
        return listaExamen;
    }

    public void setListaExamen(List<ExamenDto> listaExamen) {
        this.listaExamen = listaExamen;
    }        
    
}
