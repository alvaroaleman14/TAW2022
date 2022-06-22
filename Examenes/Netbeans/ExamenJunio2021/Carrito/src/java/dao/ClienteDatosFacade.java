/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.ClienteDatos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alvar
 */
@Stateless
public class ClienteDatosFacade extends AbstractFacade<ClienteDatos> {

    @PersistenceContext(unitName = "CarritoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteDatosFacade() {
        super(ClienteDatos.class);
    }
    
}
