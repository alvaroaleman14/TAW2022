package entity;

import entity.Cliente;
import entity.PedidoProducto;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-21T20:44:30")
@StaticMetamodel(Pedido.class)
public class Pedido_ { 

    public static volatile SingularAttribute<Pedido, Date> fechaCompra;
    public static volatile SingularAttribute<Pedido, Date> fechaEnvio;
    public static volatile SingularAttribute<Pedido, Integer> pedidoId;
    public static volatile SingularAttribute<Pedido, String> empresaReparto;
    public static volatile SingularAttribute<Pedido, Cliente> clienteId;
    public static volatile ListAttribute<Pedido, PedidoProducto> pedidoProductoList;
    public static volatile SingularAttribute<Pedido, BigDecimal> gastosEnvio;

}