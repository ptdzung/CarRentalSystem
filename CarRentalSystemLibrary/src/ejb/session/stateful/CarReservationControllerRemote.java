/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import javax.ejb.Remote;
import util.exception.InvalidLoginCredentialException;

/**
 *
 * @author Trishpal
 */
@Remote
public interface CarReservationControllerRemote {

    public void customerLogout();

    public void customerLogin(String username, String password) throws InvalidLoginCredentialException;
    
}
