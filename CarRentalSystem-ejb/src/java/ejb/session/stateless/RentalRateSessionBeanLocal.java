/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RentalRateEntity;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
public interface RentalRateSessionBeanLocal {

    Long createNewRentalRate(RentalRateEntity rentalRate) throws InputDataValidationException, UnknownPersistenceException;
    
}
