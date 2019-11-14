/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.RentalRecordEntity;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CancelReservationException;
import util.exception.EntityMismatchException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Trishpal
 */
@Stateless
@Local(ReservationRecordSessionBeanLocal.class)
@Remote(ReservationRecordSessionBeanRemote.class)
public class ReservationRecordSessionBean implements ReservationRecordSessionBeanRemote, ReservationRecordSessionBeanLocal {

   
    @EJB
    private CarSessionBeanLocal carSessionBean;
    
    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;

    public ReservationRecordSessionBean() {
    }
    
    @Override
    public String cancelReservation(long resId) throws RentalRecordNotFoundException {
        RentalRecordEntity reservationToCancel. = retrieveReservationById(resId);
        
        if (reservationToCancel.isHasPaid()) {
            if (reservationToCancel.getRentedFrom())
            reservationToCancel.setCancelled(true);
            
        }
        else {
            
            reservationToCancel.setCancelled(true);
        }
            
      }
        
     
   
    public RentalRecordEntity retrieveReservationById(Long resId) throws RentalRecordNotFoundException {
        RentalRecordEntity res =  em.find(RentalRecordEntity.class, resId);
        if (res != null) {
            return res;
        }
        else {
            throw new RentalRecordNotFoundException("Reservation ID " + resId + "does not exist!");
        }
    }
    
    @Override
    public String retrieveReservationDetails(Long resId, Long customerId) throws RentalRecordNotFoundException, EntityMismatchException{
        RentalRecordEntity res =  em.find(RentalRecordEntity.class, resId);
        if(res == null){
            throw new RentalRecordNotFoundException("Reservation Record not found");
        }else if(res.getCustomer() == null){
            throw new EntityMismatchException("Customer Id provided does not match Customer Id in reservation record");
        }else if(!res.getCustomer().getCustomerId().equals(customerId)){
            throw new EntityMismatchException("Customer Id provided does not match Customer Id in reservation record");
        }else{
            String details = "Reservation Id: " + res.getRentalRecordId() + "\n" +
                             "Car: " + res.getCar() + "\n" +
                             "Start Date: " + res.getRentedFrom() + "\n" +
                             "End Date: " + res.getRentedTo() + "\n" +
                             "Total Amount: $" + res.getTotalAmount() + "\n";
            
            return details;
        }
    }

}
