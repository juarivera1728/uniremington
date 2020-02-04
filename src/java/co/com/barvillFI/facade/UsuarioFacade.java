/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facade;

import co.com.barvillFI.facadeLocal.UsuarioFacadeLocal;
import co.com.barvillFI.entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Camilo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "BarvillFIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

  public Usuario encontrarUsuario(String correo){
        Query qu =em.createNamedQuery("Usuario.findByUsuCorreo",Usuario.class).setParameter("usuCorreo",correo);
        List<Usuario> listaUsuario = qu.getResultList();
        if(!listaUsuario.isEmpty()){
            return listaUsuario.get(0);
        }
        return null;
            
    }
    
}
