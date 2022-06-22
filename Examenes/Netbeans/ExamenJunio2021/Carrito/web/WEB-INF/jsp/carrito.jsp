<%-- 
    Document   : carrito
    Created on : 21-jun-2022, 17:21:04
    Author     : alvar
--%>
<%@page import="java.util.List"%>
<%@page import="entity.Producto"%>
<%@page import="entity.PedidoProducto"%>
<%@page import="entity.Pedido"%>
<%@page import="entity.Cliente"%>
<%@page import="entity.ClienteDatos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de productos</title>
    </head>
    
    <%
        Pedido p = (Pedido)request.getAttribute("pedido");
        List<Producto> disponibles = (List)request.getAttribute("disponibles");

        if (p.getPedidoId() == null){
            p = new Pedido();
        }
        
        %>
    <body>
        <h1>Datos del usuario: </h1>
        <p> Nombre: <%=p!= null ? p.getClienteId().getNombre():""%></p>
        <p> Apellidos: <%=p!=null ? p.getClienteId().getApellidos():""%> </p>
        <p> Email: <%=p!=null ? p.getClienteId().getEmail():""%> </p>
       
        <h1>Productos de la cesta</h1>
         <%
            if (p.getPedidoProductoList().isEmpty()){
                
         %>
         <p>No hay productos en la lista</p>
         <%
             }else{
             %>
        <table border="1">
            <tr>
            <th>Id del producto</th>
            <th>Descripción</th>
            <th>Cantidad comprada</th>
            <th>Fabricante</th>
            <th>Coste</th>
            </tr>
            <%
                for(PedidoProducto pr : p.getPedidoProductoList()){
                    Producto producto = pr.getProducto();
                %>
            <tr>
                <td><%=producto.getProductoId()%></td>
                <td><%=producto.getDescripcion()%></td>
                <td><%=pr.getCantidad()%></td>
                <td><%=producto.getFabricante()%></td>
                <td><%=producto.getCoste()%></td>
            </tr>
             <%
                }
            }
             %>
        </table>
        <h1>Productos disponibles</h1>
        <table border="1">
            <tr>
            <th>Id del producto</th>
            <th>Descripción</th>
            <th>Cantidad</th>
            <th>Fabricante</th>
            <th>Coste</th>
            <th></th>
            <th></th>
            </tr>
            <%
                if (!disponibles.isEmpty()){
                for(Producto prod : disponibles){
                %>
            <tr>
                <td><%=prod.getProductoId()%></td>
                <td><%=prod.getDescripcion()%></td>
                <td><%=prod.getCantidad()%></td>
                <td><%=prod.getFabricante()%></td>
                <td><%=prod.getCoste()%></td>
                <td><input type="text" name="cantidad" size="2"/></td>
                <td>
            <form action="NuevoProductoServlet?idpedido=<%=p.getPedidoId()%>&idproducto=<%=prod.getProductoId()%>" method="POST">
                <input type="submit" value="Añadir"/>
            </form>
                </td>
            </tr>
            <%
                }
            }
              %>
             </table> 
    </body>
</html>
