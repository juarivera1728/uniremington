/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facade;

import co.com.barvillFI.facadeLocal.HistorialSesionFacadeLocal;
import co.com.barvillFI.entities.HistorialSesion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Juan Camilo
 */
@Stateless
public class HistorialSesionFacade extends AbstractFacade<HistorialSesion> implements HistorialSesionFacadeLocal {

    @PersistenceContext(unitName = "BarvillFIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialSesionFacade() {
        super(HistorialSesion.class);
    }
    
}
