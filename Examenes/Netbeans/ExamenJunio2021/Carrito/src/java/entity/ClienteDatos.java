/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alvar
 */
@Entity
@Table(name = "CLIENTE_DATOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteDatos.findAll", query = "SELECT c FROM ClienteDatos c")
    , @NamedQuery(name = "ClienteDatos.findByDatosId", query = "SELECT c FROM ClienteDatos c WHERE c.datosId = :datosId")
    , @NamedQuery(name = "ClienteDatos.findByCp", query = "SELECT c FROM ClienteDatos c WHERE c.cp = :cp")
    , @NamedQuery(name = "ClienteDatos.findByDomicilio", query = "SELECT c FROM ClienteDatos c WHERE c.domicilio = :domicilio")
    , @NamedQuery(name = "ClienteDatos.findByCiudad", query = "SELECT c FROM ClienteDatos c WHERE c.ciudad = :ciudad")
    , @NamedQuery(name = "ClienteDatos.findByPais", query = "SELECT c FROM ClienteDatos c WHERE c.pais = :pais")})
public class ClienteDatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DATOS_ID")
    private Integer datosId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "CP")
    private String cp;
    @Size(max = 80)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Size(max = 25)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Size(max = 25)
    @Column(name = "PAIS")
    private String pais;
    @JoinColumn(name = "CLIENTE_ID", referencedColumnName = "CLIENTE_ID")
    @ManyToOne
    private Cliente clienteId;

    public ClienteDatos() {
    }

    public ClienteDatos(Integer datosId) {
        this.datosId = datosId;
    }

    public ClienteDatos(Integer datosId, String cp) {
        this.datosId = datosId;
        this.cp = cp;
    }

    public Integer getDatosId() {
        return datosId;
    }

    public void setDatosId(Integer datosId) {
        this.datosId = datosId;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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
        hash += (datosId != null ? datosId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClienteDatos)) {
            return false;
        }
        ClienteDatos other = (ClienteDatos) object;
        if ((this.datosId == null && other.datosId != null) || (this.datosId != null && !this.datosId.equals(other.datosId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ClienteDatos[ datosId=" + datosId + " ]";
    }
    
}
