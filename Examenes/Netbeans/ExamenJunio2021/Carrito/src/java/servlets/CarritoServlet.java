/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.ClienteFacade;
import dao.PedidoFacade;
import dao.ProductoFacade;
import entity.Cliente;
import entity.Pedido;
import entity.PedidoProducto;
import entity.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name="CarritoServlet",urlPatterns={"/Carrito"})
public class CarritoServlet extends HttpServlet {

    @EJB ClienteFacade cf;
    @EJB ProductoFacade pf;
    @EJB PedidoFacade pedf;
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
        
      //Recogemos de la URL los parametros
      String idpedido = (String)request.getParameter("idpedido");
      String idcliente = (String)request.getParameter("idcliente");
      
       //Buscamos el pedido
      Pedido p = pedf.find(Integer.parseInt(idpedido));   
      
      List<Integer> ids = new ArrayList<>();
      
      for (PedidoProducto pr : p.getPedidoProductoList()){
          Producto producto = pr.getProducto();
          ids.add(producto.getProductoId());
      }
      
      //Hacemos la lista de los productos disponibles
      List<Producto> productosDisponibles = pf.findDisponibles(ids);
      
     
      //En caso de que el pedido no exista
      if (idpedido == null || idpedido.isEmpty() || p == null){
          
          //Cogemos el cliente
          Cliente c = cf.find(Integer.parseInt(idcliente));
          
          //Creamos el nuevo pedido y le asociamos el id del cliente
          p = new Pedido();
          p.setClienteId(c);
          
          //Añadimos el pedido a la lista de pedidos del cliente
          c.getPedidoList().add(p);
          
          //Llamamos a la facade de pedidos para crear el pedido
          pedf.create(p);
          
          //Llamamos a la facade del cliente para editarlo y asi que se actualice
          cf.edit(c);
      }
      
      
      request.setAttribute("pedido", p);
      request.setAttribute("cliente", p.getClienteId());
      request.setAttribute("disponibles", productosDisponibles);
        
      request.getRequestDispatcher("/WEB-INF/jsp/carrito.jsp").forward(request,response);
        
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
