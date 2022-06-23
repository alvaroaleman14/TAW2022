<%@ page import="es.taw.carrito.entity.Pedido" %>
<%@ page import="es.taw.carrito.entity.PedidoProducto" %>
<%@ page import="es.taw.carrito.entity.Producto" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: felip
  Date: 05/06/2022
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pedido</title>
</head>
<body>
    <h2>Datos del cliente:</h2>
<%
    Pedido pedido = (Pedido) request.getAttribute("pedido");
%>
    Nombre: <%= pedido.getClienteId().getNombre()%><br>
    Apellidos: <%= pedido.getClienteId().getApellidos()%><br>
    Email:   <%= pedido.getClienteId().getEmail()%><br>
    Precio: <%= request.getAttribute("costePedido") %>
    <h2>Productos en la cesta de la compra:</h2>
    <%
        if(pedido.getPedidoProductoList() != null && !pedido.getPedidoProductoList().isEmpty()){
    %>
    <table border="1">
        <tr>
            <th>IDPRODUCTO</th>
            <th>DESCRIPCION</th>
            <th>CANTIDAD COMPRADA</th>
            <th>FABRICANTE</th>
            <th>COSTE</th>
        </tr>

        <%
            for(PedidoProducto pp:pedido.getPedidoProductoList()){
        %>
        <tr>
            <td><%= pp.getProducto().getProductoId()%></td>
            <td><%= pp.getProducto().getDescripcion()%></td>
            <td><%= pp.getCantidad()%></td>
            <td><%= pp.getProducto().getFabricante()%></td>
            <td><%= pp.getProducto().getCoste()%></td>
        </tr>
        <%
            }
        %>
    </table>
<%
    } else{
%>
    No hay productos en su cesta
<%
    }
%>


    <h2>Productos disponibles:</h2>
    <%
        List<Producto> productosDisponibles = (List) request.getAttribute("productosDisponibles");
        if(productosDisponibles != null && !productosDisponibles.isEmpty()){
    %>
    <table border="1">
        <tr>
            <th>IDPRODUCTO</th>
            <th>DESCRIPCION</th>
            <th>CANTIDAD COMPRADA</th>
            <th>FABRICANTE</th>
            <th>COSTE</th>
            <th></th>
            <th></th>
        </tr>

        <%
            for(Producto p:productosDisponibles){
        %>

        <tr>
            <td><%= p.getProductoId()%></td>
            <td><%= p.getDescripcion()%></td>
            <td><%= p.getCantidad() %></td>
            <td><%= p.getFabricante()%></td>
            <td><%= p.getCoste()%></td>
            <form action="/carrito/anadir" method="post">
                <input type="hidden" name="idProducto" value="<%=p.getProductoId()%>">
                <input type="hidden" name="idPedido" value="<%=pedido.getPedidoId()%>">
                 <td><input type="text" name="cantidad" value="" size="5"></td>
                <td><input type="submit" value="Añadir producto..."></td>
            </form>
        </tr>

        <%
            }
        %>
    </table>
    <%
    } else{
    %>
    No hay productos disponibles
    <%
        }
    %>
</body>
</html>
