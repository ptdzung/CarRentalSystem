/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.OwnCustomerEntity;
import util.exception.InvalidLoginCredentialException;


/**
 *
 * @author Trishpal
 */

public interface CustomerSessionBeanRemote {
    
    public void registerNewCustomer(String name, String username, String password, String emailAddress);

    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;
}
