/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pedido;
import entity.Producto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author alvar
 */
@Stateless
public class ProductoFacade extends AbstractFacade<Producto> {

    @PersistenceContext(unitName = "CarritoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductoFacade() {
        super(Producto.class);
    }

    public List<Producto> findDisponibles(List<Integer> lista) {
        Query q;
        q = this.getEntityManager().createQuery("SELECT p FROM Producto p WHERE p.cantidad>0 AND p.disponible=true AND p.productoId NOT IN :lista");
        q.setParameter("lista", lista);

        return q.getResultList();
    }

}
