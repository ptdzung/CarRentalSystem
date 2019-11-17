/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import entity.PartnerEntity;
import entity.RentalRecordEntity;
import util.exception.EntityMismatchException;
import util.exception.RentalRecordNotFoundException;
import java.util.Date;
import java.util.List;
import util.exception.InputDataValidationException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Trishpal
 */
public interface ReservationRecordSessionBeanRemote {
    public String retrieveReservationDetails(Long resId, Long customerId) throws RentalRecordNotFoundException, EntityMismatchException;

    public String cancelReservation(long resId) throws RentalRecordNotFoundException;
    
    public RentalRecordEntity retrieveReservationById(Long resId) throws RentalRecordNotFoundException;

    public List<RentalRecordEntity> retrieveRentalRecordByDate(Date date);

    List<RentalRecordEntity> retrieveRentalRecordsByCustomer(CustomerEntity cus);

    boolean carAllocation(Date date);

    Long createNewRentalRecord(RentalRecordEntity record) throws InputDataValidationException, UnknownPersistenceException;
    
    String retrievePartnerReservationDetails(Long resId, Long partnerId) throws RentalRecordNotFoundException, EntityMismatchException;

    List<RentalRecordEntity> retrieveRentalRecordsByPartner(PartnerEntity part);
}
