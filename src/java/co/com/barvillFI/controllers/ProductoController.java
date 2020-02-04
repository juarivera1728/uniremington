/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Bar;
import co.com.barvillFI.entities.Producto;
import co.com.barvillFI.entities.Usuario;
import co.com.barvillFI.facadeLocal.BarFacadeLocal;
import co.com.barvillFI.facadeLocal.ProductoFacadeLocal;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@SessionScoped
public class ProductoController implements Serializable {

    @EJB
    private co.com.barvillFI.facadeLocal.ProductoFacadeLocal ejbFacade;
    @EJB
    private co.com.barvillFI.facadeLocal.BarFacadeLocal ejbFacadeBar;
    private Producto producto; //creamos objeto
    List<Producto> listaProducto = null;
    private Bar bar;
    private int barId;
    String mensaje = " ";
    private String accion;

    public ProductoFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProductoFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public BarFacadeLocal getEjbFacadeBar() {
        return ejbFacadeBar;
    }

    public void setEjbFacadeBar(BarFacadeLocal ejbFacadeBar) {
        this.ejbFacadeBar = ejbFacadeBar;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public int getBarId() {
        return barId;
    }

    public void setBarId(int barId) {
        this.barId = barId;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getListaProducto() {
        if (listaProducto == null) {
            listaProducto = ejbFacade.findAll();
        }
        listaProducto = ejbFacade.findAll();
        return listaProducto;
    }

    public void setListaProducto(List<Producto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    @PostConstruct
    public void init() {
        producto = new Producto();//genera una instancia al objeto

    }

    public void guardar() {
        try {
            System.out.println("Nombre: " + producto.getProdNombre());
            System.out.println("Id: " + producto.getId());
            System.out.println("Cantidad: " + producto.getProdCantidad());
            System.out.println("Precio: " + producto.getProdPrecio());
            System.out.println("BarId: " + barId);
            producto.setBarId(new Bar(barId));
            ejbFacade.create(producto);
            this.mensaje = "Producto Insertado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        producto = new Producto();
        listaProducto = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Producto t,int IdB) {
        this.producto = t;
        this.barId=IdB;
        this.setAccion("M");
    }

    public void editar() {
        try {
            producto.setBarId(new Bar(barId));
            ejbFacade.edit(producto);
            this.mensaje = "Producto editado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        producto = new Producto();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        producto = new Producto();
    }

    public void eliminar(Producto ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Producto eliminado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaProducto = ejbFacade.findAll();
        producto = new Producto();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    }

    public List<Producto> findProductosxUsuario() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer Id = (Integer) session.getAttribute("idUsuario");
        Integer IdRole = (Integer) session.getAttribute("idRole");
        System.out.println("Usuario id:" + Id);
        System.out.println("Rol id:" + IdRole);
        // return getFacade().findProductosxUsuario(Id, IdRole);
        List<Bar> lista_bares = ejbFacadeBar.findAll();
        List<Bar> lista_bares2 = new LinkedList<Bar>();
        if (IdRole != 1) {
            for (Bar bar : lista_bares) {
                if (Id == bar.getUsuId().getId()) {

                    System.out.println("Bar: " + bar.getUsuId().getId() + " - Usuario:" + Id);
                    lista_bares2.add(bar);
                }
            }
        } else {
            lista_bares2 = ejbFacadeBar.findAll();
        }
        lista_bares = lista_bares2;

        System.out.println("Eliminó bares: " + lista_bares.size());

        List<Producto> listProducto = getEjbFacade().findAll();
        List<Producto> listProducto2 = new LinkedList<>();
        System.out.println("Encontró promociones");

        for (Bar bar : lista_bares) {
            for (Producto producto : listProducto) {
                System.out.println(bar.getId() + "=" + producto.getBarId().getId());
                if (producto.getBarId().equals(bar) && !listProducto2.contains(producto)) {
                    listProducto2.add(producto);
                    System.out.println("Promoción: " + producto.getProdNombre());
                }
            }
        }
        listProducto = listProducto2;
//n -> !(lista_bares.contains(n.getBarId())));
        System.out.println("LLego al final");

        return listProducto;

    }
}
