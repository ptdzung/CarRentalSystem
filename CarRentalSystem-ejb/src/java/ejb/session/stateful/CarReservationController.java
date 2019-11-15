/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.ReservationRecordSessionBeanLocal;
import entity.OwnCustomerEntity;
import entity.RentalRecordEntity;
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

/**
 *
 * @author Trishpal
 */
@Stateful
@Local(CarReservationControllerLocal.class)
@Remote(CarReservationControllerRemote.class)
public class CarReservationController implements CarReservationControllerRemote, CarReservationControllerLocal {

    @EJB
    private CustomerSessionBeanLocal customerSessionBean;
       
    @EJB
    private ReservationRecordSessionBeanLocal reservationRecordSessionBean;

    @PersistenceContext(unitName = "CarRentalManagementSystem-ejbPU")
    private EntityManager em;
    
    private OwnCustomerEntity customer;
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
    public String cancelReservation(Long resId) throws RentalRecordNotFoundException {
        return reservationRecordSessionBean.cancelReservation(resId);
    }
    
}