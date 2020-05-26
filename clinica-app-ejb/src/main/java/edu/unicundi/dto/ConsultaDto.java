/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ASUS-PC
 */
public class ConsultaDto implements Serializable{
    
    @NotNull(message = "Id es requerido")
    private Integer id;
        
    private MedicoDto medico;    
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
                
    private List<DetalleConsultaDto> detalleConsulta;
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MedicoDto getMedico() {
        return medico;
    }

    public void setMedico(MedicoDto medico) {
        this.medico = medico;
    }

    
 

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<DetalleConsultaDto> getDetalleConsulta() {
        return detalleConsulta;
    }

    public void setDetalleConsulta(List<DetalleConsultaDto> detalleConsulta) {
        this.detalleConsulta = detalleConsulta;
    }        
    
}

