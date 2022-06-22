package entity;

import entity.Pedido;
import entity.PedidoProductoPK;
import entity.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-21T20:44:30")
@StaticMetamodel(PedidoProducto.class)
public class PedidoProducto_ { 

    public static volatile SingularAttribute<PedidoProducto, Pedido> pedido;
    public static volatile SingularAttribute<PedidoProducto, Short> cantidad;
    public static volatile SingularAttribute<PedidoProducto, Producto> producto;
    public static volatile SingularAttribute<PedidoProducto, PedidoProductoPK> pedidoProductoPK;

}