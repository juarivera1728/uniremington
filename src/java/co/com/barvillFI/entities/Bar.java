/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "bar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bar.findAll", query = "SELECT b FROM Bar b")
    , @NamedQuery(name = "Bar.findById", query = "SELECT b FROM Bar b WHERE b.id = :id")
    , @NamedQuery(name = "Bar.findByBarNombre", query = "SELECT b FROM Bar b WHERE b.barNombre = :barNombre")
    , @NamedQuery(name = "Bar.findByBarDireccion", query = "SELECT b FROM Bar b WHERE b.barDireccion = :barDireccion")
    , @NamedQuery(name = "Bar.findByBarTelefono", query = "SELECT b FROM Bar b WHERE b.barTelefono = :barTelefono")})
public class Bar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "bar_nombre")
    private String barNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "bar_direccion")
    private String barDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "bar_telefono")
    private String barTelefono;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "bar_descripcion")
    private String barDescripcion;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "bar_imagen")
    private String barImagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barId")
    private List<Promocion> promocionList;
    @JoinColumn(name = "usu_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Usuario usuId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barId")
    private List<Calificacion> calificacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barId")
    private List<Producto> productoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barId")
    private List<Reserva> reservaList;

    public Bar() {
    }

    public Bar(Integer id) {
        this.id = id;
    }

    public Bar(Integer id, String barNombre, String barDireccion, String barTelefono, String barDescripcion, String barImagen) {
        this.id = id;
        this.barNombre = barNombre;
        this.barDireccion = barDireccion;
        this.barTelefono = barTelefono;
        this.barDescripcion = barDescripcion;
        this.barImagen = barImagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBarNombre() {
        return barNombre;
    }

    public void setBarNombre(String barNombre) {
        this.barNombre = barNombre;
    }

    public String getBarDireccion() {
        return barDireccion;
    }

    public void setBarDireccion(String barDireccion) {
        this.barDireccion = barDireccion;
    }

    public String getBarTelefono() {
        return barTelefono;
    }

    public void setBarTelefono(String barTelefono) {
        this.barTelefono = barTelefono;
    }

    public String getBarDescripcion() {
        return barDescripcion;
    }

    public void setBarDescripcion(String barDescripcion) {
        this.barDescripcion = barDescripcion;
    }

    public String getBarImagen() {
        return barImagen;
    }

    public void setBarImagen(String barImagen) {
        this.barImagen = barImagen;
    }

    @XmlTransient
    public List<Promocion> getPromocionList() {
        return promocionList;
    }

    public void setPromocionList(List<Promocion> promocionList) {
        this.promocionList = promocionList;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    @XmlTransient
    public List<Calificacion> getCalificacionList() {
        return calificacionList;
    }

    public void setCalificacionList(List<Calificacion> calificacionList) {
        this.calificacionList = calificacionList;
    }

    @XmlTransient
    public List<Producto> getProductoList() {
        return productoList;
    }

    public void setProductoList(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
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
        if (!(object instanceof Bar)) {
            return false;
        }
        Bar other = (Bar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.Bar[ id=" + id + " ]";
    }
    
}
