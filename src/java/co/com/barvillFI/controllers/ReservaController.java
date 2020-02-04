/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Bar;

import co.com.barvillFI.entities.Reserva;
import co.com.barvillFI.facadeLocal.BarFacadeLocal;
import co.com.barvillFI.facadeLocal.ReservaFacadeLocal;
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
public class ReservaController implements Serializable {

    @EJB
    private co.com.barvillFI.facadeLocal.ReservaFacadeLocal ejbFacade;
    @EJB
    private co.com.barvillFI.facadeLocal.BarFacadeLocal ejbFacadeBar;
    private Reserva reserva; //creamos objeto
    List<Reserva> listaReserva = null;
    List<Reserva> listaReservaC = null;
    private Bar bar;
    private int barId;
    String mensaje = " ";
    private String accion;

    public List<Reserva> getListaReservaC() {
      if (listaReservaC == null) {
            listaReservaC = ejbFacade.findAll();
        }
        listaReservaC = ejbFacade.findAll();
        return listaReservaC;
    }

    public void setListaReservaC(List<Reserva> listaReservaC) {
        this.listaReservaC = listaReservaC;
    }

    public ReservaFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ReservaFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public BarFacadeLocal getEjbFacadeBar() {
        return ejbFacadeBar;
    }

    public void setEjbFacadeBar(BarFacadeLocal ejbFacadeBar) {
        this.ejbFacadeBar = ejbFacadeBar;
    }

    public List<Reserva> getListaReserva() {
        if (listaReserva == null) {
            listaReserva = ejbFacade.findAll();
        }
        listaReserva = ejbFacade.findAll();
        return listaReserva;
    }

    public void setListaReserva(List<Reserva> listaReserva) {
        this.listaReserva = listaReserva;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva Reserva) {
        this.reserva = Reserva;
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
        reserva = new Reserva();//genera una instancia al objeto
        listaReserva = ejbFacade.findAll();
    }

    public void guardar() {
        try {
            reserva.setBarId(new Bar(barId));
            ejbFacade.create(reserva);
            this.mensaje = "Promocion Insertada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        reserva = new Reserva();
        listaReserva = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Reserva t,int IdB) {
        this.reserva = t;
        this.barId =IdB;
        this.setAccion("M");
    }

    public void editar() {
        try {
            reserva.setBarId(new Bar(barId));
            ejbFacade.edit(reserva);
            this.mensaje = "Reserva editada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        reserva = new Reserva();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        reserva = new Reserva();
    }

    public void eliminar(Reserva ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Producto eliminado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaReserva = ejbFacade.findAll();
        reserva = new Reserva();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    }

    public List<Reserva> findReservasxUsuario() {
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

        List<Reserva> listReserva = getEjbFacade().findAll();
        List<Reserva> listReserva2 = new LinkedList<>();
        System.out.println("Encontró reservas");

        for (Bar bar : lista_bares) {
            for (Reserva reserva : listReserva) {
                System.out.println(bar.getId() + "=" + reserva.getBarId().getId());
                if (reserva.getBarId().equals(bar) && !listReserva2.contains(reserva)) {
                    listReserva2.add(reserva);

                }
            }
        }
        listReserva = listReserva2;
//n -> !(lista_bares.contains(n.getBarId())));
        System.out.println("LLego al final");

        return listReserva;

    }
}
