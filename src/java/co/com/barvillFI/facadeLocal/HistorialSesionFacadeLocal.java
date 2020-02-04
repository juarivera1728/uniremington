/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facadeLocal;

import co.com.barvillFI.entities.HistorialSesion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Camilo
 */
@Local
public interface HistorialSesionFacadeLocal {

    void create(HistorialSesion historialSesion);

    void edit(HistorialSesion historialSesion);

    void remove(HistorialSesion historialSesion);

    HistorialSesion find(Object id);

    List<HistorialSesion> findAll();

    List<HistorialSesion> findRange(int[] range);

    int count();
    
}
