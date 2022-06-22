package entity;

import entity.PedidoProducto;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-21T20:44:30")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile ListAttribute<Producto, PedidoProducto> pedidoProductoList;
    public static volatile SingularAttribute<Producto, Integer> productoId;
    public static volatile SingularAttribute<Producto, String> fabricante;
    public static volatile SingularAttribute<Producto, Integer> cantidad;
    public static volatile SingularAttribute<Producto, BigDecimal> coste;
    public static volatile SingularAttribute<Producto, Boolean> disponible;

}