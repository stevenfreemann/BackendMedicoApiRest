/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.unicundi.entity;

import edu.unicundi.view.ConsultaView;
import edu.unicundi.view.ExamenConsultaId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ASUS-PC
 */
@Entity
@Table
@NamedQueries({
    @NamedQuery(name = "Consulta.listarTodos", query = "SELECT c FROM Consulta c"),
    @NamedQuery(name = "Consulta.listarUltimoIdPorMedico", query = "SELECT MAX(c.id) FROM Consulta c WHERE c.medico.nombreMedico = :nombreMedico"),
    @NamedQuery(name = "ConsultaExamen.listarTodos2", query = "SELECT c FROM Consulta c JOIN DetalleConsulta dc ON c.id = dc.consulta.id JOIN ConsultaExamen ce ON ce.consulta.id = c.id JOIN Examen e ON e.idExamen = ce.examen.idExamen")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "Consulta.listarOptimoPaginacion", query = "SELECT id, fecha, nombre_medico, cantidad\n" +
"	FROM public.consulta_view limit ? offset ?", resultClass = ConsultaView.class)
})
public class Consulta implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @NotNull(message = "Objeto medico es requerido")
    @ManyToOne
    private Medico medico;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
                
    @OneToMany(mappedBy = "consulta", cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<DetalleConsulta> detalleConsulta;
            
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
    
    public List<DetalleConsulta> getDetalleConsulta() {
        return detalleConsulta;
    }

    public void setDetalleConsulta(List<DetalleConsulta> detalleConsulta) {
        this.detalleConsulta = detalleConsulta;
    }        

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
        
}
