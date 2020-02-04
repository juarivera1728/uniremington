/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facadeLocal;

import co.com.barvillFI.entities.Promocion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Camilo
 */
@Local
public interface PromocionFacadeLocal {

    void create(Promocion promocion);

    void edit(Promocion promocion);

    void remove(Promocion promocion);

    Promocion find(Object id);

    List<Promocion> findAll();

    List<Promocion> findRange(int[] range);

    int count();
    
    String obtenerPromocionxBar(int id_bar);
    
    List<Object[]> listaPromo();
    
    List<Promocion> findPromocionesxUsuario(int usuarioId, int roleId);
    
}
