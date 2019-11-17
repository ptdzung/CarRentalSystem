/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package holidayreservationsystemjavaseclient;

import ws.client.EntityMismatchException_Exception;
import ws.client.InvalidLoginCredentialException_Exception;
import ws.client.RentalRecordNotFoundException_Exception;

/**
 *
 * @author Trishpal
 */
public class HolidayReservationSystemJavaSeClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InvalidLoginCredentialException_Exception, RentalRecordNotFoundException_Exception, EntityMismatchException_Exception {
       
        MainApp mainApp = new MainApp();
        mainApp.runApp();
    }
    
}
