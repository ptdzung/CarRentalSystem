/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OwnCustomerEntity;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Trishpal
 */
@Stateless
@Local(CustomerSessionBeanLocal.class)
@Remote(CustomerSessionBeanRemote.class)
public class CustomerSessionBean implements CustomerSessionBeanRemote, CustomerSessionBeanLocal {

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;

    public CustomerSessionBean() {
    }

    @Override
    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException{
        Query q = em.createQuery("SELECT c FROM OwnCustomerEntity c WHERE c.username = :username");
        q.setParameter("username", username);
        try{
            OwnCustomerEntity customer = (OwnCustomerEntity) q.getSingleResult();
            if(customer.getPassword().equals(password)){
              
                return customer;
            }else{
                throw new InvalidLoginCredentialException("Invalid Login Credential");
            }
        }catch(NoResultException | NonUniqueResultException e){
            throw new InvalidLoginCredentialException("Invalid Login Credential");
        }
    }
    
    @Override
    public void registerNewCustomer(String name, String username, String password, String emailAddress) {
        OwnCustomerEntity newCustomer = new OwnCustomerEntity(username, password, name, emailAddress, null);
        em.persist(newCustomer);
    }
    
    
}
