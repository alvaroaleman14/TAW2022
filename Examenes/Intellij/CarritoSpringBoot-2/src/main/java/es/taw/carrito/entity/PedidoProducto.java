/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.taw.carrito.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author guzman
 */
@Entity
@Table(name = "PEDIDO_PRODUCTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PedidoProducto.findAll", query = "SELECT p FROM PedidoProducto p")
    , @NamedQuery(name = "PedidoProducto.findByPedidoId", query = "SELECT p FROM PedidoProducto p WHERE p.pedidoProductoPK.pedidoId = :pedidoId")
    , @NamedQuery(name = "PedidoProducto.findByProductoId", query = "SELECT p FROM PedidoProducto p WHERE p.pedidoProductoPK.productoId = :productoId")
    , @NamedQuery(name = "PedidoProducto.findByCantidad", query = "SELECT p FROM PedidoProducto p WHERE p.cantidad = :cantidad")})
public class PedidoProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PedidoProductoPK pedidoProductoPK;
    @Column(name = "CANTIDAD")
    private Short cantidad;
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "PEDIDO_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedido pedido;
    @JoinColumn(name = "PRODUCTO_ID", referencedColumnName = "PRODUCTO_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Producto producto;

    public PedidoProducto() {
    }

    public PedidoProducto(PedidoProductoPK pedidoProductoPK) {
        this.pedidoProductoPK = pedidoProductoPK;
    }

    public PedidoProducto(int pedidoId, int productoId) {
        this.pedidoProductoPK = new PedidoProductoPK(pedidoId, productoId);
    }

    public PedidoProductoPK getPedidoProductoPK() {
        return pedidoProductoPK;
    }

    public void setPedidoProductoPK(PedidoProductoPK pedidoProductoPK) {
        this.pedidoProductoPK = pedidoProductoPK;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoProductoPK != null ? pedidoProductoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PedidoProducto)) {
            return false;
        }
        PedidoProducto other = (PedidoProducto) object;
        if ((this.pedidoProductoPK == null && other.pedidoProductoPK != null) || (this.pedidoProductoPK != null && !this.pedidoProductoPK.equals(other.pedidoProductoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.taw.carrito.entity.PedidoProducto[ pedidoProductoPK=" + pedidoProductoPK + " ]";
    }
    
}
