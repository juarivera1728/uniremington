/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan Camilo
 */
@Entity
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")
    , @NamedQuery(name = "Producto.findById", query = "SELECT p FROM Producto p WHERE p.id = :id")
    , @NamedQuery(name = "Producto.findByProdNombre", query = "SELECT p FROM Producto p WHERE p.prodNombre = :prodNombre")
    , @NamedQuery(name = "Producto.findByProdPrecio", query = "SELECT p FROM Producto p WHERE p.prodPrecio = :prodPrecio")
    , @NamedQuery(name = "Producto.findByProdCantidad", query = "SELECT p FROM Producto p WHERE p.prodCantidad = :prodCantidad")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "prod_nombre")
    private String prodNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_precio")
    private float prodPrecio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prod_cantidad")
    private int prodCantidad;
    @JoinColumn(name = "bar_id", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Bar barId;

    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String prodNombre, float prodPrecio, int prodCantidad) {
        this.id = id;
        this.prodNombre = prodNombre;
        this.prodPrecio = prodPrecio;
        this.prodCantidad = prodCantidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProdNombre() {
        return prodNombre;
    }

    public void setProdNombre(String prodNombre) {
        this.prodNombre = prodNombre;
    }

    public float getProdPrecio() {
        return prodPrecio;
    }

    public void setProdPrecio(float prodPrecio) {
        this.prodPrecio = prodPrecio;
    }

    public int getProdCantidad() {
        return prodCantidad;
    }

    public void setProdCantidad(int prodCantidad) {
        this.prodCantidad = prodCantidad;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.barvillFI.entities.Producto[ id=" + id + " ]";
    }
    
}
