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
@Table(name = "historial_sesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistorialSesion.findAll", query = "SELECT h FROM HistorialSesion h")
    , @NamedQuery(name = "HistorialSesion.findById", query = "SELECT h FROM HistorialSesion h WHERE h.id = :id")
    , @NamedQuery(name = "HistorialSesion.findByHistId", query = "SELECT h FROM HistorialSesion h WHERE h.histId = :histId")})
public class HistorialSesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hist_id")
    @Temporal(TemporalType.TIMESTAMP)
    private Date histId;
    @JoinColumn(name = "usuario_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuarioId;

    public HistorialSesion() {
    }

    public HistorialSesion(Integer id) {
        this.id = id;
    }

    public HistorialSesion(Integer id, Date histId) {
        this.id = id;
        this.histId = histId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getHistId() {
        return histId;
    }

    public void setHistId(Date histId) {
        this.histId = histId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
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
        if (!(object instanceof HistorialSesion)) {
            return false;
        }
        HistorialSesion other = (HistorialSesion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.HistorialSesion[ id=" + id + " ]";
    }
    
}
