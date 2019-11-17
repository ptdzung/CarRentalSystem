/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import entity.CarCategoryEntity;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.OutletEntity;
import entity.OwnCustomerEntity;
import entity.RentalRateEntity;
import entity.RentalRecordEntity;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import util.exception.EntityMismatchException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRateNotFoundException;
import util.exception.RentalRecordNotFoundException;



/**
 *
 * @author Trishpal
 */

public interface CarReservationControllerLocal {
    
    public void customerLogout();

    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException;

    public List<RentalRecordEntity> retrieveAllReservation();

    public String retrieveReservationDetails(Long resId) throws RentalRecordNotFoundException, EntityMismatchException;

    public String cancelReservation(Long resId) throws RentalRecordNotFoundException;

    List<CarEntity> searchCar(CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet) throws RentalRateNotFoundException ;
    
    List<RentalRateEntity> retrieveListOfOptimalRates(CarCategoryEntity category, Date startDate, Date endDate);

    Long createNewReservation(String creditCardNumber, Boolean hasPaid, CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet, OutletEntity returnOutlet, BigDecimal totalAmount);

    List<RentalRecordEntity> retrieveAllPartnerReservation();
    
    public String retrievePartnerReservationDetails(Long resId) throws RentalRecordNotFoundException, EntityMismatchException;
}