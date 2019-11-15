/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import entity.OwnCustomerEntity;
import javax.ejb.Local;
import util.exception.CustomerNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.UnknownPersistenceException;
import util.exception.UsernameExistException;

/**
 *
 * @author Trishpal
 */
@Local
public interface CustomerSessionBeanLocal {

    public Long registerNewCustomer(String name, String username, String password, String emailAddress) throws UsernameExistException, InputDataValidationException, UnknownPersistenceException;

    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;

    CustomerEntity retrieveCustomerByEmail(String email) throws CustomerNotFoundException;
    
}
