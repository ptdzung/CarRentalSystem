/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.PartnerEntity;
import util.exception.InvalidLoginCredentialException;


/**
 *
 * @author Trishpal
 */

public interface PartnerSessionBeanLocal {

    public PartnerEntity partnerLogin(String username, String password) throws InvalidLoginCredentialException;

    public Long createNewPartner(PartnerEntity partner);
    
}
