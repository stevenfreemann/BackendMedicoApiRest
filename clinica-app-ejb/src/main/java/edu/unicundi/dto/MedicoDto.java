/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.dto;

import edu.unicundi.entity.Direccion;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ASUS-PC
 */
public class MedicoDto implements Serializable{

    @NotNull(message = "Id es requerido")
    private Integer id;
    
    private String nombreMedico;
    
    private DireccionDto direccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public DireccionDto getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDto direccion) {
        this.direccion = direccion;
    }            
}
