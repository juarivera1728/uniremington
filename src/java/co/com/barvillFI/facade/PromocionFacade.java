/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facade;

import co.com.barvillFI.facadeLocal.PromocionFacadeLocal;
import co.com.barvillFI.entities.Promocion;
import java.util.LinkedList;
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
public class PromocionFacade extends AbstractFacade<Promocion> implements PromocionFacadeLocal {

    @PersistenceContext(unitName = "BarvillFIPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PromocionFacade() {
        super(Promocion.class);
    }
      public List<Object[]> listaPromo() {
        Query q = em.createNativeQuery("SELECT B.bar_nombre, COUNT(*) FROM bar B JOIN promocion P ON B.ID = P.bar_id GROUP BY bar_nombre");
        List<Object[]> listado = q.getResultList();
        return listado;
    }
    public String obtenerPromocionxBar(int id_bar) {
        Query q = em.createNativeQuery("SELECT MAX(P.bar_id),P.prom_descripcion FROM promocion P WHERE P.bar_id=?1");
        q.setParameter(1, id_bar);
        Object[] promocion = (Object[]) q.getSingleResult();
        for (Object objects : promocion) {
            System.out.println("pomocion: --> " + objects);
        }
        String retorno = "";
        if (promocion != null && promocion.length > 0 && promocion[1] != null) {
            retorno = promocion[1].toString();
        } else {
            retorno = "No hay promociones";
        }

        return retorno;
    }

    public List<Promocion> findPromocionesxUsuario(int usuarioId, int roleId) {


             System.out.println("Usuario: " + usuarioId + "- Role: " + roleId);
        Query q = em.createNativeQuery("SELECT p.id AS ID, b.bar_nombre AS bar_nombre ,p.prom_descripcion AS prom_descripcion,p.bar_id AS bar "
                + "FROM\n"
                + "usuario u\n"
                + "inner join bar b\n"
                + "	on u.ID = b.usu_id\n"
                + "inner join promocion p\n"
                + "	on b.ID = p.bar_id\n"
                + "WHERE u.ID = ?1 OR 1 = ?2");
        q.setParameter(1, usuarioId);
        q.setParameter(2, roleId);
        List<Object[]> listado = q.getResultList();

        List<Promocion> listPromocion = new LinkedList<Promocion>();
        for (Object[] row : listado) {
            listPromocion.add(new Promocion((int) row[0], (String) row[1], (String) row[2]));
        }
        System.out.println("LLego al final");
        //List<Promocion> listPromocion = q.getResultList();
         
        return listPromocion;
    }
    
}
