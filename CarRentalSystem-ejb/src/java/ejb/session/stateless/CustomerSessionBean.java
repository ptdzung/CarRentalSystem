/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import entity.OwnCustomerEntity;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CustomerNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;
import util.exception.UsernameExistException;

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
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public CustomerSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
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
                throw new InvalidLoginCredentialException("Invalid Login Credential!");
            }
        } catch(NoResultException | NonUniqueResultException e){
            throw new InvalidLoginCredentialException("Invalid Login Credential!");
        }
    }
    
    @Override
    public Long registerNewCustomer(String name, String username, String password, String emailAddress) throws UsernameExistException, InputDataValidationException, UnknownPersistenceException {
        OwnCustomerEntity cus = new OwnCustomerEntity(name, username, password, emailAddress, null);
        
        Set<ConstraintViolation<OwnCustomerEntity>>constraintViolations = validator.validate(cus);
        
        if(constraintViolations.isEmpty())
        {  
            try
            {
                em.persist(cus);
                em.flush();

                return cus.getCustomerId();
            }
            catch(PersistenceException ex)
            {
                if(ex.getCause() != null && ex.getCause().getClass().getName().equals("org.eclipse.persistence.exceptions.DatabaseException"))
                {
                    if(ex.getCause().getCause() != null && ex.getCause().getCause().getClass().getName().equals("java.sql.SQLIntegrityConstraintViolationException"))
                    {
                        throw new UsernameExistException();
                    }
                    else
                    {
                        throw new UnknownPersistenceException(ex.getMessage());
                    }
                }
                else
                {
                    throw new UnknownPersistenceException(ex.getMessage());
                }
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsForCustomer(constraintViolations));
        }
    }
    
    private String prepareInputDataValidationErrorsForCustomer(Set<ConstraintViolation<OwnCustomerEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException {
        Query query = em.createQuery("SELECT c FROM CustomerEntity c WHERE c.email = :inEmail");
        query.setParameter("inEmail", email);
        CustomerEntity cus = (CustomerEntity)query.getSingleResult();
        if (cus != null) {
            return cus;
        } else {
            throw new CustomerNotFoundException("Customer Email " + email + " does not exist!");
        }
    }
    
    
}
