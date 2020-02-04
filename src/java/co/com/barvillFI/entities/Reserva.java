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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findById", query = "SELECT r FROM Reserva r WHERE r.id = :id")
    , @NamedQuery(name = "Reserva.findByResNombre", query = "SELECT r FROM Reserva r WHERE r.resNombre = :resNombre")
    , @NamedQuery(name = "Reserva.findByResCorreo", query = "SELECT r FROM Reserva r WHERE r.resCorreo = :resCorreo")
    , @NamedQuery(name = "Reserva.findByResTelefono", query = "SELECT r FROM Reserva r WHERE r.resTelefono = :resTelefono")
    , @NamedQuery(name = "Reserva.findByResCreacion", query = "SELECT r FROM Reserva r WHERE r.resCreacion = :resCreacion")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "res_nombre")
    private String resNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "res_correo")
    private String resCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "res_telefono")
    private String resTelefono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "res_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date resCreacion;
    @JoinColumn(name = "bar_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bar barId;

    public Reserva() {
    }

    public Reserva(Integer id) {
        this.id = id;
    }

    public Reserva(Integer id, String resNombre, String resCorreo, String resTelefono, Date resCreacion) {
        this.id = id;
        this.resNombre = resNombre;
        this.resCorreo = resCorreo;
        this.resTelefono = resTelefono;
        this.resCreacion = resCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResNombre() {
        return resNombre;
    }

    public void setResNombre(String resNombre) {
        this.resNombre = resNombre;
    }

    public String getResCorreo() {
        return resCorreo;
    }

    public void setResCorreo(String resCorreo) {
        this.resCorreo = resCorreo;
    }

    public String getResTelefono() {
        return resTelefono;
    }

    public void setResTelefono(String resTelefono) {
        this.resTelefono = resTelefono;
    }

    public Date getResCreacion() {
        return resCreacion;
    }

    public void setResCreacion(Date resCreacion) {
        this.resCreacion = resCreacion;
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
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.Reserva[ id=" + id + " ]";
    }
    
}
