/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PartnerEntity;
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
@Local(PartnerSessionBeanLocal.class)
@Remote(PartnerSessionBeanRemote.class)
public class PartnerSessionBean implements PartnerSessionBeanRemote, PartnerSessionBeanLocal {
    
    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
  
    @Override
    public PartnerEntity partnerLogin(String username, String password) throws InvalidLoginCredentialException {
        Query q = em.createQuery("SELECT p FROM PartnerEntity p WHERE p.username = :username");
        q.setParameter("username", username);
        try{
            PartnerEntity partner = (PartnerEntity) q.getSingleResult();
            if(partner.getPassword().equals(password)){
                return partner;
            }else{
                throw new InvalidLoginCredentialException("Invalid Login Credential!");
            }
        } catch(NoResultException | NonUniqueResultException ex){
            throw new InvalidLoginCredentialException("Invalid Login Credential!");
        }       
    }
    
    @Override
    public Long createNewPartner(PartnerEntity partner){
        em.persist(partner);
        em.flush();
        
        return partner.getPartnerId();
    } 
    
        
     
}

