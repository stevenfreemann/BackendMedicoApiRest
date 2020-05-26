/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.dto;

import java.io.Serializable;


/**
 *
 * @author ASUS-PC
 */
public class ConsultaExamenAuxiliarDto implements Serializable{
    
    private ExamenDto examen;

    private ConsultaDto consulta;

    private String infoAdicional;
    

    public ExamenDto getExamen() {
        return examen;
    }

    public void setExamen(ExamenDto examen) {
        this.examen = examen;
    }

    public ConsultaDto getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaDto consulta) {
        this.consulta = consulta;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }
    
    
    
}
