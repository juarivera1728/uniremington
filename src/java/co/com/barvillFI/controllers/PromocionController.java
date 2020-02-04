/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.controllers;

import co.com.barvillFI.entities.Bar;
import co.com.barvillFI.entities.Promocion;
import co.com.barvillFI.facadeLocal.BarFacadeLocal;
import co.com.barvillFI.facadeLocal.PromocionFacadeLocal;
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
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Juan Camilo
 */
@ManagedBean
@SessionScoped
public class PromocionController implements Serializable {

    @EJB
    private co.com.barvillFI.facadeLocal.PromocionFacadeLocal ejbFacade;
    @EJB
    private co.com.barvillFI.facadeLocal.BarFacadeLocal ejbFacadeBar;
    private Promocion promocion; //creamos objeto
    List<Promocion> listaPromocion = null;
    private Bar bar;
    private int barId;
    String mensaje = " ";
    private String accion;
    private List<Object[]> listado = null;
    private PieChartModel torta;

    public List<Object[]> getListado() {
        listado = ejbFacade.listaPromo();
        return listado;
    }

    public void setListado(List<Object[]> listado) {
        this.listado = listado;
    }

    public PieChartModel getTorta() {
        return torta;
    }

    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }

    public PromocionFacadeLocal getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PromocionFacadeLocal ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public BarFacadeLocal getEjbFacadeBar() {
        return ejbFacadeBar;
    }

    public void setEjbFacadeBar(BarFacadeLocal ejbFacadeBar) {
        this.ejbFacadeBar = ejbFacadeBar;
    }

    public List<Promocion> getListaPromocion() {
        if (listaPromocion == null) {
            listaPromocion = ejbFacade.findAll();
        }
        listaPromocion = ejbFacade.findAll();
        return listaPromocion;
    }

    public void setListaPromocion(List<Promocion> listaPromocion) {
        this.listaPromocion = listaPromocion;
    }

    public Promocion getPromocion() {
        return promocion;
    }

    public void setPromocion(Promocion promocion) {
        this.promocion = promocion;
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
        promocion = new Promocion();//genera una instancia al objeto
        listado = ejbFacade.listaPromo();
        graficar();
    }

    public void guardar() {
        try {
            promocion.setBarId(new Bar(barId));
            ejbFacade.create(promocion);
            this.mensaje = "Promocion Insertada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        promocion = new Promocion();
        listaPromocion = ejbFacade.findAll();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void cargarId(Promocion t,int IdB) {
        this.promocion = t;
        this.barId=IdB;
        this.setAccion("M");
    }

    public void editar() {
        try {
            promocion.setBarId(new Bar(barId));
            ejbFacade.edit(promocion);
            this.mensaje = "Promocion editada Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        promocion = new Promocion();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);
        // tipoestado = new Tipoestado();
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));

    }

    public void limpiar() {
        promocion = new Promocion();
    }

    public void eliminar(Promocion ct) {
        try {
            ejbFacade.remove(ct);
            this.mensaje = "Producto eliminado Correctamente";
        } catch (Exception e) {
            this.mensaje = "ERROR :" + e.getMessage();
            e.printStackTrace();
        }
        listaPromocion = ejbFacade.findAll();
        promocion = new Promocion();
        FacesMessage msj = new FacesMessage(this.mensaje);
        FacesContext.getCurrentInstance().addMessage(null, msj);

        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Los datos se registraron con éxito."));
    }

    public List<Promocion> findPromocionesxUsuario() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        Integer Id = (Integer) session.getAttribute("idUsuario");
        Integer IdRole = (Integer) session.getAttribute("idRole");
        System.out.println("Usuario id:" + Id);
        System.out.println("Rol id:" + IdRole);
        //return getFacade().findPromocionesxUsuario(Id, IdRole);
        System.out.println("Inicio metodo");
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
            lista_bares2 = lista_bares;
        }
        lista_bares = lista_bares2;

        System.out.println("Eliminó bares: " + lista_bares.size());

        List<Promocion> listPromocion = getEjbFacade().findAll();
        List<Promocion> listPromocion2 = new LinkedList<>();
        System.out.println("Encontró promociones");

        for (Bar bar : lista_bares) {
            for (Promocion promocion : listPromocion) {
                System.out.println(bar.getId() + "=" + promocion.getBarId().getId());
                if (promocion.getBarId().equals(bar) && !listPromocion2.contains(promocion)) {
                    listPromocion2.add(promocion);
                    System.out.println("Promoción: " + promocion.getPromDescripcion());
                }
            }
        }
        listPromocion = null;
        listPromocion = listPromocion2;
        if (listPromocion == null) {
            return null;
        } else {
            return listPromocion;
        }

    }

    public void graficar() {
        torta = new PieChartModel();
        for (Object[] objects : listado) {
            torta.set(objects[0].toString(), (Number) objects[1]);
        }
        torta.setTitle("Grafico de Promociones");
        torta.setLegendPosition("W");
        torta.setFill(true);
        torta.setShowDataLabels(true);
        torta.setDiameter(150);

    }
}
