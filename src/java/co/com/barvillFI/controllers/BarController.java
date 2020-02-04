/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Bar;
import co.com.barvillFI.entities.Usuario;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
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
public class BarController implements Serializable{
    @EJB
    private co.com.barvillFI.facadeLocal.BarFacadeLocal ejbFacade;
     @EJB
    private co.com.barvillFI.facadeLocal.PromocionFacadeLocal ejbFacadePromo;
    
    
    private Bar bar; //creamos objeto
    List<Bar> listabar = null;
    private Usuario usuario;
    private int usarioId;
    String mensaje = " ";
    private String accion;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getUsarioId() {
        return usarioId;
    }

    public void setUsarioId(int usarioId) {
        this.usarioId = usarioId;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public List<Bar> getListabar() {
       if (listabar == null) {
            listabar = ejbFacade.findAll();
        }
        listabar = ejbFacade.findAll();
        return listabar;
    }

    public void setListabar(List<Bar> listabar) {
        this.listabar = listabar;
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
        bar = new Bar();//genera una instancia al objeto

    }

    public void guardar() {
        try {
            bar.setUsuId(new Usuario (usarioId));
            ejbFacade.create(bar);
            this.mensaje = "br Insertado Correctamente";
              usarioId=0;
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        bar = new Bar();
        listabar = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Bar t,int idU) {
        this.bar = t;
        this.usarioId=idU;
        this.setAccion("M");
    }

    public void editar() {
        try {
            bar.setUsuId(new Usuario (usarioId));
            ejbFacade.edit(bar);
            this.mensaje = "Bar editado Correctamente";
            usarioId=0;
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        bar = new Bar();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        bar = new Bar();
    }

    public void eliminar(Bar ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Bar eliminada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listabar = ejbFacade.findAll();
        bar = new Bar();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    }
    
    public String print(String property) {
        
        return "<%= " + property + " %>";
    }

    public String getJson() {
        ArrayList<Object> list = new ArrayList<Object>();
        String a = "";
        try {
            List<Bar> listaBar = getListabar();
            for (int i = 0; i < listaBar.size(); i++) {

                Bar b = listaBar.get(i);
                String p = ejbFacadePromo.obtenerPromocionxBar(b.getId());

                if (p != null && !p.isEmpty()) {
                    list.add(this.toString(b) + p);
                } else {
                    list.add(this.toString(b));
                }

            }
            String json = new Gson().toJson(list);

            return json;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public String serialize() {
        return "<%= id %>|<%= nombre %>|<%= direccion %>|<%= telefono %>|<%= descripcion %>|<%= imagen %>|<%= promocion %>";
    }

    public String toString(Bar b) {
        return b.getId() + "|"
                + b.getBarNombre() + "|"
                + b.getBarDireccion() + "|"
                + b.getBarTelefono() + "|"
                + b.getBarDescripcion() + "|"
                + b.getBarImagen() + "|";
    }
        
    public List<Bar> getItemsAvailableSelectOne() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer Id = (Integer) session.getAttribute("idUsuario");
        Integer IdRole = (Integer) session.getAttribute("idRole");
        System.out.println("Usuario id:" + Id);
        System.out.println("Rol id:" + IdRole);
        //return getFacade().findPromocionesxUsuario(Id, IdRole);
        System.out.println("Inicio metodo");
        List<Bar> lista_bares = ejbFacade.findAll();
        List<Bar> lista_bares2 = new LinkedList<Bar>();
        if (IdRole != 1) {
            for (Bar bar : lista_bares) {
                if (Id == bar.getUsuId().getId()) {

                    System.out.println("Bar: " + bar.getUsuId().getId() + " - Usuario:" + Id);
                    lista_bares2.add(bar);
                }
            }
        } else {
            lista_bares2 = lista_bares;
        }
        lista_bares = lista_bares2;
        return lista_bares;
    }
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    }
}
