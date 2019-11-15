/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import entity.OwnCustomerEntity;
import entity.RentalRecordEntity;
import java.util.List;
import util.exception.EntityMismatchException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Trishpal
 */
public interface CarReservationControllerRemote {

    public void customerLogout();

    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;

    public List<RentalRecordEntity> retrieveAllReservation();

    public String retrieveReservationDetails(Long resId) throws RentalRecordNotFoundException, EntityMismatchException;

    public String cancelReservation(Long resId) throws RentalRecordNotFoundException;
    
}
