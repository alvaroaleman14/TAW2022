package es.taw.carrito.dao;

import es.taw.carrito.entity.Pedido;
import es.taw.carrito.entity.PedidoProducto;
import es.taw.carrito.entity.PedidoProductoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, PedidoProductoPK> {
    @Query("SELECT SUM(pp.cantidad * pr.coste) FROM PedidoProducto pp, Producto pr WHERE pp.pedidoProductoPK.pedidoId = :pedidoId AND pp.pedidoProductoPK.productoId = pr.productoId")
    public BigDecimal costePedido(@Param("pedidoId") Integer pedidoId);
}
