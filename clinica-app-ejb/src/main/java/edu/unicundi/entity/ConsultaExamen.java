/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.entity;

import edu.unicundi.view.ExamenConsultaId;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.MapsId;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 *
 * @author ASUS-PC
 */
@Entity
@Table(name = "consulta_examen")
@NamedQueries({
    @NamedQuery(name = "ConsultaExamen.listarTodos", query = "SELECT c FROM ConsultaExamen c"),
    @NamedQuery(name = "ConsultaExamen.listarPorIdConsulta", query = "SELECT c FROM ConsultaExamen c WHERE c.consulta.id = :idConsulta")    
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "ConsultaExamen.listarExamenPorConsultaNativo", query = "SELECT ce.info_adicional, e.idexamen, e.descripcion, e.nombre\n" +
"	FROM public.consulta_examen ce	\n" +
"	JOIN public.examen e ON ce.examen_idexamen = e.idexamen\n" +
"	WHERE ce.consulta_id = ?", resultClass = ExamenConsultaId.class),
    @NamedNativeQuery(name = "ConsultaExamen.listarVistaExamenPorConsultaNativo", query = "SELECT info_adicional, idexamen, descripcion, nombre\n" +
"	FROM public.examen_detalles_consulta_view \n" +
"	WHERE consulta_id = ?", resultClass = ExamenConsultaId.class)
})

public class ConsultaExamen implements Serializable{
    
    @EmbeddedId
    private ConsultaExamenPK consultaExamenId;
    
    @ManyToOne
    @MapsId("idConsulta")
    private Consulta consulta;
    
    @ManyToOne
    @MapsId("idExamen")
    private Examen examen;
    
    @Column(name = "info_adicional",  nullable = false, length = 100)
    private String infoAdicional;

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getInfoAdicional() {
        return infoAdicional;
    }

    public void setInfoAdicional(String infoAdicional) {
        this.infoAdicional = infoAdicional;
    }        
    
}
