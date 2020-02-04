/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id")
    , @NamedQuery(name = "Usuario.findByUsuCorreo", query = "SELECT u FROM Usuario u WHERE u.usuCorreo = :usuCorreo")
    , @NamedQuery(name = "Usuario.findByUsuContrasena", query = "SELECT u FROM Usuario u WHERE u.usuContrasena = :usuContrasena")
    , @NamedQuery(name = "Usuario.findByUsuCreacion", query = "SELECT u FROM Usuario u WHERE u.usuCreacion = :usuCreacion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "usu_contrasena")
    private String usuContrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usu_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date usuCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId")
    private List<Bar> barList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<HistorialSesion> historialSesionList;
    @JoinColumn(name = "est_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Estados estId;
    @JoinColumn(name = "rol_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Rol rolId;
    @JoinColumn(name = "per_id", referencedColumnName = "ID")
    @OneToOne(optional = false)
    private Persona perId;

    public Usuario() {
    }

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(Integer id, String usuCorreo, String usuContrasena, Date usuCreacion) {
        this.id = id;
        this.usuCorreo = usuCorreo;
        this.usuContrasena = usuContrasena;
        this.usuCreacion = usuCreacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuContrasena() {
        return usuContrasena;
    }

    public void setUsuContrasena(String usuContrasena) {
        this.usuContrasena = usuContrasena;
    }

    public Date getUsuCreacion() {
        return usuCreacion;
    }

    public void setUsuCreacion(Date usuCreacion) {
        this.usuCreacion = usuCreacion;
    }

    @XmlTransient
    public List<Bar> getBarList() {
        return barList;
    }

    public void setBarList(List<Bar> barList) {
        this.barList = barList;
    }

    @XmlTransient
    public List<HistorialSesion> getHistorialSesionList() {
        return historialSesionList;
    }

    public void setHistorialSesionList(List<HistorialSesion> historialSesionList) {
        this.historialSesionList = historialSesionList;
    }

    public Estados getEstId() {
        return estId;
    }

    public void setEstId(Estados estId) {
        this.estId = estId;
    }

    public Rol getRolId() {
        return rolId;
    }

    public void setRolId(Rol rolId) {
        this.rolId = rolId;
    }

    public Persona getPerId() {
        return perId;
    }

    public void setPerId(Persona perId) {
        this.perId = perId;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.Usuario[ id=" + id + " ]";
    }
    
}
