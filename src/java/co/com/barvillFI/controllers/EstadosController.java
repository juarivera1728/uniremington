/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Estados;
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
public class EstadosController implements Serializable {
      @EJB
    private co.com.barvillFI.facadeLocal.EstadosFacadeLocal ejbFacade;
    private Estados estados; //creamos objeto
    List<Estados> listaEstados = null;
    String mensaje = " ";
    private String accion;

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public List<Estados> getListaEstados() {
           if (listaEstados == null) {
            listaEstados = ejbFacade.findAll();
        }
        listaEstados = ejbFacade.findAll();
        return listaEstados;
    }

    public void setListaEstados(List<Estados> listaEstados) {
        this.listaEstados = listaEstados;
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
        estados = new Estados();//genera una instancia al objeto

    }

    public void guardar() {
        try {          
          
            ejbFacade.create(estados);
            this.mensaje = "estados Insertada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        estados = new Estados();
        listaEstados = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Estados t) {
        this.estados = t;
        this.setAccion("M");
    }

    public void editar() {
        try {
            
            ejbFacade.edit(estados);
            this.mensaje = "Estados editada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        estados = new Estados();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        estados = new Estados();
    }

    public void eliminar(Estados ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Persona eliminada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaEstados = ejbFacade.findAll();
        estados = new Estados();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    } 
}
