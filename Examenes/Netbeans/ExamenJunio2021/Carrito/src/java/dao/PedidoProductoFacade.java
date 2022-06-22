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
import javax.persistence.Query;

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
    
    public PedidoProducto findbyId(Integer idpedido, Integer idproducto){
        Query q;
        q = this.getEntityManager().createQuery("SELECT pp FROM PedidoProducto pp WHERE pp.pedido_id = :idpedido AND pp.producto_id= :idproducto");
        q.setParameter("idpedido", idpedido);
        q.setParameter("idproducto", idproducto);
        
        return (PedidoProducto)q.getSingleResult();
    }
    
}
