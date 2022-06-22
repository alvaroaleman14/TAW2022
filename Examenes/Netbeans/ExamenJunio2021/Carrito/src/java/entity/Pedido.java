/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "PEDIDO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")
    , @NamedQuery(name = "Pedido.findByPedidoId", query = "SELECT p FROM Pedido p WHERE p.pedidoId = :pedidoId")
    , @NamedQuery(name = "Pedido.findByGastosEnvio", query = "SELECT p FROM Pedido p WHERE p.gastosEnvio = :gastosEnvio")
    , @NamedQuery(name = "Pedido.findByFechaCompra", query = "SELECT p FROM Pedido p WHERE p.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "Pedido.findByFechaEnvio", query = "SELECT p FROM Pedido p WHERE p.fechaEnvio = :fechaEnvio")
    , @NamedQuery(name = "Pedido.findByEmpresaReparto", query = "SELECT p FROM Pedido p WHERE p.empresaReparto = :empresaReparto")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PEDIDO_ID")
    private Integer pedidoId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "GASTOS_ENVIO")
    private BigDecimal gastosEnvio;
    @Column(name = "FECHA_COMPRA")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Column(name = "FECHA_ENVIO")
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    @Size(max = 30)
    @Column(name = "EMPRESA_REPARTO")
    private String empresaReparto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<PedidoProducto> pedidoProductoList;
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID")
    @ManyToOne(optional = false)
    private Cliente clienteId;

    public Pedido() {
    }

    public Pedido(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public BigDecimal getGastosEnvio() {
        return gastosEnvio;
    }

    public void setGastosEnvio(BigDecimal gastosEnvio) {
        this.gastosEnvio = gastosEnvio;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEmpresaReparto() {
        return empresaReparto;
    }

    public void setEmpresaReparto(String empresaReparto) {
        this.empresaReparto = empresaReparto;
    }

    @XmlTransient
    public List<PedidoProducto> getPedidoProductoList() {
        return pedidoProductoList;
    }

    public void setPedidoProductoList(List<PedidoProducto> pedidoProductoList) {
        this.pedidoProductoList = pedidoProductoList;
    }

    public Cliente getClienteId() {
        return clienteId;
    }

    public void setClienteId(Cliente clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedidoId != null ? pedidoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.pedidoId == null && other.pedidoId != null) || (this.pedidoId != null && !this.pedidoId.equals(other.pedidoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pedido[ pedidoId=" + pedidoId + " ]";
    }
    
}
