/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.PedidoProducto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvar
 */
@Stateless
public class PedidoProductoFacade extends AbstractFacade<PedidoProducto> {

    @PersistenceContext(unitName = "CarritoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoProductoFacade() {
        super(PedidoProducto.class);
    }
    
}
