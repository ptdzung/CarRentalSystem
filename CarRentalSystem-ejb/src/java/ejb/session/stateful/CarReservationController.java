/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import ejb.session.stateless.CarSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.RentalRateSessionBeanLocal;
import ejb.session.stateless.ReservationRecordSessionBeanLocal;
import entity.CarCategoryEntity;
import entity.CarEntity;
import entity.CarModelEntity;
import entity.OutletEntity;
import entity.OwnCustomerEntity;
import entity.PartnerEntity;
import entity.RentalDayEntity;
import entity.RentalRateEntity;
import entity.RentalRecordEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRecordNotFoundException;
import util.exception.EntityMismatchException;
import util.exception.InputDataValidationException;
import util.exception.RentalRateNotFoundException;
import util.exception.UnknownPersistenceException;

/**
 *
 * @author Trishpal
 */
@Stateful
@Local(CarReservationControllerLocal.class)
@Remote(CarReservationControllerRemote.class)
public class CarReservationController implements CarReservationControllerRemote, CarReservationControllerLocal {

    @EJB
    private RentalRateSessionBeanLocal rentalRateSessionBean;
    
    @EJB
    private CarSessionBeanLocal carSessionBean;
    
    @EJB
    private CustomerSessionBeanLocal customerSessionBean;
       
    @EJB
    private ReservationRecordSessionBeanLocal reservationRecordSessionBean;

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
    
    private OwnCustomerEntity customer;
    private PartnerEntity partner;
    private String email;

    public CarReservationController() {
    }
    
    @Override
    public OwnCustomerEntity customerLogin(String username, String password) throws InvalidLoginCredentialException {
        customer = customerSessionBean.customerLogin(username, password);
        if (customer != null) {
            return customer;
        } else {
            throw new InvalidLoginCredentialException("Invalid login credentials!");
        }
    }
    
    @Override
    public void customerLogout() {
        customer = null;
    }
    
    public void setCustomerEmail(String email) {
        this.email = email;
    }
    
    @Override
    public List<RentalRecordEntity> retrieveAllReservation() {
        return reservationRecordSessionBean.retrieveRentalRecordsByCustomer(customer);
    }
    
    @Override
    public String retrieveReservationDetails(Long resId) throws RentalRecordNotFoundException, EntityMismatchException {
        return reservationRecordSessionBean.retrieveReservationDetails(resId, customer.getCustomerId());
    }
    
    @Override
    public String retrievePartnerReservationDetails(Long resId) throws RentalRecordNotFoundException, EntityMismatchException {
        return reservationRecordSessionBean.retrievePartnerReservationDetails(resId, partner.getPartnerId());
    }
    
    @Override
    public String cancelReservation(Long resId) throws RentalRecordNotFoundException {
        return reservationRecordSessionBean.cancelReservation(resId);
    }

    @Override
    public List<CarEntity> searchCar(CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet) throws RentalRateNotFoundException {
        List<RentalRateEntity> availableRates = rentalRateSessionBean.retrieveRentalRatesForSearch(category, startDate, endDate);
        if (availableRates.isEmpty()) {
            throw new RentalRateNotFoundException("There is no available rental rates for this car category!");
        }
        
        List<CarEntity> list = new ArrayList<>();
        list = carSessionBean.searchCar(category, model, startDate, endDate, pickupOutlet);
        return list;
    }
    
    @Override
    public List<RentalRateEntity> retrieveListOfOptimalRates(CarCategoryEntity category, Date startDate, Date endDate) {
        List<RentalRateEntity> rates = rentalRateSessionBean.retrieveRentalRatesForSearch(category, startDate, endDate);
        List<Date> dates = getListOfDates(startDate, endDate);
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while(true) {
            dates.add(calendar.getTime());
            if (calendar.DATE == endDate.getDate() && calendar.MONTH == endDate.getMonth() && calendar.YEAR == endDate.getYear()) {
                break;
            }
            calendar.setTime(new Date(calendar.getTimeInMillis() + 86400000));
            calendar.set(calendar.HOUR, 0);
            calendar.set(calendar.MINUTE, 0);
        }
        dates.add(endDate);
        List<RentalRateEntity> returnRates = new ArrayList<>();
        
        for (Date d : dates) {
            List<RentalRateEntity> availableRates = new ArrayList<>();
            for (RentalRateEntity r: rates) {
                if (r.getStartDate() == null && r.getEndDate() == null) {
                    availableRates.add(r);
                } else if (d.after(r.getStartDate()) || d.before(r.getEndDate())) {
                    availableRates.add(r);
                }
            }
            RentalRateEntity optimal = rentalRateSessionBean.getOptimalRate(availableRates);
            returnRates.add(optimal);
        }
        
        return returnRates;
    }

    @Override
    public Long createNewReservation(String creditCardNumber, Boolean hasPaid, CarCategoryEntity category, CarModelEntity model, Date startDate, Date endDate, OutletEntity pickupOutlet, OutletEntity returnOutlet, BigDecimal totalAmount) {
        Long newRecordId = -1l;
        
        try {
            newRecordId = reservationRecordSessionBean.createNewRentalRecord(new RentalRecordEntity(startDate, endDate, creditCardNumber, customer, 
                                                                                  model, category, totalAmount, pickupOutlet, returnOutlet, hasPaid));
            RentalRecordEntity curr = reservationRecordSessionBean.retrieveReservationById(newRecordId);
            
            List<Date> dates = getListOfDates(startDate, endDate);
            List<RentalRateEntity> rates = retrieveListOfOptimalRates(category, startDate, endDate);
            for (int i = 0; i < dates.size(); i++) {
                RentalDayEntity day = new RentalDayEntity(dates.get(i), rates.get(i), curr);
                em.persist(day);
                em.flush();
            }
            
        } catch (RentalRecordNotFoundException | InputDataValidationException | UnknownPersistenceException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
        
        return newRecordId;
    }
    
    private List<Date> getListOfDates(Date startDate, Date endDate) {
        List<Date> dates = new ArrayList<>();
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        while(true) {
            dates.add(calendar.getTime());
            if (calendar.DATE == endDate.getDate() && calendar.MONTH == endDate.getMonth() && calendar.YEAR == endDate.getYear()) {
                break;
            }
            calendar.setTime(new Date(calendar.getTimeInMillis() + 86400000));
            calendar.set(calendar.HOUR, 0);
            calendar.set(calendar.MINUTE, 0);
        }
        dates.add(endDate);
        
        return dates;
    }

    @Override
    public List<RentalRecordEntity> retrieveAllPartnerReservation() {
        return reservationRecordSessionBean.retrieveRentalRecordsByPartner(partner);
    }
    
    
    
}