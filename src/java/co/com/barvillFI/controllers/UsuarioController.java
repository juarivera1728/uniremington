/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Bar;
import co.com.barvillFI.entities.Estados;
import co.com.barvillFI.entities.Persona;
import co.com.barvillFI.entities.Rol;
import co.com.barvillFI.entities.Usuario;
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
public class UsuarioController implements Serializable {
     @EJB
    private co.com.barvillFI.facadeLocal.UsuarioFacadeLocal ejbFacade;
     
    private Usuario usuario; //creamos objeto
    List<Usuario> listausuario = null;
    private Persona persona;
    private int idPersona;
        private Rol rol;
    private int idRol;
      private Estados estados;
    private int idEstados;
    String mensaje = " ";
    private String accion;

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public Estados getEstados() {
        return estados;
    }

    public void setEstados(Estados estados) {
        this.estados = estados;
    }

    public int getIdEstados() {
        return idEstados;
    }

    public void setIdEstados(int idEstados) {
        this.idEstados = idEstados;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListausuario() {
      if (listausuario == null) {
            listausuario = ejbFacade.findAll();
        }
        listausuario = ejbFacade.findAll();
        return listausuario;
    }

    public void setListausuario(List<Usuario> listausuario) {
        this.listausuario = listausuario;
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
        usuario = new Usuario();//genera una instancia al objeto

    }

    public void guardar() {
        try {
            System.out.println("persona:"+usuario.getUsuCorreo());
             System.out.println("persona:"+usuario.getUsuContrasena());
              System.out.println("persona:"+idPersona);
               System.out.println("persona:"+idEstados);
                System.out.println("persona:"+idRol);
                 System.out.println("persona:"+usuario.getUsuCreacion());
             usuario.setPerId(new Persona (idPersona));
              usuario.setEstId(new Estados (idEstados));
             usuario.setRolId(new Rol (idRol));
            ejbFacade.create(usuario);
            this.mensaje = "Persona Insertado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        usuario = new Usuario();
        listausuario = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Usuario t,int Idp,int IdE,int IdR) {
        this.usuario = t;
        this.idPersona=Idp;
        this.idRol=IdR;
        this.idEstados=IdE;
        this.setAccion("M");
    }

    public void editar() {
        try {
                         usuario.setPerId(new Persona (idPersona));
              usuario.setEstId(new Estados (idEstados));
             usuario.setRolId(new Rol (idRol));
           
            ejbFacade.edit(usuario);
            this.mensaje = "Usuario editado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        usuario = new Usuario();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        usuario = new Usuario();
    }

    public void eliminar(Usuario ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Usuario eliminado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listausuario = ejbFacade.findAll();
        usuario = new Usuario();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    }
    
    
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }
}
