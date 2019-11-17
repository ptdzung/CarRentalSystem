/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.CustomerEntity;
import entity.PartnerEntity;
import entity.RentalRecordEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.EntityMismatchException;
import util.exception.InputDataValidationException;
import util.exception.RentalRecordNotFoundException;
import util.exception.UnknownPersistenceException;

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
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ReservationRecordSessionBean() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public String cancelReservation(long resId) throws RentalRecordNotFoundException {
        String message;
        RentalRecordEntity reservationToCancel = retrieveReservationById(resId);
        reservationToCancel.setCancelled(true);
        
        long difference = reservationToCancel.getRentedFrom().getTime() - reservationToCancel.getCancelledDate().getTime();
        int daysBetween = (int) (difference / (1000*60*60*24));
        if (reservationToCancel.isHasPaid()) {
            if (daysBetween >= 14) {
                reservationToCancel.setTotalAmount(new BigDecimal(0));
                message = "You have been refunded the full balance of your reservation";           
            }
            else if ( 14 > daysBetween &&  daysBetween >= 7) {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.2)));
                message = "You have been refunded 80% of the total amount";
            }
            else if ( 7 > daysBetween &&  daysBetween >= 3) {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.5)));
                message = "You have been refunded 50% of the total amount";
            }
            else {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.7)));
                message = "You have been refunded 30% of the total amount";
            }             
        }
        else {
            reservationToCancel.setHasPaid(true);
            if (daysBetween >= 14) {
                reservationToCancel.setTotalAmount(new BigDecimal(0));
                message = "You have not been charged the full balance of your reservation";          
            }
            else if ( 14 > daysBetween &&  daysBetween >= 7) {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.2)));
                message = "You have been charged for 20% of the total amount";
            }
            else if ( 7 > daysBetween &&  daysBetween >= 3) {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.5)));
                message = "You have been charged  for 50% of the total amount";
            }
            else {
                reservationToCancel.setTotalAmount(reservationToCancel.getTotalAmount().multiply(new BigDecimal(0.7)));
                message = "You have been charged for 70% of the total amount";
            }  
        }
        return message;
    }
        
     
    @Override
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
    
    @Override
    public String retrievePartnerReservationDetails(Long resId, Long partnerId) throws RentalRecordNotFoundException, EntityMismatchException{
        RentalRecordEntity res =  em.find(RentalRecordEntity.class, partnerId);
        if(res == null){
            throw new RentalRecordNotFoundException("Reservation Record not found");
        }else if(res.getPartner() == null){
            throw new EntityMismatchException("Partner Id provided does not match Partner Id in reservation record");
        }else if(!res.getPartner().getPartnerId().equals(partnerId)){
            throw new EntityMismatchException("Partner Id provided does not match Partner Id in reservation record");
        }else{
            String details = "Reservation Id: " + res.getRentalRecordId() + "\n" +
                             "Car: " + res.getCar() + "\n" +
                             "Start Date: " + res.getRentedFrom() + "\n" +
                             "End Date: " + res.getRentedTo() + "\n" +
                             "Total Amount: $" + res.getTotalAmount() + "\n";
            
            return details;
        }
    }

    @Override
    public List<RentalRecordEntity> retrieveRentalRecordByDate(Date date) {
        Query query = em.createQuery("SELECT rr FROM RentalRecordEntity rr");
        List<RentalRecordEntity> list = query.getResultList();
        List<RentalRecordEntity> returnList = new ArrayList<>();
        for (RentalRecordEntity r : list) {
            Date temp = r.getRentedFrom();
            if(temp.getTime() - date.getTime() > 86400000) {
                returnList.add(r);
            }
        }
        
        return returnList;
    }

    @Override
    public List<RentalRecordEntity> retrieveRentalRecordsByCustomer(CustomerEntity cus) {
        Query query = em.createQuery("SELECT rr FROM RentalRecordEntity rr WHERE rr.customer = :inCustomer");
        query.setParameter("inCustomer", cus);
        
        return query.getResultList();
    }
    
    

    @Override
    public boolean carAllocation(Date date) {
        Query query = em.createQuery("SELECT r FROM RentalRecordEntity r WHERE r.startDate BETWEEN :inStartDate AND :inEndDate");
        query.setParameter("inStartDate", date);
        query.setParameter("inEndDate", new Date(date.getTime() + 86399999));
        List<RentalRecordEntity> rentalRecords = query.getResultList();
        if (rentalRecords.isEmpty()) {
            return false;
        }
        
        for(RentalRecordEntity r: rentalRecords) {
            r.setCar(carSessionBean.retrieveCarForAllocation(r));
        }
        return true;
    }

    @Override
    public Long createNewRentalRecord(RentalRecordEntity record) throws InputDataValidationException, UnknownPersistenceException {
        try
        {
            Set<ConstraintViolation<RentalRecordEntity>>constraintViolations = validator.validate(record);
        
            if(constraintViolations.isEmpty())
            {
                em.persist(record);
                em.flush();

                return record.getRentalRecordId();
            }
            else
            {
                throw new InputDataValidationException(prepareInputDataValidationErrorsForRental(constraintViolations));
            }            
        }
        catch(PersistenceException ex) {
                throw new UnknownPersistenceException(ex.getMessage());
        }
    }
    
    private String prepareInputDataValidationErrorsForRental(Set<ConstraintViolation<RentalRecordEntity>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }

    @Override
    public List<RentalRecordEntity> retrieveRentalRecordsByPartner(PartnerEntity part) {
        Query query = em.createQuery("SELECT rr FROM RentalRecordEntity rr WHERE rr.partner = :inPartner");
        query.setParameter("inPartner", part);
        
        return query.getResultList();
    }

    
}