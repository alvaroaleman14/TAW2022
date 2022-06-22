/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClienteFacade;
import dao.PedidoFacade;
import dao.PedidoProductoFacade;
import dao.ProductoFacade;
import entity.Pedido;
import entity.PedidoProducto;
import entity.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alvar
 */
@WebServlet(name = "NuevoProductoServlet", urlPatterns = {"/NuevoProductoServlet"})
public class NuevoProductoServlet extends HttpServlet {
    @EJB ClienteFacade cf;
    @EJB ProductoFacade pf;
    @EJB PedidoFacade pedf;
    @EJB PedidoProductoFacade ppf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         
        //Recogemos de la URL las variables que hemos pasado
      String idpedido = (String)request.getParameter("idpedido");
      String idproducto = (String)request.getParameter("idproducto");
      String cantidad = (String)request.getAttribute("cantidad");
      
      //Buscamos el pedido y el producto
      Pedido p = pedf.find(Integer.parseInt(idpedido));
      Producto producto = pf.find(Integer.parseInt(idproducto));
      PedidoProducto pp = ppf.findbyId(p.getPedidoId(),producto.getProductoId());
      
      if (pp == null){
          //En caso de que el pedido de productos sea nulo se crea uno nuevo y añadimos el producto y creamos el pedidoproducto
          pp = new PedidoProducto(p.getPedidoId(), producto.getProductoId());
          pp.setCantidad(Short.parseShort(cantidad));
          pp.setPedido(p);
          pp.setProducto(producto);
          ppf.create(pp);
      }else{
          //Actualizamos la cantidad que quedaba, restandole la que ha seleccionado el cliente
          int lastcantidad = Short.toUnsignedInt(pp.getCantidad());
          int newcantidad = Integer.parseInt(cantidad);
          int update = lastcantidad - newcantidad;
          
          pp.setCantidad(Short.parseShort(Integer.toString(update)));
          ppf.edit(pp);
      }
      
      p.getPedidoProductoList().add(pp);
      producto.getPedidoProductoList().add(pp);
      producto.setCantidad(producto.getCantidad()-Integer.parseInt(cantidad));
      
      pf.edit(producto);
      pedf.edit(p);
      
      response.sendRedirect(request.getContextPath()+"/Carrito?idpedido="+p.getPedidoId());
      

      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
