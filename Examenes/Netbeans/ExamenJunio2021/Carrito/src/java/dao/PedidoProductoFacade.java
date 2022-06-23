/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Pedido;
import entity.PedidoProducto;
import entity.Producto;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
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
    
        public BigDecimal getCosteTotal(Pedido p) {
        Query q;
        BigDecimal coste;

        Integer idPedido = p.getPedidoId();

        q = this.getEntityManager().createQuery("SELECT SUM(pp.cantidad * pr.coste) FROM PedidoProducto pp, Producto pr WHERE pp.pedidoProductoPK.pedidoId = :idPedido");
        q.setParameter("idPedido", idPedido);

        coste = (BigDecimal) q.getSingleResult();
        return coste;
    }
}
