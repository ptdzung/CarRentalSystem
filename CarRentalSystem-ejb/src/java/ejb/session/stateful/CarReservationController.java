/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateful;

import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.ReservationRecordSessionBeanLocal;
import entity.OwnCustomerEntity;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.InvalidLoginCredentialException;

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
    public void customerLogin(String username, String password) throws InvalidLoginCredentialException {
        customer = customerSessionBean.customerLogin(username, password);
    }
    
    @Override
    public void customerLogout() {
        customer =null;
    }
    
    public void setCustomerEmail(String email) {
        this.email = email;
    }
    
    
}
