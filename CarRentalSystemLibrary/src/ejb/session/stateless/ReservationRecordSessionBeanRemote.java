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

/**
 *
 * @author Trishpal
 */
public interface ReservationRecordSessionBeanRemote {
    
    public String retrieveCustomerReservationDetails(Long resId, Long customerId) throws RentalRecordNotFoundException, EntityMismatchException;

    public String cancelReservation(long resId) throws RentalRecordNotFoundException;
    
    public RentalRecordEntity retrieveReservationById(Long resId) throws RentalRecordNotFoundException;

    public List<RentalRecordEntity> retrieveRentalRecordByDate(Date date);

    List<RentalRecordEntity> retrieveRentalRecordsByCustomer(CustomerEntity cus);
    
    public List<RentalRecordEntity> retrieveRentalRecordsByPartner(PartnerEntity part);

    boolean carAllocation(Date date);
    
    public String retrievePartnerReservationDetails(Long resId, Long partnerId) throws RentalRecordNotFoundException, EntityMismatchException;

}
