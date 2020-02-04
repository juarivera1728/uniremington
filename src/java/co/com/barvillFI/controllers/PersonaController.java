/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Persona;
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
public class PersonaController implements Serializable{
   @EJB
    private co.com.barvillFI.facadeLocal.PersonaFacadeLocal ejbFacade;
    private Persona persona; //creamos objeto
    List<Persona> listaPersona = null;
    String mensaje = " ";
    private String accion;

   
    public List<Persona> getListaPersona() {
        if (listaPersona == null) {
            listaPersona = ejbFacade.findAll();
        }
        listaPersona = ejbFacade.findAll();
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
        persona = new Persona();//genera una instancia al objeto

    }

    public void guardar() {
        try {          
          
            ejbFacade.create(persona);
            this.mensaje = "Promocion Insertada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        persona = new Persona();
        listaPersona = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Persona t) {
        this.persona = t;
        this.setAccion("M");
    }

    public void editar() {
        try {
            
            ejbFacade.edit(persona);
            this.mensaje = "Promocion editada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        persona = new Persona();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        persona = new Persona();
    }

    public void eliminar(Persona ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Persona eliminada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaPersona = ejbFacade.findAll();
        persona = new Persona();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    } 
}
