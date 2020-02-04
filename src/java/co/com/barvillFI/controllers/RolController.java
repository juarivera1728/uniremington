/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Rol;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@SessionScoped
public class RolController implements Serializable {
     @EJB
    private co.com.barvillFI.facadeLocal.RolFacadeLocal ejbFacade;
    private Rol rol; //creamos objeto
    List<Rol> listaRol = null;
    String mensaje = " ";
    private String accion;

   
    public List<Rol> getListaRol() {
        if (listaRol == null) {
            listaRol = ejbFacade.findAll();
        }
        listaRol = ejbFacade.findAll();
        return listaRol;
    }

    public void setListaRol(List<Rol> listaPersona) {
        this.listaRol = listaRol;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
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
        rol = new Rol();//genera una instancia al objeto
        listaRol = ejbFacade.findAll();
    }

    public void guardar() {
        try {          
            ejbFacade.create(rol);
            this.mensaje = "rol Insertada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        rol = new Rol();
        listaRol = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Rol t) {
        this.rol = t;
        this.setAccion("M");
    }

    public void editar() {
        try {
            
            ejbFacade.edit(rol);
            this.mensaje = "Promocion editada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        rol = new Rol();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        rol = new Rol();
    }

    public void eliminar(Rol ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Persona eliminada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaRol = ejbFacade.findAll();
        rol = new Rol();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    } 
}
