/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.barvillFI.facadeLocal;

import co.com.barvillFI.entities.Bar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Juan Camilo
 */
@Local
public interface BarFacadeLocal {

    void create(Bar bar);

    void edit(Bar bar);

    void remove(Bar bar);

    Bar find(Object id);

    List<Bar> findAll();

    List<Bar> findRange(int[] range);

    int count();
    
}
