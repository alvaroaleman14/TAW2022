package entity;

import entity.Cliente;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-21T20:44:30")
@StaticMetamodel(ClienteDatos.class)
public class ClienteDatos_ { 

    public static volatile SingularAttribute<ClienteDatos, String> domicilio;
    public static volatile SingularAttribute<ClienteDatos, Cliente> clienteId;
    public static volatile SingularAttribute<ClienteDatos, String> ciudad;
    public static volatile SingularAttribute<ClienteDatos, Integer> datosId;
    public static volatile SingularAttribute<ClienteDatos, String> cp;
    public static volatile SingularAttribute<ClienteDatos, String> pais;

}