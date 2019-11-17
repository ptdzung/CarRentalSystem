/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.ws;

import ejb.session.stateless.PartnerSessionBeanLocal;
import ejb.session.stateless.ReservationRecordSessionBeanLocal;
import entity.PartnerEntity;
import entity.RentalRecordEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import util.exception.EntityMismatchException;
import util.exception.InvalidLoginCredentialException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Trishpal
 */
@WebService(serviceName = "PartnerWebService")
@Stateless
public class PartnerWebService {

    @EJB
    private PartnerSessionBeanLocal partnerSessionBeanLocal;
    
    @EJB
    private ReservationRecordSessionBeanLocal reservationSessionBeanLocal;
    
    
    @WebMethod(operationName = "partnerLogin")
    public PartnerEntity partnerLogin(@WebParam(name = "username") String username,
                                       @WebParam(name = "password") String password) 
            throws InvalidLoginCredentialException {
            
        return partnerSessionBeanLocal.partnerLogin(username, password);       
    }
    
    @WebMethod(operationName = "cancelReservation")
    public String cancelReservation(@WebParam(name = "resId") long resId) 
            throws RentalRecordNotFoundException {
        
        return reservationSessionBeanLocal.cancelReservation(resId);
    }
    
    @WebMethod(operationName = "retrievePartnerReservationDetails")
    public String retrievePartnerReservationDetails(@WebParam(name = "resId") long resId,
                                                     @WebParam(name = "partnerId") long partnerId) 
            throws RentalRecordNotFoundException, EntityMismatchException {
        
        return reservationSessionBeanLocal.retrievePartnerReservationDetails(resId, partnerId);
    }
    
    @WebMethod(operationName = "retrieveRentalRecordsByPartner")
    public List<RentalRecordEntity> retrieveRentalRecordsByPartner(@WebParam(name = "part") PartnerEntity part) {
        
        return reservationSessionBeanLocal.retrieveRentalRecordsByPartner(part);
    }
    
    
}
