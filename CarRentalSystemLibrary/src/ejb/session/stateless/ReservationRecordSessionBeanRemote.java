/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import util.exception.EntityMismatchException;
import util.exception.RentalRecordNotFoundException;

/**
 *
 * @author Trishpal
 */

public interface ReservationRecordSessionBeanRemote {

    public String retrieveReservationDetails(Long resId, Long customerId) throws RentalRecordNotFoundException, EntityMismatchException;

    public String cancelReservation(long resId) throws RentalRecordNotFoundException;
}
