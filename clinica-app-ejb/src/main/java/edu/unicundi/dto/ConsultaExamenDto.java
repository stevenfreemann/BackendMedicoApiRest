/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ASUS-PC
 */
public class ConsultaExamenDto implements Serializable{
    
    @NotNull(message = "IdExamen es requerido")
    private Integer idExamen;
    
    @NotNull(message = "IdConsulta es requerido")
    private Integer idConsulta;
    
    @NotNull(message = "InfoAdicional es requerido")
    private String infoAdicional;

    public Integer getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Integer idExamen) {
        this.idExamen = idExamen;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }        
    
}
