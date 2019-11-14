/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RentalRateEntity;
import java.util.List;
import util.exception.CarCategoryNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.RentalRateNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Pham The Dzung
 */
public interface RentalRateSessionBeanLocal {

    Long createNewRentalRate(RentalRateEntity rentalRate) throws InputDataValidationException, UnknownPersistenceException;

    RentalRateEntity retrieveRentalRate(String rateName, String categoryName) throws RentalRateNotFoundException, CarCategoryNotFoundException;

    void updateRentalRate(RentalRateEntity rate) throws RentalRateNotFoundException, InputDataValidationException;
    
    RentalRateEntity retrieveRentalRateById(Long rentalRateId) throws RentalRateNotFoundException;
    
    boolean deleteRentalRate(Long rentalRateId) throws RentalRateNotFoundException;

    List<RentalRateEntity> retrieveAllRentalRates();
    
}
