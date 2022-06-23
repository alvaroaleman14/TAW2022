package es.taw.carrito.controller;

import es.taw.carrito.dao.ClienteRepository;
import es.taw.carrito.dao.PedidoProductoRepository;
import es.taw.carrito.dao.PedidoRepository;
import es.taw.carrito.dao.ProductoRepository;
import es.taw.carrito.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PedidoController {

    private PedidoRepository pedidoRepository;

    public PedidoRepository getPedidoRepository() {
        return pedidoRepository;
    }
    @Autowired
    public void setPedidoRepository(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public ClienteRepository getClienteRepository() {
        return clienteRepository;
    }
    @Autowired
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public PedidoProductoRepository getPedidoProductoRepository() {
        return pedidoProductoRepository;
    }
    @Autowired
    public void setPedidoProductoRepository(PedidoProductoRepository pedidoProductoRepository) {
        this.pedidoProductoRepository = pedidoProductoRepository;
    }

    public ProductoRepository getProductoRepository() {
        return productoRepository;
    }
    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private ClienteRepository clienteRepository;
    private PedidoProductoRepository pedidoProductoRepository;
    private ProductoRepository productoRepository;


    @GetMapping("/carrito/editar/{idpedido}")
    public String doVerPedido(@PathVariable("idpedido") Integer idpedido, Model model){
        Pedido pedido = pedidoRepository.findById(idpedido).orElse(null);
        model.addAttribute("pedido",pedido);
        List<Producto> productosDisponibles = new ArrayList<>();

        if(pedido.getPedidoProductoList() != null && !pedido.getPedidoProductoList().isEmpty()){
            List<Integer> idsProductos = new ArrayList<>();
            for(PedidoProducto pp: pedido.getPedidoProductoList()){
                idsProductos.add(pp.getProducto().getProductoId());
            }
             productosDisponibles = productoRepository.getProductosDisponibles(idsProductos);
        }else{
            productosDisponibles = productoRepository.getProductosDisponibles();
        }

        model.addAttribute("productosDisponibles", productosDisponibles);

        BigDecimal coste = pedidoProductoRepository.costePedido(pedido.getPedidoId());
        model.addAttribute("costePedido",coste);
        return "pedido";
    }

    @GetMapping("/carrito/crear/{idcliente}")
    public String doCrearPedido(@PathVariable("idcliente") Integer idcliente, Model model){
        Cliente cliente = clienteRepository.findById(idcliente).orElse(null);
        Pedido pedido = new Pedido();
        pedido.setClienteId(cliente);
        pedidoRepository.save(pedido);
        model.addAttribute("pedido",pedido);

        List<Producto> productosDisponibles = productoRepository.getProductosDisponibles();
        model.addAttribute("productosDisponibles", productosDisponibles);


        return "pedido";
    }

    @PostMapping("/carrito/anadir")
    public String doAnadirProducto(@RequestParam("idPedido") Integer idPedido,
                                   @RequestParam("idProducto") Integer idProducto,
                                   @RequestParam("cantidad") Short cantidad){

        Pedido pedido = pedidoRepository.findById(idPedido).orElse(null);
        Producto producto = productoRepository.findById(idProducto).orElse(null);

        PedidoProducto nuevopp = new PedidoProducto(pedido.getPedidoId(),producto.getProductoId());
        nuevopp.setCantidad(cantidad);
        nuevopp.setProducto(producto);
        nuevopp.setPedido(pedido);
        this.pedidoProductoRepository.save(nuevopp);

        producto.setCantidad(producto.getCantidad()-cantidad);
        List<PedidoProducto> listaProducto = producto.getPedidoProductoList();
        listaProducto.add(nuevopp);
        producto.setPedidoProductoList(listaProducto);
        this.productoRepository.save(producto);

        List<PedidoProducto> listaPedido = pedido.getPedidoProductoList();
        listaPedido.add(nuevopp);
        pedido.setPedidoProductoList(listaPedido);
        this.pedidoRepository.save(pedido);

        return "redirect:/carrito/editar/" + pedido.getPedidoId();
    }




}
