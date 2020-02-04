/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "calificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calificacion.findAll", query = "SELECT c FROM Calificacion c")
    , @NamedQuery(name = "Calificacion.findById", query = "SELECT c FROM Calificacion c WHERE c.id = :id")
    , @NamedQuery(name = "Calificacion.findByCalPuntaje", query = "SELECT c FROM Calificacion c WHERE c.calPuntaje = :calPuntaje")
    , @NamedQuery(name = "Calificacion.findByCalCreacion", query = "SELECT c FROM Calificacion c WHERE c.calCreacion = :calCreacion")})
public class Calificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cal_puntaje")
    private int calPuntaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cal_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date calCreacion;
    @JoinColumn(name = "bar_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bar barId;

    public Calificacion() {
    }

    public Calificacion(Integer id) {
        this.id = id;
    }

    public Calificacion(Integer id, int calPuntaje, Date calCreacion) {
        this.id = id;
        this.calPuntaje = calPuntaje;
        this.calCreacion = calCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCalPuntaje() {
        return calPuntaje;
    }

    public void setCalPuntaje(int calPuntaje) {
        this.calPuntaje = calPuntaje;
    }

    public Date getCalCreacion() {
        return calCreacion;
    }

    public void setCalCreacion(Date calCreacion) {
        this.calCreacion = calCreacion;
    }

    public Bar getBarId() {
        return barId;
    }

    public void setBarId(Bar barId) {
        this.barId = barId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calificacion)) {
            return false;
        }
        Calificacion other = (Calificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.Calificacion[ id=" + id + " ]";
    }
    
}
