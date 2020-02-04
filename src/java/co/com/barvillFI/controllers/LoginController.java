/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Rol;
import co.com.barvillFI.entities.Usuario;
import co.com.barvillFI.facade.UsuarioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Camilo
 */
@Named(value = "LoginController")
@SessionScoped
public class LoginController implements Serializable {

    private String correo;
    private String password;
    @EJB
    private co.com.barvillFI.facadeLocal.UsuarioFacadeLocal usufacade;

    private Usuario usuarioAuntenticado;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuarioAuntenticado() {
        return usuarioAuntenticado;
    }

    public void setUsuarioAuntenticado(Usuario usuarioAuntenticado) {
        this.usuarioAuntenticado = usuarioAuntenticado;
    }

    public LoginController() {

    }

    @PostConstruct
    public void init() {

    }

    public String autentica() {

        usuarioAuntenticado = usufacade.encontrarUsuario(correo);
        if (usuarioAuntenticado != null) {
            if (usuarioAuntenticado.getUsuContrasena().equals(password)) {
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                session.setAttribute("idUsuario", usuarioAuntenticado.getId());
                System.out.println("entro");
                
                Rol role = usuarioAuntenticado.getRolId();  
                
                System.out.println("el rol es:"+role.getId());
                session.setAttribute("idRole", role.getId());
                return "template";

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El password no corresponde", "El password no"
                    + "corresponde"));
            return null;

        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "El usuario no existe", "El usuario no"
                + "existe"));
        return null;
    }
    public void cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    
    }
}
